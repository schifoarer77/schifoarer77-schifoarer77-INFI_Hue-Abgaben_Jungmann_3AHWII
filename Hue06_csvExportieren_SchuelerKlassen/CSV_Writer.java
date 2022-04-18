package Hue06_csvExportieren_SchuelerKlassen;


import java.sql.*;


public class CSV_Writer {

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
			String url = "jdbc:mysql://localhost:3306/infi_hue_06_csvfileexport_schuelerklassen";
			String user = "julian";
			String password = "220705";

			Connection c = createConnection(url, user, password);

			SchuelerZuKlasse.dropTableSchuelerZuKlasse(c);
			Klasse.dropTableKlasse(c);
			Schueler.dropTableSchueler(c);
			
			Klasse.createtableKlasse(c);
			Schueler.createtableSchueler(c);
			SchuelerZuKlasse.createtableSchuelerZuKlasse(c);
			
			Klasse.insertintotableKlasse(c, "1", "2", "24", "3AHWII");
			Klasse.insertintotableKlasse(c, "2", "4", "38", "4BHMT");

			Schueler.insertintotableSchueler(c, "1", "Hans", "Peter", "hp@gmail.at");
			Schueler.insertintotableSchueler(c, "2", "Martin", "Sepp", "ms@gmail.at");

			SchuelerZuKlasse.insertintotableSchuelerZuKlasse(c, "1", "2", "2020-07-13");
			SchuelerZuKlasse.insertintotableSchuelerZuKlasse(c, "2", "4", "2021-12-26");

			
			Klasse.KlassenWriter(c, "C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\csvExport\\klasse.csv");
			Schueler.SchuelerWriter(c, "C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\csvExport\\schueler.csv");
			SchuelerZuKlasse.SchuelerZuKlasseWriter(c, "C:\\Users\\julia\\Desktop\\INFI\\Hausuebung\\csvExport\\schuelerZuKlasse.csv");			
			
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException");
		}
	}
}

