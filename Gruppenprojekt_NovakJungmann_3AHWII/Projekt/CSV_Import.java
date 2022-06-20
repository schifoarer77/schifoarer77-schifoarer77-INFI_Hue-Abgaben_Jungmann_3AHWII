package Gruppenprojekt_NovakJungmann_3AHWII.Projekt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Properties;
import java.util.Scanner;


public class CSV_Import {				
	
	public static void reader(Connection conn, String tableName, String path) throws Exception {
		Scanner scanner = new Scanner (new File(path));
		if(scanner.hasNextLine()) scanner.nextLine(); //bei striktem Format wird header nicht benötigt. Trotzdem muss der Scanner es "ueberlesen"
		while(scanner.hasNextLine()) {
			String[] content = scanner.nextLine().split(";");
			for (int i = 0; i < content.length; i++) {
				content[i] = content[i].trim();
			}
			try {
				if(tableName.equals("user")) insertOrUpdateUser(conn, content, true);
				else if(tableName.equals("classifieds")) insertOrUpdateClassifiedAd(conn, content, true);
			} catch(Exception e) {
				if(tableName.equals("user")) insertOrUpdateUser(conn, content, false);
				else if(tableName.equals("classifieds")) insertOrUpdateClassifiedAd(conn, content, false);
			}
		}

		
/* Fuer Dynamische Inserts & Updates
 * nicht verwendet
 */
		/*String[][] columns; 
		if(scanner.hasNextLine()) {
			String[] contents = scanner.nextLine().split(";");
			for (int i = 0; i < contents.length; i++) {
				contents[i].trim();
			}
			columns = createHeader(contents);
		}
		else columns = new String[0][0];


		while(scanner.hasNextLine()) {
			String[] contents = scanner.nextLine().split(";");
			for (int i = 0; i < contents.length; i++) {
				contents[i].trim();
			}
			//wenn insert nicht klappt --> update
			try {
				insertInto(conn, tableName, columns, contents);		//sonst contents inserten in Tabelle
			} catch(Exception e) {
				update(conn, tableName, columns, contents);
			}
		}*/
		
		
		scanner.close();
	}


	
	public static void insertOrUpdateUser(Connection conn, String[] content, boolean insert) throws SQLException {
		String sql;
		if(insert) sql = "CALL insertUser(?,?,?,?);";
		else sql = "CALL updateUser(?,?,?,?);";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		
		preStmt.setString(1, content[0]);
		preStmt.setString(2, content[1]);
		preStmt.setString(3, content[2]);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate datum = LocalDate.parse(content[3], formatter);
		preStmt.setDate(4, java.sql.Date.valueOf(datum));
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	
	public static void insertOrUpdateClassifiedAd(Connection conn, String[] content, boolean insert) throws SQLException {
		String sql;
		if(insert) sql = "CALL insertClassifieds(?,?,?,?,?);";
		else sql = "CALL updateClassifieds(?,?,?,?,?);";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		
		preStmt.setInt(1, Integer.parseInt(content[0]));
		preStmt.setString(2, content[1]);
		preStmt.setDouble(3, Double.parseDouble(content[2]));
		preStmt.setString(4, content[3]);
		preStmt.setString(5, content[4]);
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	
	
	
/*
 * 	DYNAMISCHE INSERTS und UPDATES
 * 	unterstützen keine stored Procedures, deswegen nicht benutzt
 * 
 * 	Vorteil: solange der Primary key in der csv-datei enthalten ist, können alle anderen Inhalte weggelassen werden oder ihre reihenfolge tauschen
 *  Nachteil: aufwaendig + wie oben erwaehnt ohne stored Procedures
 */
	
/*	
	public static String[][] createHeader(String[] contents) {
		String[][] columns = new String[contents.length][2];
		for (int i = 0; i < contents.length; i++) {
			columns[i][0] = contents[i];

			if (contents[i].contains("id")) columns[i][1] = "INT";
			else if (contents[i].contains("price")) columns[i][1] = "DOUBLE";
			else if (contents[i].contains("date")) columns[i][1] = "DATE";
			else columns[i][1] = "VARCHAR(255)";
		}
		return columns;
	}

//  dynamischer insert
	public static void insertInto(Connection conn, String tableName, String[][] columns, String[] contents) throws Exception {
		String sql = String.format("INSERT INTO %s (", tableName);
		//TODO sql-aufbau mit StringWriter
		for (int i = 0; i < columns.length; i++) {
			sql += columns[i][0];
			if(i < columns.length - 1) sql += ", ";
		}
		sql += ") VALUES (";
		for (int i = 0; i < columns.length; i++) {
			sql += "?";
			if(i < columns.length - 1) sql += ", ";
		}
		sql += ");";

		PreparedStatement preStmt = conn.prepareStatement(sql);
		for (int i = 0; i < columns.length; i++) {
			//columns[i][1] -> Datentyp
			if(columns[i][1].equals("INT")) preStmt.setInt((i + 1), Integer.parseInt(contents[i]));
			else if(columns[i][1].equals("DOUBLE")) preStmt.setDouble((i + 1), Double.parseDouble(contents[i]));
			else if(columns[i][1].equals("DATE")) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate datum = LocalDate.parse(contents[i], formatter);
				preStmt.setDate((i + 1), java.sql.Date.valueOf(datum));
			}
			else preStmt.setString((i + 1), contents[i]);
		}
		preStmt.executeUpdate();
		preStmt.close();
	}

	public static void update(Connection conn, String tableName, String[][] columns, String[] contents) throws Exception {
		String sql = String.format("UPDATE %s SET ", tableName);
		//email an stelle 0. email als primary key gibt an, was geupdatet wird
		//TODO sql-aufbau mit StringWriter
		for (int i = 1; i < columns.length; i++) {
			sql += String.format("%s=?", columns[i][0]);
			if(i < columns.length - 1) sql += ", ";
			else sql += " ";
		}
		sql += String.format("WHERE email = ?;", contents[0]);

		PreparedStatement preStmt = conn.prepareStatement(sql);
		for (int i = 1; i < columns.length; i++) {
			//columns[i][1] -> Datentyp
			if(columns[i][1].equals("INT")) preStmt.setInt(i, Integer.parseInt(contents[i]));
			else if(columns[i][1].equals("DOUBLE")) preStmt.setDouble(i, Double.parseDouble(contents[i]));
			else if(columns[i][1].equals("DATE")) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate datum = LocalDate.parse(contents[i], formatter);
				preStmt.setDate(i, java.sql.Date.valueOf(datum));
			}
			else preStmt.setString(i, contents[i]);
		}
		preStmt.setString(columns.length, contents[0]);
		preStmt.executeUpdate();
		preStmt.close();
	}
*/
}
