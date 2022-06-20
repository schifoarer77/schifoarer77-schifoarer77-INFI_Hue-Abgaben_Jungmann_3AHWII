package Gruppenprojekt_NovakJungmann_3AHWII.Projekt;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;

public class JSON_Export {
	static void write(Connection conn, String path) throws IOException {
		try {
			FileWriter writer = new FileWriter(new File(path));
			JSONObject json = new JSONObject();
//			String fileContent = "";

			Statement stmt = conn.createStatement();
			String sql = "CALL selectConnection;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				json.put("userEmail", rs.getString("userEmail"));
				json.put("classifiedsId", rs.getInt("classifiedsId"));
				json.put("connect_timestamp", rs.getTimestamp("connect_timestamp"));
				writer.write(json.toString());
				writer.write("\r\n");
				writer.flush();
			}
//			writer.write(fileContent);
//			writer.flush();
			writer.close();

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}