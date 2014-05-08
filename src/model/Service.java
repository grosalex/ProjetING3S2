package model;

public class Service {
	private String nom;
	private String batiment;
	private Doctor directeur;
	private String code;
	public Service(String nom, String batiment, Doctor directeur,String code) {
		this.nom = nom;
		this.batiment = batiment;
		this.directeur = directeur;
		this.code = code;
	}
	public String getCode() {
		return code;
	}
}
