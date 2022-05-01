package Hue08_jsonExportieren_SchuelerKlassen;

import java.sql.*;


public class Writer {

	public static Connection createConnection(String url, String user, String password) {
		try {
			return DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();	 
		}
		return null;
	}


	public static void main(String args[]) {

		try {
			String url = "jdbc:mysql://localhost:3306/infi_hue_08_jsonexport_schuelerklassen";
			String user = "julian";
			String password = "220705";

			Connection c = createConnection(url, user, password);

	
			Klasse.KlassenWriter(c, "C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\jsonExport\\klasse.txt");
			Schueler.SchuelerWriter(c, "C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\jsonExport\\schueler.txt");
			SchuelerZuKlasse.SchuelerZuKlasseWriter(c, "C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\jsonExport\\schuelerZuKlasse.txt");			
			
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException");
		}
	}
}

