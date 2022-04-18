package Hue02_AufMySQLUmgeschrieben;

import java.sql.*;

public class main_Runner {

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
			System.out.println("+++ Sind im finally +++");
			System.out.println();
		}
	}


	public static void main( String args[] ) {
		try {
			String url = "jdbc:mysql://localhost:3306/infi_01_artkundbest";
			String user = "julian";
			String password = "220705";

			Connection c = createConnection(url, user, password);	
			System.out.println("--Connection erstellen--");
			System.out.println("Erfolgreich");
			System.out.println();
			c.setAutoCommit(true);


			System.out.println("--Table löschen--");
			Bestellung.dropTableBestellung(c);
			Artikel.dropTableArtikel(c);
			Kunden.dropTableKunden(c);
			System.out.println("Erfolgreich");
			System.out.println();

			System.out.println("--Table erstellen--");
			Artikel.createtableArtikel(c); 
			Kunden.createtableKunden(c);
			Bestellung.createtableBestellung(c);
			System.out.println("Erfolgreich");
			System.out.println();

			//Kunde
			System.out.println("--Kunden--");
			Kunden.insertintotableKunden(c, "Maier Hans", "maier@gmail.com");
			Kunden.insertintotableKunden(c, "Walder Mathias", "walder@mathias.de");
			Kunden.insertintotableKunden(c, "Mauer Benjamin", "mauer@chello.at");
			System.out.println("Erfolgreich");
			System.out.println();

			//Artikel			
			System.out.println("--Artikel--");
			Artikel.insertintotableArtikel(c, "Allgemeinmedizin", 39.99);
			Artikel.insertintotableArtikel(c, "Wirtschaftsinformatik", 45.50);
			Artikel.insertintotableArtikel(c, "Sport", 23.45);
			System.out.println("Erfolgreich");
			System.out.println();

			//Bestellung
			System.out.println("--Bestellung--");
			Bestellung.insertintotableBestellung(c, 1, 1, 6, "2021-11-08");
			Bestellung.insertintotableBestellung(c, 2, 2, 9, "2019-05-05");
			Bestellung.insertintotableBestellung(c, 3, 3, 16, "2020-03-01");
			System.out.println("Erfolgreich");
			System.out.println();

			//Bestellung des Kunde
			System.out.println("--Bestellung des Kunden--");
			Bestellung.selectBestellung(c, 1);
			System.out.println("Erfolgreich");
			System.out.println();

			//Erweiterung Bsp a)
			System.out.println("--Erweiterung Bsp a)--");
			Bestellung.deleteBestellung(c, 1, 3, password);
			System.out.println("Erfolgreich");
			System.out.println();

			//Erweiterung Bsp b)
			System.out.println("--Erweiterung Bsp b)--");
			Bestellung.updateBestellung(c, 1, 5, 2, password);
			System.out.println("Erfolgreich");
			System.out.println();

			//Erweiterung Bsp c)
			System.out.println("--Erweiterung Bsp c)--");
			Artikel.alterArtikelLagerbestand(c, "lagerbestand", "INTEGER");
			Artikel.updateArtikelLagerbestand(c, 2, 3);
			System.out.println("Erfolgreich");
			System.out.println();

			//Erweiterung Bsp d)
			System.out.println("--Erweiterung Bsp d)--");
			if(Artikel.lagerbestandChecken(c, 1, 8)) {
				System.out.println("Bestellung wurde für Sie erfolgreich durchgeführt");
			}else {
				System.out.println("Bestellung fehlgeschlagen, zu wenig Artikel verfügbar");
			}
			System.out.println();

			//Erweiterung Zusatzaufgabe)
			System.out.println("--Erweiterung Zusatzaufgabe--");
			Bestellung.LagerbestandUpdaten(c, 1, 2, 1, "2021-11-08");
			System.out.println();
			System.out.println("Neuer Lagerbestand:");
			System.out.println(Artikel.selectLagerbestand(c, 2));
			System.out.println("Erfolgreich");
			c.close();


		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
