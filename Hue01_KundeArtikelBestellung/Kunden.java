package Hue01_KundeArtikelBestellung;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Kunden {

	public static void createtableKunden(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "drop table if exists Kunden;";
			
			sql = "create table if not exists Kunden" +
					"(id INTEGER PRIMARY KEY AUTOINCREMENT," +
					"name VARCHAR(30)," +
					"email VARCHAR(25));";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertintotableKunden(Connection c,  String name, String email) {
		try {
			Statement stmt = c.createStatement();
			String sql = "insert into Kunden" + " (name, email) " +
					"values (\"" + name + "\", \"" + email +"\");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
