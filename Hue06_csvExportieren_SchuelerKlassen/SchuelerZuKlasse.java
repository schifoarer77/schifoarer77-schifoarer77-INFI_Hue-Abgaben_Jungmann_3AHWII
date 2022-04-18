package Hue06_csvExportieren_SchuelerKlassen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.PreparedStatement;


public class SchuelerZuKlasse extends CSV_Writer{

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
	
	public static void insertintotableSchuelerZuKlasse(Connection c, String schuelerID, String klassenID, String eintragsdatum) {					
		try {
			LocalDate today = LocalDate.now();
			java.sql.Date sqlLd = java.sql.Date.valueOf(today);
			
			String sql = "insert into SchuelerZuKlasse (schuelerID, klassenID, eintragsdatum) " +
					"values (" + schuelerID + ", " + klassenID + ", \"" + sqlLd + "\");";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, schuelerID);
			stmt.setString(2, klassenID);
			stmt.setString(3, eintragsdatum);
			stmt.executeUpdate(sql);
			stmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static void SchuelerZuKlasseWriter(Connection c, String schuelerZuKlasse) {
		try { 
			File f = new File(schuelerZuKlasse);
			FileWriter fw = new FileWriter(f);

			Statement stmt = c.createStatement();
			String sql = "select schuelerID, klassenID, eintragsdatum from schuelerZuKlasse;";
			ResultSet rs = stmt.executeQuery(sql);
			int id = 1;	//autoincrement

			while (rs.next()) {
				int si = rs.getInt("schuelerID");
				int ki = rs.getInt("klassenID");
				Date ed = rs.getDate("eintragsdatum");
				
				String csv = id + ", " + si + ", " + ki + ", " + ed;
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



