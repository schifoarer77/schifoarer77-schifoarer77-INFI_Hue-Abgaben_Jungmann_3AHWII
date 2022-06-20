package Gruppenprojekt_NovakJungmann_3AHWII.Projekt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

public class Runner {
	
	public static Connection getConnection(String url, String user, String password)  {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void add(Connection conn, Scanner in) {
		boolean completeProcess = false;
		while(!completeProcess) {
			System.out.print("ADD>Insert destination-table [user, classifieds]: ");
			String tableName = in.next().toLowerCase().trim();
			if(!tableName.equals("exit")) {
				if(tableName.equals("user") || tableName.equals("classifieds")) {
					System.out.print("ADD>Insert Path of the csv-file [path]: ");
					String path = in.next().toLowerCase().trim();
					if(!path.equals("exit")) {
						System.out.print("ADD>Insert the Filename [filename]: ");
						String fileName = in.next().toLowerCase().trim();
						if(!fileName.equals("exit")) {
							try {
								CSV_Import.reader(conn, tableName, String.format("%s\\%s.csv", path, fileName));
								System.out.println("action performed\n");
								completeProcess = true;
							} catch (FileNotFoundException e) {
								System.out.println("ERROR! unknown path or filename. Please check your inputs due to mistakes and type \"help\" if neccessary\n");
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("ERROR! Something went wrong with the content of the csv-file. Type \"help\" if neccessary\n");
							} 
						}
						else {
							System.out.println("action aborted\n");
							completeProcess = false;
						}
					}
					else {
						System.out.println("action aborted\n");
						completeProcess = true;
					}
				}
				else System.out.println("Invalid table. Only \"User\" and \"Classifieds\" are allowed\n");
			}
			else {
				System.out.println("action aborted\n");
				completeProcess = true;
			}
		}
	}

	
	public static void show(Connection conn, Scanner in) {
		boolean completeProcess = false;
		while(!completeProcess) {
			System.out.print("SHOW>which table [user, classifieds, classifiedsToUser]: ");
			String tableName = in.next().trim().toLowerCase();
			if(!tableName.equals("exit")) {
				try {
					if(tableName.equals("user")) {
						System.out.println("---------------");
						String sql = "CALL selectUser;";
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("Email;\tUsername;\tPassword;\tBirthdate\n");
						while(rs.next()) {
							String email = rs.getString("email");
							String username = rs.getString("username");
							String password = rs.getString("password");
							LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
							System.out.printf("%s;\t%s;\t%s;\t%s\n", email, username, password, birthdate);
						}
					}
					else if(tableName.equals("classifieds")) {
						System.out.println("---------------");
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("CALL selectClassifieds;");
						System.out.println("id;\ttitle;\tprice;\tdescription;\tcreatoremail\n");
						while(rs.next()) {
							int id = rs.getInt("id");
							String title = rs.getString("title");
							double price = rs.getDouble("price");
							String description = rs.getString("description");
							String creatorEmail = rs.getString("creatoremail");
							System.out.printf("%d;\t%s;\t%f;\t%s;\t%s\n", id, title, price, description, creatorEmail);
						}
					}
					else if(tableName.equals("classifiedstouser")) {
						System.out.println("---------------");
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("CALL selectConnection;");
						System.out.println("userEmail;\tclassifiedsId;\tconnect_timestamp\n");
						while(rs.next()) {
							String userEmail = rs.getString("useremail");
							int classifiedsId = rs.getInt("classifiedsId");
							Timestamp connect_timestamp = rs.getTimestamp(("connect_timestamp"));
							System.out.printf("%s;\t%d;\t%s\n", userEmail, classifiedsId, connect_timestamp.toString());
						}
					}
					System.out.println("---------------\naction performed\n");
					completeProcess = true;
				} catch (SQLException e) {
//					theoretisch sollte es nie soweit kommen, da nur tabellennamen angegeben werden & diese überprüft werden
					e.printStackTrace();
					System.out.println("ERROR. Something went wrong. Type \"help\" if neccessary\n");
				}
			}
			else {
				System.out.println("action aborted\n");
				completeProcess = true;
			}
		}
	}
	
	
	public static void connect(Connection conn, Scanner in) {
		boolean completeProcess = false;
		while(!completeProcess) {
			System.out.print("CONNECT>Which user (email) [email]: ");
			String userEmail = in.next().toLowerCase().trim();
			if(!userEmail.equals("exit")) {
				System.out.print("CONNECT>Which article (id) [number]: ");
				String classifieds = in.next().toLowerCase().trim();
				if(!classifieds.equals("exit")) {
					try {
						int classifiedsId = Integer.parseInt(classifieds);
						
						String sql = "CALL connect(?,?,?);";
						PreparedStatement preStmt = conn.prepareStatement(sql);
						preStmt.setString(1, userEmail);
						preStmt.setInt(2, classifiedsId);
						preStmt.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
						
						preStmt.executeUpdate();
						preStmt.close();
						
						System.out.println("Process successful\n");
						completeProcess = true;
					}catch(SQLException e) {
						System.out.println("ERROR! Invalid inputs\n");
					}
				}
				else {
					System.out.println("action aborted\n");
					completeProcess = true;
				}
			}
			else {
				System.out.println("action aborted\n");
				completeProcess = true;
			}
		}
	}
	
	
	public static void get(Connection conn, Scanner in) {
		boolean completeProcess = false;
		while(!completeProcess) {
			System.out.print("GET>Insert destination-folder [path]: ");
			String path = in.next().toLowerCase().trim();
			if(!path.equals("exit")) {
				System.out.print("GET>Insert a name for JSON-File [filename]: ");
				String fileName = in.next().toLowerCase().trim();
				if(!fileName.equals("exit")) {
					try {
						JSON_Export.write(conn, String.format("%s\\%s.json", path, fileName));
						System.out.println("action performed\n");
						completeProcess = true;
					} catch (IOException e) {
						System.out.println("ERROR with writing the file. Please check your inputs!\n");
					} 
				}
				else {
					System.out.println("action aborted\n");
					completeProcess = true;
				}
			}
			else {
				System.out.println("action aborted\n");
				completeProcess = true;
			}
		}
	}
	
	
	
	//TEST-PFAD: C:\Users\linov\Desktop
	public static void main(String[] args) {
		try {
			String configFilePath = "C:\\Users\\julia\\Desktop\\INFI\\Projekt (1)\\Projekt\\config.properties";
			FileInputStream propsInput = new FileInputStream(configFilePath);
			Properties prop = new Properties();
			prop.load(propsInput); 

			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");

			Connection conn = getConnection(url, user, password);
			System.out.println("Connection erfolgreich\n");
			conn.setAutoCommit(true);
			
			//TODO helpStr noch befüllen
			String helpStr = "ADD -> inserts or updates the tables (of the database) with a CSV-file.\r\n" + 
					"SHOW -> by specifying which table you get all the table-data in the Console.\r\n" + 
					"CONNECT -> connects a user with a classified ad. The timestamp will also be saved.\r\n" + 
					"GET -> Writes all the data of the User-Classifieds-connection-table in a JSON file. \r\n" +
					"\r\n" +
					"by typing \"exit\" at any time, you can leave the individual sub-actions and also the program itself.\r\n" +
					"for more information, type \"help\".\r\n\r\n";
			
			//TODO ohne den ganzen "+" machen
			System.out.println("Welcome to our INFI-Project called \"keine Ahnung, wir sind offen für Ideen\".\r\n" + 
					"With the following commands you navigate through the programm. Please, have fun with our project :)\r\n" + 
					"\r\n" + helpStr);
			
			
			Scanner in = new Scanner(System.in);
			
			String scnnrInput;
			boolean runProgramm = true;
			while(runProgramm) {
				System.out.print("HOME>");
				if(in.hasNext()) {
					scnnrInput = in.next().toLowerCase().trim();
					switch(scnnrInput) {
					case "add": add(conn, in); break;
					case "show": show(conn, in); break;
					case "connect": connect(conn, in); break;
					case "get": get(conn, in); break;
					case "exit": runProgramm = false; System.out.println("Bye Bye!\nThanks for using our project!\n"); break;
					case "help": System.out.println(helpStr); break;
					default: System.out.println("Invalid input. Type \"help\" to get an overview of all commands\n"); break;
					}
				}
			}
			conn.close();
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
