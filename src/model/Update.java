package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.Connexion;

public class Update {

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
}


