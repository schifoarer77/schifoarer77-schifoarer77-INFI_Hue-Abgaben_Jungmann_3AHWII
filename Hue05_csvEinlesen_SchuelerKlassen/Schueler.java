package Hue05_csvEinlesen_SchuelerKlassen;

import java.sql.*;

import Hue03_Testaufgabe_SchuelerKlassen_mysql.Runner;


public class Schueler extends Runner{

		public static void dropTableSchueler(Connection c, String tablename) {
			try {
				Statement stmt = c.createStatement();
				String sql = "drop table if exists "+ tablename +";";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void createtableSchueler(Connection c, String tablename) {
			try {
				Statement stmt = c.createStatement();
				String sql = "create table if not exists "+ tablename +"" +
						"(katalogNR INT PRIMARY KEY," +
						"vorname VARCHAR(20)," +
						"nachname VARCHAR(20)," +
						"email VARCHAR(25));";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		public static void insertintotableSchueler(Connection c, String tablename, String katalogNR, String vorname, String nachname, String email) {
			
			try {
				String sqlSchueler = "insert into " + tablename + " (katalogNR, vorname, nachname, email) values (?, ?, ?, ?);";
				PreparedStatement stmt = c.prepareStatement(sqlSchueler);
				
				stmt.setString(1, katalogNR);
				stmt.setString(2, vorname);
				stmt.setString(3, nachname);
				stmt.setString(4, email);
				stmt.executeUpdate();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public static void selectSchueler(Connection c, String tablename) {
			try {
				Statement stmt = c.createStatement();
				String sql = "select katalogNR, vorname, nachname, email from "+ tablename +";";
				ResultSet rs = stmt.executeQuery(sql);

				System.out.println("+---+---------------+----+--------------+");
				while (rs.next()) {
					int kn = rs.getInt("katalogNR");
					String vn = rs.getString("vorname");
					String nn = rs.getString("nachname");
					String el = rs.getString("email");
					System.out.printf("| %d | %s | %s | %s\t|\n", kn, vn, nn, el);
				}
				System.out.println("+---+---------------+----+--------------+");

				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}

