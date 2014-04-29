package model;

import java.util.Date;

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
	
	public String historise() {
		return "UPDATE hospitalisation "
				+ "SET present=FALSE "
				+ "WHERE code_service=" + service.getCode() +" AND no_malade=" + patient.getID() + " AND no_chambre=" + chambre.getId()
				+ " AND lit=" + lit.getId();
	}

}
