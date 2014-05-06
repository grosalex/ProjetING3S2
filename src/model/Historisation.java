package model;

import java.sql.SQLException;
import connexion.Connexion;

public class Historisation {
	
	public static void hospitalisation(Connexion c, Hospitalisation h) {
		try {
			c.executeUpdate("UPDATE hospitalisation "
					+ "SET present=FALSE "
					+ "WHERE code_service=" + h.getService().getCode() 
					+" AND no_malade=" + h.getPatient().getID() 
					+" AND no_chambre=" + h.getChambre().getId()
					+" AND lit=" + h.getLit().getId());
		} catch (SQLException e) {
			System.out.println("SQL Exception while historizing");
			e.printStackTrace();
		}
	}
	
	public static void rdv (Connexion c, RendezVous r) {
		try {
			c.executeUpdate("UPDATE rdv "
					+ "SET present=FALSE "
					+ "WHERE date=" + r.getDate() +" AND docteur=" + r.getDocteur().getID() + " AND patient=" + r.getPatient().getID());
		} catch (SQLException e) {
			System.out.println("SQL Exception while historizing");
			e.printStackTrace();
		}
	}

}
