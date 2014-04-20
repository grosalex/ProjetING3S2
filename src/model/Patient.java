package model;

public class Patient extends Personne {
	private String mutuelle;
	public Patient(String nom, String prenom, String telephone, String adresse, String mut) {
		super(nom, prenom, telephone, adresse);
		this.mutuelle = mut;
	}

}
