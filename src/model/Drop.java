package model;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.Connexion;
public class Drop {
	/**
	 * Methode supprime une personne dans toutes les BD
	 * @param personne
	 * @throws SQLException
	 */
	public static int DropEmploye(Personne personne) throws SQLException {
		PreparedStatement preparedStatement=null;
		int id = 0;
		String dropSQL="";
		dropSQL= "INSERT INTO employe"
				+ "(NOM,PRENOM,TEL,ADRESSE) VALUES"
				+ "(?,?,?,?)";
		

		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(dropSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, personne.getNom());
			preparedStatement.setString(2, personne.getPrenom());
			preparedStatement.setString(3, personne.getTelephone());
			preparedStatement.setString(4, personne.getAdresse());

			// Insertion de la ligne dans la table.
			id = preparedStatement.executeUpdate();
			//personne.setID(id); // on a l'ID de la table personne , � v�rifier.

		}catch (SQLException e){
			// A voir si on lance ou nouvelle exception
			System.out.println(e.getMessage());
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
		
		return id;

	}
}
