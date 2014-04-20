package model;

// Objet personnel héritant de personne
public class Personnel extends Personne {
	private String metier;
	public Personnel(String nom,String prenom,String telephone,String adresse, String metier){
		super (nom,prenom,telephone,adresse);
		this.metier=metier;
	}
	// Getter
	public String getMetier(){
		return metier;
	}
	// Setter
	public void setMetier(String metier){
		this.metier=metier;
	}
	}
