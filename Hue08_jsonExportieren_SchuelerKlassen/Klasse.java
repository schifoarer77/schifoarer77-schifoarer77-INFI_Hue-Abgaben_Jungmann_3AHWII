package Hue08_jsonExportieren_SchuelerKlassen;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;


public class Klasse extends Writer{


	@SuppressWarnings("unchecked")
	public static void KlassenWriter(Connection c, String file) {
		try { 
			FileWriter fw = new FileWriter(file);
			JSONObject json = new JSONObject();

			Statement stmt = c.createStatement();
			String sql = "select klassenNr, klassenvorstand, schuelerAnzahl, abteilung from klasse;";
			ResultSet rs = stmt.executeQuery(sql);
			int id = 1;	//autoincrement
			String s = "";

			while (rs.next()) {
				int klassenNr = rs.getInt("klassenNr");
				String klassenvorstand = rs.getString("klassenvorstand");
				int schuelerAnzahl = rs.getInt("schuelerAnzahl");
				String abteilung = rs.getString("abteilung");

				json.put("klassenNr", klassenNr);
				json.put("klassenvorstand", klassenvorstand);
				json.put("schuelerAnzahl", schuelerAnzahl);
				json.put("abteilung", abteilung);

				id++;
				s = s + json;
			}

			fw.write(s);
			fw.flush();
			fw.close();
			rs.close();
			stmt.close();
			System.out.println("Write klasse...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}   catch (IOException e) {
			e.printStackTrace();
		}
	}
}
