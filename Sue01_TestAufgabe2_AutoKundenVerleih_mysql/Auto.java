package Sue01_TestAufgabe2_AutoKundenVerleih_mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Auto extends Runner{

	public static void dropTableAuto(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "drop table if exists Auto;";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createtableAuto(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists Auto" +
					"(kennzeichen VARCHAR(8) PRIMARY KEY," +
					"marke VARCHAR(20)," +
					"modell VARCHAR(20)," +
					"preisProTag DOUBLE," +
					"zulassungsdatum DATE);";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public static void insertintotableAuto(Connection c, String kennzeichen, String marke, String modell, double preisProTag, LocalDate zulassungsdatum) {
		try {
			String sqlAuto = "insert into Auto (kennzeichen, marke, modell, preisProTag, zulassungsdatum) values (?, ?, ?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sqlAuto);
			
			java.sql.Date sqlZulassungsdatum = java.sql.Date.valueOf(zulassungsdatum);

			stmt.setString(1, kennzeichen);
			stmt.setString(2, marke);
			stmt.setString(3, modell);
			stmt.setDouble(4, preisProTag);
			stmt.setDate(5, sqlZulassungsdatum);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

