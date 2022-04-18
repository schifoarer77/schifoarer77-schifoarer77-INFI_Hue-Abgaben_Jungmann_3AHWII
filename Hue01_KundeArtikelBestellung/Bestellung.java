package Hue01_KundeArtikelBestellung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bestellung {

	public static void createtableBestellung(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "drop table if exists Bestellung";

			sql = "create table if not exists Bestellung" +
					"(kundenID INTEGER NOT NULL," +
					"artikelID INTEGER NOT NULL," +
					"bestelldatum DATE," +
					"anzahl INTEGER," +
					"PRIMARY KEY (kundenID, artikelID, bestelldatum)," +
					"FOREIGN KEY (kundenID) REFERENCES Kunden(id)," +
					"FOREIGN KEY (artikelID) REFERENCES Artikel(id));";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertintotableBestellung(Connection c, int kundenID,  int artikelID, int anzahl, String bestelldatum) {
		try {
			Statement stmt = c.createStatement();
			String sql = "insert into Bestellung" + " (kundenID, artikelID, anzahl, bestelldatum) " +
					"values (" + kundenID + ", " + artikelID + ", " + anzahl + ", \"" + bestelldatum + "\");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static void selectBestellung(Connection c, int Kunden) {
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT k.name, a.bezeichnung, b.anzahl, (SELECT a.preis * b.anzahl " +
            		"FROM Artikel a INNER JOIN Bestellung b ON a.id = b.artikelID " +
            		"WHERE b.kundenID = " + Kunden + ")AS gesamtPreis FROM Kunden k " +
                    "INNER JOIN Bestellung b ON k.id = b.kundenID " +
                    "INNER JOIN Artikel a ON a.id = b.artikelID " +
                    "WHERE b.kundenID = " + Kunden + ";");

            while ( rs.next() ) {
                 String  name = rs.getString("name");
                 String  bezeichnung = rs.getString("bezeichnung");
                 int anzahl = rs.getInt("anzahl");
                 double gesamtPreis = rs.getDouble("gesamtPreis");

                 System.out.println("Name = " + name );
                 System.out.println("Bezeichnung = " + bezeichnung );
                 System.out.println("Anzahl = " + anzahl );
                 System.out.printf("Gesamtpreis = %5.2f$", gesamtPreis);
                 System.out.println();
              }

            rs.close();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
//Aufgabe a) Erweiterung
	public static void deleteBestellung(Connection c, int kundenID, int artikelID) {
		try {
			Statement stmt = c.createStatement();
			String sql = "delete from Bestellung where kundenID = " + kundenID + " and artikelID = " + artikelID + ";";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//Aufgabe b) Erweiterung
	public static void updateBestellung(Connection c, int kundenID, int artikelID, int anzahl) {
		try {
			Statement stmt = c.createStatement();
			String sql = "update Bestellung set anzahl = "+ anzahl +" where kundenID = " + kundenID +" and artikelID = " + artikelID +";";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//Aufgabe Zusatzaufgabe
	public static void LagerbestandUpdaten(Connection c, int kundenID, int artikelID, int anzahl, String bestelldatum) {
		int newLagerbestand = 0;
		if (Artikel.lagerbestandChecken(c, artikelID,  anzahl)) { 
			newLagerbestand = Artikel.selectLagerbestand(c, artikelID) - anzahl;
			
		insertintotableBestellung(c, kundenID, artikelID, anzahl, bestelldatum);
		Artikel.updateArtikelLagerbestand(c, artikelID, newLagerbestand);
		System.out.println("Artikel wurde erfolgreich bestellt!");
		}else {
			System.out.println("Artikel konnte nicht bestellt werden!");

		}
	}
}
