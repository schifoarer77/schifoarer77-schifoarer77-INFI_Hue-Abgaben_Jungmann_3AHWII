package Hue01_KundeArtikelBestellung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class main_Runner {

	public static void main(String args[]) {
		try {
			Connection c = createConnection();	
			c.setAutoCommit(true);
			
			System.out.println("\n-------------------------------------------Artikel--------------------------------------------");
			System.out.println();
			Artikel.createtableArtikel(c); 
			Artikel.insertintotableArtikel(c, "Allgemeinmedizin", 39.99);
			Artikel.insertintotableArtikel(c, "Wirtschaftsinformatik", 45.50);
			Artikel.insertintotableArtikel(c, "Sport", 23.45);
			System.out.println();
			System.out.println();
			
			
			System.out.println("-------------------------------------------Kunden---------------------------------------------");
			System.out.println();
			Kunden.createtableKunden(c);
			Kunden.insertintotableKunden(c, "Maier Hans", "maier@gmail.com");
			Kunden.insertintotableKunden(c, "Walder Mathias", "walder@mathias.de");
			Kunden.insertintotableKunden(c, "Mauer Benjamin", "mauer@chello.at");
			System.out.println();
			System.out.println();
			
			
			System.out.println("-----------------------------------------Bestellung-------------------------------------------");
			System.out.println();
			Bestellung.createtableBestellung(c);
			System.out.println();
			Bestellung.insertintotableBestellung(c, 1, 1, 6, "2021-11-08");
			Bestellung.insertintotableBestellung(c, 2, 2, 9, "2019-05-05");
			Bestellung.insertintotableBestellung(c, 3, 3, 16, "2020-03-01");
			System.out.println();
			System.out.println();
	
			
			System.out.println("---------------------------------Bestellung des Kunden----------------------------------------");
			System.out.println();
			Bestellung.selectBestellung(c, 1);
			System.out.println();
			System.out.println("==============================================================================================");
			System.out.println();
			

		//Aufgabe a)
			System.out.println("Aufgabe a) Erweiterung");
			System.out.println();
			Bestellung.deleteBestellung(c, 1, 3);
			System.out.println("--------------------------------------------------------------------");
			System.out.println();
			
		//Aufgabe b)
			System.out.println("Aufgabe b) Erweiterung");
			System.out.println();
			Bestellung.updateBestellung(c, 1, 5, 2);
			System.out.println("--------------------------------------------------------------------");
			System.out.println();
			
		//Aufgabe c)
			System.out.println("Aufgabe c) Erweiterung");
			System.out.println();
			Artikel.alterArtikelLagerbestand(c, "lagerbestand", "INTEGER");
			Artikel.updateArtikelLagerbestand(c, 2, 3);
			System.out.println("--------------------------------------------------------------------");
			System.out.println();
			
		//Aufgabe d)
			System.out.println("Aufgabe d) Erweiterung");
			System.out.println();
			if(Artikel.lagerbestandChecken(c, 1, 8)) {
				System.out.println("Bestellung wurde für Sie erfolgreich durchgeführt");
			}else {
				System.out.println("Bestellung fehlgeschlagen, zu wenig Artikel verfügbar");
			}
			System.out.println("--------------------------------------------------------------------");
			System.out.println();
			
		//Aufgabe Zusatzaufgabe
			System.out.println("Aufgabe Zusatzaufgabe");
			System.out.println();
			Bestellung.LagerbestandUpdaten(c, 1, 2, 1, "2021-11-08");
			System.out.println();
			System.out.println("Neuer Lagerbestand:");
			System.out.println(Artikel.selectLagerbestand(c, 2));
			c.close();

			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("finally")
	public static Connection createConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:C:\\Users\\julia\\KundenArtikelBestellung.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
			return null; 
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Datenbank konnte nicht gefunden werden");
			System.exit(1);
			return null; 
		}
		finally {
			System.out.println("==============================================================================================");
			System.out.println("Sind im finally");
			System.out.println();
		}
	}
}
