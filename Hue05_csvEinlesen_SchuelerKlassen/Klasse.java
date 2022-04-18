package Hue05_csvEinlesen_SchuelerKlassen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Hue05_csvEinlesen_SchuelerKlassen.CSV_Einlesen;

public class Klasse extends CSV_Einlesen{


	public static void dropTableKlasse(Connection c, String tablename) {
		try { 
			Statement stmt = c.createStatement();
			String sql = "drop table if exists "+ tablename +";";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public static void createtableKlasse(Connection c, String tablename) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists "+ tablename +"" +
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

	public static void insertintotableKlasse(Connection c, String tablename, String klassenNr, String klassenvorstand, String schuelerAnzahl, String abteilung) {

		try {
			String sqlSchueler = "insert into " + tablename + " (klassenNr, klassenvorstand, schuelerAnzahl, abteilung) values (?, ?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sqlSchueler);

			stmt.setString(1, klassenNr);
			stmt.setString(2, klassenvorstand);
			stmt.setString(3, schuelerAnzahl);
			stmt.setString(4, abteilung);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void selectKlasse(Connection c, String tablename) {
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

