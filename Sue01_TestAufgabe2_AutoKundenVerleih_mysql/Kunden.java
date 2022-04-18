package Sue01_TestAufgabe2_AutoKundenVerleih_mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Kunden extends Runner{

	public static void dropTableKunden(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "drop table if exists Kunden;";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createtableKunden(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists Kunden" +
					"(kundenNR INT PRIMARY KEY AUTO_INCREMENT," +
					"name VARCHAR(30)," +
					"geburtsdatum DATE," +
					"iban VARCHAR(22));";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public static void insertintotableKunden(Connection c,  String name, LocalDate geburtsdatum, String iban) {
		try {
			String sqlKunden = "insert into Kunden (name, geburtsdatum, iban) values (?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sqlKunden);
			
			java.sql.Date sqlgeburtsdatum = java.sql.Date.valueOf(geburtsdatum);
			
			stmt.setString(1, name);
			stmt.setDate(2, sqlgeburtsdatum);
			stmt.setString(3, iban);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
