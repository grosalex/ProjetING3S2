package model;

public class Hospitalisation {
	
	private String code_service;
	private int id_chambre;
	private Patient patient;
	private int lit;
	
	public Hospitalisation(String code_service, int id_chambre, Patient patient, int lit) {
		this.code_service = code_service;
		this.id_chambre = id_chambre;
		this.patient = patient;
		this.lit = lit;
	}

	public Hospitalisation(int id_chambre, Patient patient) {
		this.id_chambre = id_chambre;
		this.patient = patient;
	}

	public int getChambre() {
		return id_chambre;
	}

	public Patient getPatient() {
		return patient;
	}

	public int getLit() {
		return lit;
	}



	public String getCode_service() {
		return code_service;
	}

}
