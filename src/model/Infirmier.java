package model;

// Objet Infirmier héritant de Personne
public class Infirmier extends Personne {
private String rotation;
private int code_Service;
private float salaire;
public Infirmier(int id, String nom,String prenom,String telephone,String adresse,String rotation, int code_Service,float salaire){
	super (id,nom,prenom,telephone,adresse);
	this.rotation=rotation;
	this.code_Service=code_Service;
	this.salaire=salaire;
	}
// Getter
public String getRotation(){
	return rotation;
	}
public int getCode_Service(){
	return code_Service;
	}
public float getSalaire(){
	return salaire;
	}
// Setter
public void setRotation(String rotation){
	this.rotation=rotation;
	}
public void setCode_Service(int code_Service){
	this.code_Service=code_Service;
	}
public void setSalaire(float salaire){
	this.salaire=salaire;
	}
}