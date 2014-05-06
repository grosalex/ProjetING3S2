package model;

// Objet Personne 
public class Personne {
private String nom;
private String prenom;
private String telephone;
private String adresse;
private int ID;
public Personne(String nom,String prenom,String telephone,String adresse ){
	this.nom=nom;
	this.prenom=prenom;
	this.telephone=telephone;
	this.adresse=adresse;
}
// sert � lire le contenu
public String getNom(){
	return nom;
}
public String getPrenom(){
	return prenom;
}
public String getTelephone(){
	return telephone;
}
public String getAdresse(){
	return adresse;
}
public int getID(){
	return ID;
}
// modifier la valeur du contenu
public void setNom(String nom){
	this.nom=nom;
}
public void setPrenom(String prenom){
	this.prenom=prenom;
}
public void setAdresse(String adresse){
	this.adresse=adresse;
}
public void setTelephone(String telephone){
	this.telephone=telephone;
}
public void setID(int ID){
	this.ID=ID;
}

}
