package Hue03_Testaufgabe_SchuelerKlassen_mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;

public class SchuelerZuKlasse extends Runner{

		public static void dropTableSchuelerZuKlasse(Connection c) {
			try {
				Statement stmt = c.createStatement();
				String sql = "drop table if exists SchuelerZuKlasse;";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void createtableSchuelerZuKlasse(Connection c) {
			try {
				Statement stmt = c.createStatement();
				String sql = "create table if not exists SchuelerZuKlasse" +
						"(schuelerID INT NOT NULL," +
						"klassenID INT NOT NULL," +
						"eintragsdatum DATE NOT NULL," +
						"PRIMARY KEY (schuelerID, klassenID, eintragsdatum)," +
						"FOREIGN KEY (schuelerID) REFERENCES Schueler(katalogNR)," +
						"FOREIGN KEY (klassenID) REFERENCES Klasse(klassenNr));";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				 
			}
		}

		public static void insertintotableSchuelerZuKlasse(Connection c, int schuelerID,  int klassenID) {					
			try {
				LocalDate today = LocalDate.now();
				java.sql.Date sqlLd = java.sql.Date.valueOf(today);
				
				Statement stmt = c.createStatement();
				String sql = "insert into SchuelerZuKlasse (schuelerID, klassenID, eintragsdatum) " +
						"values (" + schuelerID + ", " + klassenID + ", \"" + sqlLd + "\");";
				stmt.executeUpdate(sql);
				stmt.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}

