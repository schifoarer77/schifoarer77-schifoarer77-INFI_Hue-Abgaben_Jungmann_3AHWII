package Hue06_csvExportieren_SchuelerKlassen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Hue03_Testaufgabe_SchuelerKlassen_mysql.Runner;


public class Schueler extends CSV_Writer{

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


	public static void insertintotableSchueler(Connection c, String katalogNR, String vorname, String nachname, String email) {

		try {
			String sqlSchueler = "insert into Schueler (katalogNR, vorname, nachname, email) values (?, ?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sqlSchueler);

			stmt.setString(1, katalogNR);
			stmt.setString(2, vorname);
			stmt.setString(3, nachname);
			stmt.setString(4, email);
			stmt.executeUpdate(sqlSchueler);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void SchuelerWriter(Connection c, String schueler) {
		try { 
			File f = new File(schueler);
			FileWriter fw = new FileWriter(f);

			Statement stmt = c.createStatement();
			String sql = "select katalogNR, vorname, nachname, email from schueler;";
			ResultSet rs = stmt.executeQuery(sql);
			int id = 1;	//autoincrement

			while (rs.next()) {
				int kn = rs.getInt("katalogNR");
				String vn = rs.getString("vorname");
				String nn = rs.getString("nachname");
				String el = rs.getString("email");

				String csv = id + ", " + kn + ", " + vn + ", " + nn + ", " + el;
				fw.write(csv);
				id++;
			}

			rs.close();
			stmt.close();
			fw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}   catch (IOException e) {
			e.printStackTrace();
		}
	}
}



