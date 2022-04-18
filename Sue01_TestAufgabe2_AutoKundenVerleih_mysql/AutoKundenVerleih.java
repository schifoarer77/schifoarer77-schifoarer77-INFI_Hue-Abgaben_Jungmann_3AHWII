package Sue01_TestAufgabe2_AutoKundenVerleih_mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class AutoKundenVerleih extends Runner{


	public static void dropTableAutoKundenVerleih(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "drop table if exists AutoKundenVerleih;";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createtableAutoKundenVerleih(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists AutoKundenVerleih" +
					"(kennzeichen VARCHAR(8)," +
					"kundenID INT," +
					"verleihsBeginn DATE," +
					"verleihsEnde DATE NOT NULL," +
					"PRIMARY KEY(kennzeichen, kundenID, verleihsBeginn)," +
					"FOREIGN KEY(kennzeichen) REFERENCES Auto(kennzeichen)," +
					"FOREIGN KEY(kundenID) REFERENCES Kunden(kundenNR));";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void insertintotableAutoKundenVerleih(Connection c, String kennzeichen,  int kundenID, LocalDate verleihsEnde) {					
		try {
			
			String sql = "insert into AutoKundenVerleih (kennzeichen, kundenID, verleihsBeginn, verleihsEnde) values (?, ?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			java.sql.Date sqlBeginn = java.sql.Date.valueOf(LocalDate.now());
			java.sql.Date sqlEnde = java.sql.Date.valueOf(verleihsEnde);
			
			stmt.setString(1, kennzeichen);
			stmt.setInt(2, kundenID);
			stmt.setDate(3, sqlBeginn);
			stmt.setDate(4, sqlEnde);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


