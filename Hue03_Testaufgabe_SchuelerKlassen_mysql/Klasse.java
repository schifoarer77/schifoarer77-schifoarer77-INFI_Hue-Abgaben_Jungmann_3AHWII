package Hue03_Testaufgabe_SchuelerKlassen_mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Hue05_csvEinlesen_SchuelerKlassen.CSV_Einlesen;

public class Klasse extends Runner{


		public static void dropTableKlasse(Connection c) {
			try { 
				Statement stmt = c.createStatement();
				String sql = "drop table if exists Klasse;";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		public static void createtableKlasse(Connection c) {
			try {
				Statement stmt = c.createStatement();
				String sql = "create table if not exists Klasse" +
						"(klassenNr INT PRIMARY KEY," +
						"klassenvorstand VARCHAR(30)," +
						"schuelerAnzahl INT NOT NULL," +
						"abteilung VARCHAR(40));";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public static void insertintotableKlasse(Connection c,  int klassenNr, int schuelerAnzahl, String klassenvorstand, String abteilung) {
			try {
				Statement stmt = c.createStatement();
				String sql = "insert into Klasse (klassenNr, klassenvorstand, schuelerAnzahl, abteilung) " +
						"values ("+ klassenNr +", \"" + klassenvorstand + "\", "+ schuelerAnzahl +", \""+ abteilung + "\");";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}

