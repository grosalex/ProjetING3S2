package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import connexion.Connexion;
/**
 * 
 * @author Marques William , Bruneau Alexandre , Bao Huanley , Bertrand Kevin
 *
 */
public class Drop {
	/////////////////////////////////////////////////////////////////////////////////////////////
	// Cette partie concerne uniquement les malades , et les hospitalisations
	// Un malade ne peut etre hospitalisé 
	// l'inverse l'est également.
	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Methode qui supprime une ligne de la table malade 
	 * @param numero
	 * @throws SQLException
	 */
	public static void dropMalade(int numero) throws SQLException {
		PreparedStatement preparedStatement=null;
		String dropSQL="DELETE FROM malade WHERE numero="+numero+"";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(dropSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.executeQuery();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * Methode qui supprime une hospitalisation, mais aussi un malade 
	 * puique un malade non hospitalisé n'est plus un malade
	 * @param no_malade
	 * @param code_service
	 * @param no_chambre
	 * @param lit
	 * @throws SQLException
	 */
	public static void dropHop(Hospitalisation hospitalisation)throws SQLException {

		PreparedStatement preparedStatement=null;
		String updateSQL= "UPDATE chambre SET lits_dispos=lits_dispos+1 WHERE no_chambre="+hospitalisation.getChambre()+"";
		String dropSQL="DELETE FROM hospitalisation WHERE no_malade="+hospitalisation.getPatient().getID()+" ";
		try{
			Connexion.getInstance().executeUpdate(dropSQL);
			Connexion.getInstance().executeUpdate(updateSQL);
		}catch (SQLException e){
			e.printStackTrace();
		}
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// A partir d'ici cela concerne le staff de l'hopital , 
	// à savoir les docteur, infirmier et le personnel
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Methode qui supprimer un employe dans la table employe
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param telephone
	 * @throws SQLException
	 */
	public static void dropEmploye(int numero) throws SQLException {
		PreparedStatement preparedStatement=null;
		String dropSQL="DELETE FROM employe WHERE numero="+numero+"";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(dropSQL, Statement.RETURN_GENERATED_KEYS); //
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * Methode qui permet de supprimer un infirmier qui va etre supprimer aussi dans la table employee 
	 * @param no_malade
	 * @param code_service
	 * @param no_chambre
	 * @param lit
	 * @throws SQLException
	 */
	public static void dropInf(int numero)throws SQLException {
		dropEmploye(numero);
		PreparedStatement preparedStatement=null;
		String dropSQL="DELETE FROM infirmier WHERE numero="+numero+"";

		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(dropSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.executeQuery();
		}catch (SQLException e){
			e.printStackTrace();
		}
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	public static void dropPersonnel(int numero)throws SQLException {
		dropEmploye(numero);
		PreparedStatement preparedStatement=null;
		String dropSQL="DELETE FROM personnel WHERE numero="+numero+"";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(dropSQL, Statement.RETURN_GENERATED_KEYS); //
		}catch (SQLException e){
			e.printStackTrace();
		}
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	public static void dropDoctor(int numero)throws SQLException {
		dropEmploye(numero);
		PreparedStatement preparedStatement=null;
		String dropSQL="DELETE FROM doctor WHERE numero="+numero+"";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(dropSQL, Statement.RETURN_GENERATED_KEYS); //
		}catch (SQLException e){
			e.printStackTrace();
		}
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
}
