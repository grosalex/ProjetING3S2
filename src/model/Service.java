package model;

public class Service {
	private String nom;
	private String batiment;
	private int doc_id;
	private String code;
	public Service(String nom, String batiment, int doc_id,String code) {
		this.nom = nom;
		this.batiment = batiment;
		this.doc_id = doc_id;
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getNom() {
		return nom;
	}
	public String getBatiment() {
		return batiment;
	}
	public int getDirecteur() {
		return doc_id;
	}
}
