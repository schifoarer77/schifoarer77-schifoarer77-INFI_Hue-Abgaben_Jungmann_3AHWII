package Hue07_jsonEinlesen_SchuelerKlassen;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.xdevapi.JsonParser;

public class Schueler extends Reader{

//		public static void dropTableSchueler(Connection c, String tablename) {
//			try {
//				Statement stmt = c.createStatement();
//				String sql = "drop table if exists "+ tablename +";";
//				stmt.executeUpdate(sql);
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		
//		public static void createtableSchueler(Connection c, String tablename) {
//			try {
//				Statement stmt = c.createStatement();
//				String sql = "create table if not exists "+ tablename +"" +
//						"(katalogNR INT PRIMARY KEY," +
//						"vorname VARCHAR(20)," +
//						"nachname VARCHAR(20)," +
//						"email VARCHAR(25));";
//				stmt.executeUpdate(sql);
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} 
//		}
//		
//		
//		public static void insertintotableSchueler(Connection c, String tablename, String katalogNR, String vorname, String nachname, String email) {
//			
//			try {
//				String sqlSchueler = "insert into " + tablename + " (katalogNR, vorname, nachname, email) values (?, ?, ?, ?);";
//				PreparedStatement stmt = c.prepareStatement(sqlSchueler);
//				
//				stmt.setString(1, katalogNR);
//				stmt.setString(2, vorname);
//				stmt.setString(3, nachname);
//				stmt.setString(4, email);
//				stmt.executeUpdate();
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

		public static void ReaderSchueler(Connection c, String file, String tablename) {
			try {
				File fs = new File(file);
				Scanner s = new Scanner(fs);
				String str = "";
				
				while (s.hasNextLine()) {
					str = s.nextLine();
					Object obj = new JSONParser().parse(str);
					JSONObject js = (JSONObject) obj;
					
					long katalogNR = (long)js.get("katalogNR");
					String vorname = (String)js.get("vorname");
					String nachname = (String)js.get("nachname");
					String email = (String)js.get("email");
					
					String sql = "insert into "+ tablename +" (katalogNR, vorname, nachname, email) value (?, ?, ?, ?);";
					PreparedStatement stmt = c.prepareStatement(sql);

					stmt.setLong(1, katalogNR);
					stmt.setString(2, vorname);
					stmt.setString(3, nachname);
					stmt.setString(4, email);
					stmt.executeUpdate();
					stmt.close();
					System.out.printf("KatalogNR: %d\nVorname: %s\nNachname: %s\nEmail: %s\n", katalogNR, vorname, nachname, email);

				}
				s.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

