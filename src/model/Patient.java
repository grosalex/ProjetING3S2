package model;

public class Patient extends Personne {
	private int id;
	private String mutuelle;
	
	public int getId() {
		return id;
	}

	public String getMutuelle() {
		return mutuelle;
	}

	public Patient(String nom, String prenom, String telephone, String adresse,
			int id, String mutuelle) {
		super(nom, prenom, telephone, adresse);
		this.id = id;
		this.mutuelle = mutuelle;
	}

}
