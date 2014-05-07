package model;

public class Hospitalisation {
	
	private Service service;
	private Chambre chambre;
	private Patient patient;
	private Lit lit;
	
	public Hospitalisation(Service service, Chambre chambre, Patient patient) {
		this.service = service;
		this.chambre = chambre;
		this.patient = patient;
	}

	public Service getService() {
		return service;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public Patient getPatient() {
		return patient;
	}

	public Lit getLit() {
		return lit;
	}

}
