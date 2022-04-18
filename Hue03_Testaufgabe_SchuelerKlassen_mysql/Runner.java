package Hue03_Testaufgabe_SchuelerKlassen_mysql;

import java.sql.*;
import java.time.LocalDate;

import Hue05_csvEinlesen_SchuelerKlassen.Schueler;


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
				String url = "jdbc:mysql://localhost:3306/infi_test_03_schuelerklasse";
				String user = "julian";
				String password = "220705";

				Connection c = createConnection(url, user, password);	
				
				System.out.println("--Connection erstellen--");
				System.out.println("Erfolgreich");
				System.out.println();
				c.setAutoCommit(true);

				System.out.println("--doppelte Tables gelöscht--");
				SchuelerZuKlasse.dropTableSchuelerZuKlasse(c);			//wegen den Löschanomalien
				Hue03_Testaufgabe_SchuelerKlassen_mysql.Schueler.dropTableSchueler(c);
				Klasse.dropTableKlasse(c);
				System.out.println("Erfolgreich");
				System.out.println();
				
				System.out.println("--Tables erstellen--");
				Klasse.createtableKlasse(c);
				Hue03_Testaufgabe_SchuelerKlassen_mysql.Schueler.createtableSchueler(c);
				SchuelerZuKlasse.createtableSchuelerZuKlasse(c);
				System.out.println("Erfolgreich");
				System.out.println();
				
				System.out.println("--Tables inserten--");
				Hue03_Testaufgabe_SchuelerKlassen_mysql.Schueler.insertintotableSchueler(c, "Livio", "Novak", "linovak@tsn.ratte");
				Hue03_Testaufgabe_SchuelerKlassen_mysql.Schueler.insertintotableSchueler(c, "Sepp", "Walter", "sewalter@tsn.apfel");
				Hue03_Testaufgabe_SchuelerKlassen_mysql.Schueler.insertintotableSchueler(c, "Marc", "Suckerbörg", "marc@tsn.börg");
				
				Klasse.insertintotableKlasse(c, 1, 25, "God Jumbo", "Wirtschaft");
				Klasse.insertintotableKlasse(c, 2, 16, "Hans Mayer", "Maschinenbau");
				Klasse.insertintotableKlasse(c, 3, 21, "Sepp Sonne", "Biomedizin");
				
				SchuelerZuKlasse.insertintotableSchuelerZuKlasse(c, 1, 1);
				SchuelerZuKlasse.insertintotableSchuelerZuKlasse(c, 2, 2);
				SchuelerZuKlasse.insertintotableSchuelerZuKlasse(c, 3, 3);
				System.out.println("Erfolgreich");
				System.out.println();


				c.close();


			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

