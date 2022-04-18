package Hue05_csvEinlesen_SchuelerKlassen;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;



public class CSV_Einlesen {

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
			String url = "jdbc:mysql://localhost:3306/infi_hue_05_csvfile_schuelerklassen";
			String user = "julian";
			String password = "220705";

			Connection c = createConnection(url, user, password);	

			File fs = new File("C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\Schueler.csv");
			File fk = new File("C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\Klassen.csv");
			Scanner s = new Scanner(fs);
			String str = "";
			
			Schueler.dropTableSchueler(c, "schueler");
			Klasse.dropTableKlasse(c, "klasse");

			Schueler.createtableSchueler(c, "schueler");
			Klasse.createtableKlasse(c, "klasse");

			while (s.hasNextLine()) {
				str = s.nextLine();
				String[] values = str.split(";");

				Schueler.insertintotableSchueler(c, "schueler", values[0], values[1], values[2], values[3]);
			}
			s.close();

			Scanner s2 = new Scanner(fk);
			String str2 = "";

			while (s2.hasNextLine()) {
				str2 = s2.nextLine();
				String[] values = str2.split(";");

				Klasse.insertintotableKlasse(c, "klasse", values[0], values[1], values[2], values[3]);

				Schueler.selectSchueler(c, "schueler");
				System.out.println();
				Klasse.selectKlasse(c, "klasse");
			}
			
			s2.close();
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

