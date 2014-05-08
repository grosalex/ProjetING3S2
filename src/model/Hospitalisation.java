package model;

public class Hospitalisation {
	
	private int code_service;
	private Chambre chambre;
	private Patient patient;
	private int lit;
	
	public Hospitalisation(int code_service, Chambre chambre, Patient patient, int lit) {
		this.code_service = code_service;
		this.chambre = chambre;
		this.patient = patient;
		this.lit = lit;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public Patient getPatient() {
		return patient;
	}

	public int getLit() {
		return lit;
	}



	public int getCode_service() {
		return code_service;
	}

}
