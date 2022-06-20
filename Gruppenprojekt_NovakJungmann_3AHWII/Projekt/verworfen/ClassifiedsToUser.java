package Projekt.verworfen;

import java.time.LocalDate;

public class ClassifiedsToUser {
	private String userEmail;
	private int classifiedsId;
	private LocalDate connectDate;
	
	
	public ClassifiedsToUser(String userEmail, int classifiedsId) {
		super();
		this.userEmail = userEmail;
		this.classifiedsId = classifiedsId;
	}


	public LocalDate getConnectDate() {
		return connectDate;
	}

	public void setConnectDate(LocalDate connectDate) {
		this.connectDate = connectDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public int getClassifiedsId() {
		return classifiedsId;
	}
}
