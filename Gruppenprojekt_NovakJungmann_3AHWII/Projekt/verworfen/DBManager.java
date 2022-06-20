package Projekt.verworfen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBManager {
	private Connection conn;
	
	public DBManager(String url, String user, String password) {
		try {
			this.conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void insertIntoUser(User user) throws SQLException {
		String sql = "INSERT INTO user VALUES(?,?,?,?);";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, user.getEmail());
		preStmt.setString(2, user.getUsername());
		preStmt.setString(3, user.getPassword());
		preStmt.setDate(4, java.sql.Date.valueOf(user.getBirthdate()));
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	
	public void insertIntoClassifieds(Classifieds clf) throws SQLException {
		String sql = "INSERT INTO user VALUES(?,?,?,?);";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setInt(1, clf.getId());
		preStmt.setString(2, clf.getTitle());
		preStmt.setDouble(3, clf.getPrice());
		preStmt.setString(4, clf.getDescription());
		preStmt.setString(5, clf.getCreatorEmail());
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	
	public void insertIntoClassifiedsToUser(String userEmail, int classifiedID) throws SQLException {
		String sql = "INSERT INTO classifiedsToUser VALUES(?,?,?)";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, userEmail);
		preStmt.setInt(2, classifiedID);
		preStmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	
	
	
	//TODO lass dir des nommal durch den kopf gian
	public ArrayList<User> selectUsers() throws SQLException {
		String sql = "SELECT * FROM user;";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<User> allUser = new ArrayList<>();
		while(rs.next()) {
			String email = rs.getString("email");
			String username = rs.getString("username");
			String password = rs.getString("password");
			LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
			allUser.add(new User(email, username, password, birthdate));
		}
		rs.close();
		stmt.close();
		
		return allUser;
		//WENN ALS ARRAY: return (User[]) allUser.toArray();
	}
	
	
	public ArrayList<Classifieds> selectClassifieds() throws SQLException {
		String sql = "SELECT * FROM classified;";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Classifieds> allClassifieds = new ArrayList<>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			double price = rs.getDouble("price");
			String description = rs.getString("description");
			String creatorEmail = rs.getString("creatoremail");
			allClassifieds.add(new Classifieds(id, title, price, description, creatorEmail));
		}
		rs.close();
		stmt.close();
		
		return allClassifieds;
		//WENN ALS ARRAY: return (User[]) allUser.toArray();
	}
	
	
	public ArrayList<ClassifiedsToUser> selectClassifiedsToUser() throws SQLException {
		String sql = "SELECT * FROM classifiedToUser;";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<ClassifiedsToUser> allClassifiedsToUser = new ArrayList<>();
		while(rs.next()) {
			String userEmail = rs.getString("userEmail");
			int classifiedId = rs.getInt("classifiedId");
			allClassifiedsToUser.add(new ClassifiedsToUser(userEmail, classifiedId));
		}
		rs.close();
		stmt.close();
		
		return allClassifiedsToUser;
	}
	
	
	
	
	
	public void updateUser(User user) throws SQLException {
		String sql = "UPDATE user SET username=?, password=?, birthdate=? WHERE email=?);";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, user.getUsername());
		preStmt.setString(2, user.getPassword());
		preStmt.setDate(3, java.sql.Date.valueOf(user.getBirthdate()));
		preStmt.setString(4, user.getEmail());
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	
	public void updateClassifieds(Classifieds clf) throws SQLException {
		String sql = "INSERT INTO user VALUES(?,?,?,?);";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setInt(1, clf.getId());
		preStmt.setString(2, clf.getTitle());
		preStmt.setDouble(3, clf.getPrice());
		preStmt.setString(4, clf.getDescription());
		preStmt.setString(5, clf.getCreatorEmail());
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
//	public User getUser() {
//		Statement
//	}
	
	
//	public static void insertInto(Connection conn, String tableName, String[] content) {
//		try {
//			String sql;
//			if(tableName.equals("User")) sql = String.format("INSERT INTO %s VALUES(?,?,?,?);", tableName);
//			else sql = String.format("INSERT INTO %s VALUES(?,?,?,?);", tableName);
//			for (int i = 0; i < columns.length; i++) {
//				sql += "?";
//				if(i < columns.length - 1) sql += ", ";
//			}
//			sql += ");"
//			
//			PreparedStatement preStmt = conn.prepareStatement(sql);
//			
//			if(tableName.equals("User")) {
//				preStmt.setInt(1, 1);
//				preStmt.setInt(2, 1);
//				preStmt.setInt(3, 1);
//				preStmt.setInt(4, 1);
//			}
//			
//				//columns[i][1] -> Datentyp
//				if(columns[i][1].equals("INT")) preStmt.setInt((i + 1), Integer.parseInt(contents[i]));
//				else if(columns[i][1].equals("DOUBLE")) preStmt.setDouble((i + 1), Double.parseDouble(contents[i]));
//				else if(columns[i][1].equals("DATE")) {
//					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//					LocalDate datum = LocalDate.parse(contents[i], formatter);
//					preStmt.setDate((i + 1), java.sql.Date.valueOf(datum));
//				}
//				else { //VARCHAR
//					if(contents[i].length() > Integer.parseInt(columns[i][2])) {	//alter Table wenn contents > laenge des VARCHAR()
//						columns[i][2] = String.format("%d", contents[i].length());
//
//						Statement stmt = conn.createStatement();
//						String alterSql = String.format("ALTER TABLE %s MODIFY %s VARCHAR(%d);", tableName, columns[i][0], contents[i].length());
//
//						stmt.executeUpdate(alterSql);
//						stmt.close();
//					}
//					preStmt.setString((i + 1), contents[i]);
//				}
//			}
//			preStmt.executeUpdate();
//			preStmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public static void insertIntoBeitragZuUser(Connection conn, String user, int beitrag) {
//		
//	}
//	
//	
//	public static String[] select(Connection conn) {
//		try {
//			String[] s = new String[3];
//			String sql = "SELECT * FROM BeitragZuUser;";
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			for(int i = 0; rs.next(); i++) {
//				
//			}
//			return s;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
