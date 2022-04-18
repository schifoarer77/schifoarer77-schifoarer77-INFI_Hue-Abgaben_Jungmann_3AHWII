package Hue04_csvDateiEinlesen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class CSV_einlesen {

	public static Connection createConnection(String url, String user, String password) {
		try {
			//Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Datenbank konnte nicht gefunden werden");
			System.exit(1);
			return null; 
		}
		finally {
			System.out.println("-- Console: Startbereit --");
			System.out.println();
		} 
	}


	public static void main(String args[]) {

		String path = "C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\Personen.csv";
		String line = "";  

		try {
			String url = "jdbc:mysql://localhost:3306/infi_csvdatei_einlesen";
			String user = "julian";
			String password = "220705";

			Connection c = createConnection(url, user, password);	

			
//BufferedReader

//			BufferedReader br = new BufferedReader(new FileReader(path));  
//
//			while ((line = br.readLine()) != null) {  
//				String[] values = line.split(";");
//
//				System.out.println(
//						"Vorname: " + values[0] + 
//						" Nachname: " + values[1] + 
//						" Wohnort: " + values[2]);
//			}       


//Scanner

			File f = new File("C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\Personen.csv");
			Scanner s = new Scanner(f);
			String[] values = new String[3];
			String str = "";

			while (s.hasNextLine()) {
				str = s.nextLine();
				values = str.split(";");
				String sql = "insert into personen (vorname, nachname, wohnort) values " +
				"(?, ?, ?);";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, values[0]);
				stmt.setString(2, values[1]);
				stmt.setString(3, values[2]);
				stmt.executeUpdate();
				stmt.close();
				System.out.println("Inhalt siehe phpMyAdmin");
			}
			s.close();
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException");
		}	catch (IOException e)   
		{  
			e.printStackTrace();  
		}
	}

}

