package Projekt.verworfen;

public class Classifieds {
	private int id;
	private String title;
	private double price;
	private String description;
	private String creatorEmail;
	
	
	public Classifieds(int id, String title, double price, String description, String creatorEmail) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.creatorEmail = creatorEmail;
	}
	
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public String getCreatorEmail() {
		return creatorEmail;
	}


	@Override
	public boolean equals(Object obj) {
		Classifieds clf2 = (Classifieds) obj;
		return id == clf2.id;
	}
	
	@Override
	public String toString() {
		return String.format("ID=%d\nTITLE=%s\nPRICE=%f\nDESCRIPTION=%s\nCREATOREMAIL=%s\n\n", id, title, price, description, creatorEmail);
	}
}
