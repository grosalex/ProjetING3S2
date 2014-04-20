package model;

// Objet doctor héritant de Personne
public class Doctor extends Personne {
private String specialite;
public Doctor(String nom,String prenom,String telephone,String adresse, String specialite){
	super (nom,prenom,telephone,adresse);
	this.specialite=specialite;
}
// Getter
public String getSpecialite(){
	return specialite;
}
//Setter
public void setSpecialite(String specialite){
	this.specialite=specialite;
}
}
