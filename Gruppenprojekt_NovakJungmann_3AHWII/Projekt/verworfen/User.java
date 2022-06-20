package Projekt.verworfen;

import java.time.LocalDate;

public class User {
	private String email;
	private String username;
	private String password;
	private LocalDate birthdate;
	
	
	public User(String email, String username, String password, LocalDate birthdate) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.birthdate = birthdate;
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}



	@Override
	public boolean equals(Object obj) {
		User user2 = (User) obj;
		return email.equals(user2.email);
	}
	
	@Override
	public String toString() {
		return String.format("EMAIL=%s\nUSERNAME=%s\nPassword=%s\nBirthdate=%s\n\n", email, username, password, birthdate);
	}
}
