package Hue08_jsonExportieren_SchuelerKlassen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.json.simple.JSONObject;

import java.sql.Date;
import java.sql.PreparedStatement;


public class SchuelerZuKlasse extends Writer{

	@SuppressWarnings("unchecked")
	public static void SchuelerZuKlasseWriter(Connection c, String file) {
		try { 
			FileWriter fw = new FileWriter(file);
			JSONObject json = new JSONObject();

			Statement stmt = c.createStatement();
			String sql = "select schuelerID, klassenID, eintragsdatum from schuelerZuKlasse;";
			ResultSet rs = stmt.executeQuery(sql);
			int id = 1;	//autoincrement
			String s = "";

			while (rs.next()) {
				int schuelerID = rs.getInt("schuelerID");
				int klassenID = rs.getInt("klassenID");
				Date eintragsdatum = rs.getDate("eintragsdatum");
				
				json.put("schuelerID", schuelerID);
				json.put("klassenID", klassenID);
				json.put("eintragsdatum", eintragsdatum);

				id++;
				s = s + json;
			}

			fw.write(s);
			fw.flush();
			fw.close();
			rs.close();
			stmt.close();
			System.out.println("Write schuelerZuKlasse...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}   catch (IOException e) {
			e.printStackTrace();
		}
	}
}



