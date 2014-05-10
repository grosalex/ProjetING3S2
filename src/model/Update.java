package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.Connexion;

/**
 * Mise a jour de diverses donnees
 * @author BERTRAND , BRUNEAU , BAO , MARQUES
 *
 */
public class Update {

	/**
	 * Mise a jour d'un employe (doc ou inf)
	 * @param newP Personne contenant les nouvelles donnees
	 * @throws SQLException En cas de pb SQL/de Connexion
	 */
	public static void Employee(Personne newP) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL="";
		insertSQL= "update employe set nom=?, prenom=?, tel=?,adresse=? "
				+ "WHERE numero=?";
		System.out.println("telephone in update :"+newP.getTelephone());
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, newP.getNom());
			preparedStatement.setString(2, newP.getPrenom());
			preparedStatement.setString(3, newP.getTelephone());
			preparedStatement.setString(4, newP.getAdresse());
			preparedStatement.setInt(5, newP.getID());
			
			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();
			//personne.setID(id); // on a l'ID de la table personne , � v�rifier.

		}catch (SQLException e){
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	
	/**
	 * Mise a jour d'un docteur
	 * @param d Docteur contenant les nouvelles donnees
	 * @throws SQLException En cas de pb SQL/de Connexion
	 */
	public static void Doctor(Doctor d) throws SQLException {
		Employee(d);
		PreparedStatement preparedStatement=null;
		String insertSQL="";
		insertSQL= "update docteur set specialite=? "
				+ "WHERE numero=?";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, d.getSpecialite());
			preparedStatement.setInt(2, d.getID());

			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();
			//personne.setID(id); // on a l'ID de la table personne , � v�rifier.

		}catch (SQLException e){
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	
	/**
	 * Mise a jour d'un infirmier
	 * @param n Infirmier contenant les nouvelles donnees
	 * @throws SQLException En cas de pb SQL/de Connexion
	 */
	public static void Nurse(Infirmier n) throws SQLException {
		Employee(n);
		PreparedStatement preparedStatement=null;
		String insertSQL="";
		insertSQL= "update infirmier set code_service=?, rotation=?, salaire=? "
				+ "WHERE numero=?";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setInt(1, n.getCode_Service());
			preparedStatement.setString(2, n.getRotation());
			preparedStatement.setFloat(3, n.getSalaire());
			preparedStatement.setInt(4, n.getID());
			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();
			//personne.setID(id); // on a l'ID de la table personne , � v�rifier.

		}catch (SQLException e){
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	
	/**
	 * Mise a jour d'un patient
	 * @param p Patient contenant les nouvelles donnees
	 * @throws SQLException En cas de pb SQL/de Connexion
	 */
	public static void Patient(Patient p) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL="";
		insertSQL= "update malade set nom=?, prenom=?,"
				+ "adresse=?, tel=?, mutuelle=? "
				+ "WHERE numero=?";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, p.getPrenom());
			preparedStatement.setString(2, p.getPrenom());
			preparedStatement.setString(3, p.getAdresse());
			preparedStatement.setString(4, p.getTelephone());
			preparedStatement.setString(5, p.getMutuelle());
			preparedStatement.setInt(6, p.getID());
			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();
			//personne.setID(id); // on a l'ID de la table personne , � v�rifier.

		}catch (SQLException e){
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	
	/**
	 * Mise a jour d'un service
	 * @param s Service contenant les nouvelles donnees
	 * @throws SQLException En cas de pb SQL/de Connexion
	 */
	public static void Service(Service s) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL="";
		insertSQL= "update service set"
				+ "batiment=?, directeur=? "
				+ "WHERE code=?";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, s.getBatiment());
			preparedStatement.setInt(2, s.getDirecteur());
			preparedStatement.setString(3, s.getCode());
			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();
			//personne.setID(id); // on a l'ID de la table personne , � v�rifier.

		}catch (SQLException e){
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	
	/**
	 * Mise a jour d'une chambre
	 * @param c Chambre contenant les nouvelles donnees
	 * @throws SQLException En cas de pb SQL/de Connexion
	 */
	public static void Chambre(Chambre c) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL="";
		insertSQL= "update chambre set surveillant=?, nb_lits=?,"
				+ "lits_dispos=?"
				+ "WHERE code_service=? AND no_chambre=?";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setInt(1, c.getId_surveillant());
			preparedStatement.setInt(2, c.getNb_lits());
			preparedStatement.setInt(3, c.getLits_dispos());
			preparedStatement.setString(4, c.getService());
			preparedStatement.setInt(5, c.getId());
			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();
			//personne.setID(id); // on a l'ID de la table personne , � v�rifier.

		}catch (SQLException e){
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
}


