package model;

public class Patient extends Personne {
	private String mutuelle;

	public String getMutuelle() {
		return mutuelle;
	}

	public Patient(String nom, String prenom, String telephone, String adresse,
			int id, String mutuelle) {
		super(id,nom, prenom, telephone, adresse);
		this.mutuelle = mutuelle;
	}

	public Patient(int iD) {
		super(iD);
		this.mutuelle = "";
	}

	public Patient(String nom, String prenom, String telephone, String adresse,
			String mutuelle) {
		super(nom, prenom, telephone, adresse);
		this.mutuelle = mutuelle;
	}

}
