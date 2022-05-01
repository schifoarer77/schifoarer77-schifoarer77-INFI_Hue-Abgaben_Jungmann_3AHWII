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

import Hue03_Testaufgabe_SchuelerKlassen_mysql.Runner;


public class Schueler extends Writer {

	@SuppressWarnings("unchecked")
	public static void SchuelerWriter(Connection c, String file) {
		try { 
			FileWriter fw = new FileWriter(file);
			JSONObject json = new JSONObject();

			Statement stmt = c.createStatement();
			String sql = "select katalogNR, vorname, nachname, email from schueler;";
			ResultSet rs = stmt.executeQuery(sql);
			int id = 1;	//autoincrement
			String s = "";


			while (rs.next()) {
				int katalogNR = rs.getInt("katalogNR");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				String email = rs.getString("email");

				json.put("katalogNR", katalogNR);
				json.put("vorname", vorname);
				json.put("nachname", nachname);
				json.put("email", email);

				id++;
				s = s + json;
			}

			fw.write(s);
			fw.flush();
			fw.close();
			rs.close();
			stmt.close();
			System.out.println("Write schueler...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}   catch (IOException e) {
			e.printStackTrace();
		}
	}
}



