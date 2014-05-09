package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.Connexion;

public class Historisation {

	public static void hospitalisation(Hospitalisation h) {
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO hospitalisation_old"
				+ "(no_malade,code_service,no_chambre,lit) VALUES"
				+ "(?,?,?,?)";
		try {
			
			Connexion.getInstance().executeUpdate("UPDATE hospitalisation SET nb_lits_dispo=nb_lits_dispo+1 WHERE no_chambre="+h.getChambre());
			Connexion.getInstance().executeQuery("CREATE TABLE IF NOT EXISTS `hospitalisation_old` ("
					+"`no_malade` int(11) NOT NULL,"
					+"`code_service` char(3) NOT NULL,"
					+"`no_chambre` int(11) NOT NULL,"
					+"`lit` int(11) default NULL,"
					+"PRIMARY KEY  (`no_malade`),"
					+"UNIQUE KEY `code_service` (`code_service`,`no_chambre`,`lit`)"
					+") ENGINE=MyISAM DEFAULT CHARSET=latin1");
			
			Connexion.getInstance().executeUpdate("DELETE FROM hospitalisation"
					+ "WHERE no_malade="+h.getPatient().getID());
			
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, h.getPatient().getID());
			preparedStatement.setString(2, h.getCode_service());
			preparedStatement.setInt(3, h.getChambre());
			preparedStatement.setInt(4, h.getLit());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL Exception while historizing");
			e.printStackTrace();
		}
	}

}
