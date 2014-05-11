package model;

public class Chambre {

	private String code_service;
	private int id_surveillant;
	private int nb_lits;
	private int lits_dispos;
	private int id;
	public Chambre(String code_service, int surveillant, int nb_lits) {
		this.code_service = code_service;
		this.id_surveillant = surveillant;
		this.nb_lits = nb_lits;
	}
	public Chambre(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public int getNb_lits() {
		return nb_lits;
	}
	public String getService() {
		return code_service;
	}
	public int getId_surveillant() {
		return id_surveillant;
	}
	public int getLits_dispos() {
		return lits_dispos;
	}
	

}
