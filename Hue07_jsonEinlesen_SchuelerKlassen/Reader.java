package Hue07_jsonEinlesen_SchuelerKlassen;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;



public class Reader {

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

		try {
			String url = "jdbc:mysql://localhost:3306/infi_hue_07_jsonreader_schuelerklassen";
			String user = "julian";
			String password = "220705";

			Connection c = createConnection(url, user, password);	

			Schueler.ReaderSchueler(c, "C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\jsonImport\\Schueler.txt", "Schueler");
			Klasse.ReaderKlasse(c, "C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\jsonImport\\Klassen.txt", "Klassen");


			c.close();
	
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException");
		}
	}
}

