package Hue06_csvExportieren_SchuelerKlassen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Klasse extends CSV_Writer{


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

	public static void insertintotableKlasse(Connection c, String klassenNr, String klassenvorstand, String schuelerAnzahl, String abteilung) {

		try {
			String sqlSchueler = "insert into Klasse (klassenNr, klassenvorstand, schuelerAnzahl, abteilung) values (?, ?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sqlSchueler);

			stmt.setString(1, klassenNr);
			stmt.setString(2, klassenvorstand);
			stmt.setString(3, schuelerAnzahl);
			stmt.setString(4, abteilung);
			stmt.executeUpdate(sqlSchueler);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void KlassenWriter(Connection c, String klasse) {
		try { 
			File f = new File(klasse);
			FileWriter fw = new FileWriter(f);

			Statement stmt = c.createStatement();
			String sql = "select klassenNr, klassenvorstand, schuelerAnzahl, abteilung from klasse;";
			ResultSet rs = stmt.executeQuery(sql);
			int id = 1;	//autoincrement

			while (rs.next()) {
				int kn = rs.getInt("klassenNr");
				String kv = rs.getString("klassenvorstand");
				int sa = rs.getInt("schuelerAnzahl");
				String at = rs.getString("abteilung");

				String csv = id + ", " + kn + ", " + kv + ", " + sa + ", " + at;
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
