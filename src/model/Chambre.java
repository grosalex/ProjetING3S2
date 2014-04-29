package model;

import java.util.ArrayList;

public class Chambre {

	private Service service;
	private Infirmier surveillant;
	private ArrayList<Lit> lits;
	private int id;
	public Chambre(Service service, Infirmier surveillant, ArrayList<Lit> lits) {
		this.service = service;
		this.surveillant = surveillant;
		this.lits = lits;
	}
	public int getId() {
		return id;
	}
	

}
