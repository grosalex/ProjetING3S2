package model;

import java.util.Date;

import connexion.Connexion;

public class RendezVous {
	private Date date;
	private Doctor docteur;
	private Patient patient;

	public RendezVous(Date date, Doctor docteur, Patient patient) {
		this.date = date;
		this.docteur = docteur;
		this.patient = patient;
	}
	
	public Date getDate() {
		return date;
	}

	public Doctor getDocteur() {
		return docteur;
	}

	public Patient getPatient() {
		return patient;
	}

	public String historise() {
		return "UPDATE rdv "
				+ "SET present=FALSE "
				+ "WHERE date=" + date +" AND docteur=" + docteur.getID() + " AND patient=" + patient.getID();
	}

}
