package Sue01_TestAufgabe2_AutoKundenVerleih_mysql;

import java.sql.*;
import java.time.LocalDate;


public class Runner {

	public static Connection createConnection(String url, String user, String password) {
		try {
			//Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Datenbank konnte nicht gefunden werden");
			System.exit(1);
			return null; 
		}
		finally {
			System.out.println("+++ Sind im finally +++");
			System.out.println();
		} 
	}


	public static void main(String args[]) {
		try {
			String url = "jdbc:mysql://localhost:3306/infi_sue_01_autoverleih";
			String user = "julian";
			String password = "220705";

			Connection c = createConnection(url, user, password);	

			System.out.println("--Connection erstellen--");
			System.out.println("Erfolgreich");
			System.out.println();
			c.setAutoCommit(true);

			System.out.println("--doppelte Tables gelöscht--");
			AutoKundenVerleih.dropTableAutoKundenVerleih(c);
			Auto.dropTableAuto(c);
			Kunden.dropTableKunden(c);
			System.out.println("Erfolgreich");
			System.out.println();

			System.out.println("--Tables erstellen--");
			Auto.createtableAuto(c);
			Kunden.createtableKunden(c);
			AutoKundenVerleih.createtableAutoKundenVerleih(c);
			System.out.println("Erfolgreich");
			System.out.println();

			System.out.println("--Tables inserten--");
			Auto.insertintotableAuto(c, "IL-73583", "Porsche", "Taycan Turbo S", 299.99, LocalDate.of(2021, 10, 14));
			Auto.insertintotableAuto(c, "I-IBK446", "RollsRoyce", "Cullinan", 699.99, LocalDate.of(2021, 10, 14));
			Kunden.insertintotableKunden(c, "Peter Pan", LocalDate.of(2021, 10, 14), "AT821200094739958546");
			Kunden.insertintotableKunden(c, "Marc Suckerboerg", LocalDate.of(2021, 10, 14), "AT781200088561982252");
			AutoKundenVerleih.insertintotableAutoKundenVerleih(c, "IL-73583", 1, LocalDate.of(2021, 10, 14));
			AutoKundenVerleih.insertintotableAutoKundenVerleih(c, "I-IBK446", 2, LocalDate.of(2020, 11, 23));
			System.out.println("Erfolgreich");
			System.out.println();


			c.close();


		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
