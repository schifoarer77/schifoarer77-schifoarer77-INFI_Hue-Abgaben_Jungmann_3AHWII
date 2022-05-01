package Hue07_jsonEinlesen_SchuelerKlassen;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Hue05_csvEinlesen_SchuelerKlassen.CSV_Einlesen;

public class Klasse extends Reader{


//	public static void dropTableKlasse(Connection c, String tablename) {
//		try { 
//			Statement stmt = c.createStatement();
//			String sql = "drop table if exists "+ tablename +";";
//			stmt.executeUpdate(sql);
//			stmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
//	}
//
//	public static void createtableKlasse(Connection c, String tablename) {
//		try {
//			Statement stmt = c.createStatement();
//			String sql = "create table if not exists "+ tablename +"" +
//					"(klassenNr INT PRIMARY KEY," +
//					"klassenvorstand VARCHAR(30)," +
//					"schuelerAnzahl INT NOT NULL," +
//					"abteilung VARCHAR(40));";
//			stmt.executeUpdate(sql);
//			stmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void insertintotableKlasse(Connection c, String tablename, String klassenNr, String klassenvorstand, String schuelerAnzahl, String abteilung) {
//
//		try {
//			String sqlSchueler = "insert into " + tablename + " (klassenNr, klassenvorstand, schuelerAnzahl, abteilung) values (?, ?, ?, ?);";
//			PreparedStatement stmt = c.prepareStatement(sqlSchueler);
//
//			stmt.setString(1, klassenNr);
//			stmt.setString(2, klassenvorstand);
//			stmt.setString(3, schuelerAnzahl);
//			stmt.setString(4, abteilung);
//			stmt.executeUpdate();
//			stmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	public static void ReaderKlasse(Connection c, String file, String tablename) {
		try {
			File fk = new File(file);
			Scanner s = new Scanner(fk);
			String str = "";
			
			while (s.hasNextLine()) {
				str = s.nextLine();
				Object obj = new JSONParser().parse(str);
				JSONObject js = (JSONObject) obj;
				
				long klassenNr = (long)js.get("klassenNr");
				String klassenvorstand = (String)js.get("klassenvorstand");
				String schuelerAnzahl = (String)js.get("schuelerAnzahl");
				String abteilung = (String)js.get("abteilung");
				
				String sql = "insert into "+ tablename +" (klassenNr, klassenvorstand, schuelerAnzahl, abteilung) value (?, ?, ?, ?);";
				PreparedStatement stmt = c.prepareStatement(sql);

				stmt.setLong(1, klassenNr);
				stmt.setString(2, klassenvorstand);
				stmt.setString(3, schuelerAnzahl);
				stmt.setString(4, abteilung);
				stmt.executeUpdate();
				stmt.close();
				System.out.printf("KlassenNr: %d\nKlassenvorstand: %d\nSchuelerAnzahl: %s\nAbteilung: %s\n", klassenNr, klassenvorstand, abteilung, abteilung);

			}
			s.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Statement stmt = c.createStatement();
			String sql = "select klassenNr, klassenvorstand, schuelerAnzahl, abteilung from "+ tablename +";";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("+---+---------------+----+--------------+");
			while (rs.next()) {
				int kn = rs.getInt("klassenNr");
				String kv = rs.getString("klassenvorstand");
				int sa = rs.getInt("schuelerAnzahl");
				String at = rs.getString("abteilung");
				System.out.printf("| %d | %s | %d | %s\t|\n", kn, kv, sa, at);
			}
			System.out.println("+---+---------------+----+--------------+");

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

