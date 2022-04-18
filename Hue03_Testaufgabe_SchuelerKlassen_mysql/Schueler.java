package Hue03_Testaufgabe_SchuelerKlassen_mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Hue05_csvEinlesen_SchuelerKlassen.CSV_Einlesen;


public class Schueler extends Runner{

		public static void dropTableSchueler(Connection c) {
			try {
				Statement stmt = c.createStatement();
				String sql = "drop table if exists Schueler;";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void createtableSchueler(Connection c) {
			try {
				Statement stmt = c.createStatement();
				String sql = "create table if not exists Schueler" +
						"(katalogNR INT PRIMARY KEY AUTO_INCREMENT," +
						"vorname VARCHAR(20)," +
						"nachname VARCHAR(20)," +
						"email VARCHAR(25));";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		public static void insertintotableSchueler(Connection c,  String vorname, String nachname, String email) {
			try {
				Statement stmt = c.createStatement();
				String sql = "insert into Schueler (vorname, nachname, email) " +
						"values (\"" + vorname + "\", \"" + nachname + "\" , \"" + email + "\");";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}

