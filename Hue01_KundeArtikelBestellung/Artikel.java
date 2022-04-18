package Hue01_KundeArtikelBestellung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Artikel extends main_Runner{

	public static void createtableArtikel(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "drop table if exists Artikel;";

			sql = "create table if not exists Artikel" +
					"(id INTEGER PRIMARY KEY AUTOINCREMENT," +
					"bezeichnung VARCHAR(32)," +
					"preis DOUBLE);";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertintotableArtikel(Connection c,  String bezeichnung, double preis) {
		try {
			Statement stmt = c.createStatement();
			String sql = "insert into Artikel" + " (bezeichnung, preis) " +
					"values (\"" + bezeichnung + "\", "  + preis +");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Aufgabe c) Erweiterung
	public static void alterArtikelLagerbestand(Connection c,  String tabellenBezeichnung, String datatype) {
		try {
			Statement stmt = c.createStatement();
			String sql = "alter table Artikel add column " + tabellenBezeichnung + " " + datatype + ";";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateArtikelLagerbestand(Connection c, int artikelID, int lagerbestand) {
		try {
			Statement stmt = c.createStatement();
			String sql = "update Artikel set lagerbestand = "+ lagerbestand +" where id = " + artikelID +";";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Aufgabe d) Erweiterung
	public static boolean lagerbestandChecken(Connection c, int artikelID, int anzahl) {
		int lagerbestand = 0;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select lagerbestand from Artikel where id = " + artikelID + ";");

			rs.next();
			lagerbestand = rs.getInt("lagerbestand");
			rs.close();
			stmt.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(lagerbestand >= anzahl) {
			return true;
		}			
		return false;
	}
	
	//Aufgabe d) Erweiterung
	public static int selectLagerbestand(Connection c, int artikelID) {
		int lagerbestand = 0;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select lagerbestand from Artikel where id = " + artikelID + ";");

			rs.next();
			lagerbestand = rs.getInt("lagerbestand");
			rs.close();
			stmt.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lagerbestand;
	}
}