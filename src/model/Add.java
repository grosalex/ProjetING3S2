package model;

// =================== Classe d'ajout des differents champs dans la BD ===============
// Produit par Mr Marques William    Chef de Projet
//                Bruneau Alexandre
//                Bertrand Kevin
//                Bao Huanley
//====================================================================================


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import connexion.Connexion;

// creer l'objet personne et docteur avant . 

/**
 * Classe qui regroupe les diffï¿½rentes mï¿½thodes d'ajout
 * @author BERTRAND , BRUNEAU , BAO , MARQUES
 *
 */
public class Add {

	// ce cons ne peut etre appleé

	public Add() {
	}
	/**
	 * Methode qui ajoute les champs d'un docteur dans la BD docteur
	 * @param doctor
	 * @throws SQLException
	 */
	public static void addDoctor(Doctor doctor) throws SQLException {
		int id = addEmploye(doctor);
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO docteur " 
				+ "(numero, specialite) VALUES"
				+ "(?,?)";
		// delete from doctor where ID_personne = ? 
		//
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL);
			preparedStatement.setInt(1, id); 
			preparedStatement.setString(2, doctor.getSpecialite());

			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();

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

	}
	/**
	 * Methode qui ajoute un nouveau patient dans la table patient et dans la table personne
	 * @param patient
	 * @throws SQLException
	 */
	public static void addPatient(Patient patient) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO patient " 
				+ "(MUTUELLE) VALUES"
				+ "(?)";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL);
			preparedStatement.setString(1, patient.getMutuelle());

			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();

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

	}

	/**
	 * Methode qui ajoute les champs d'une infirmiere dans la table infirmiere extends personne
	 * @param infirmier
	 * @throws SQLException
	 */
	public static void addNurse(Infirmier infirmier) throws SQLException {
		int id = addEmploye(infirmier);
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO INFIRMIER " 
				+ "(ID_PERSONNE, CODE_SERVICE,ROTATION,SALAIRE) VALUES"
				+ "(?,?,?,?)";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL);
			preparedStatement.setInt(1, id); 
			preparedStatement.setInt(2, infirmier.getCode_Service());
			preparedStatement.setString(3, infirmier.getRotation());
			preparedStatement.setFloat(4, infirmier.getSalaire());

			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();

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

	}

	/**
	 * Methode qui ajoute les champs d'un membre du personnel dans la table personnel extends personne 
	 * @param personnel
	 * @throws SQLException
	 */
	public static void addPersonnel(Personnel personnel) throws SQLException {
		int id = addEmploye(personnel);
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO PERSONNEL " 
				+ "(ID_PERSONNE, METIER) VALUES"
				+ "(?,?)";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL);
			preparedStatement.setInt(1, id); 
			preparedStatement.setString(2, personnel.getMetier());

			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();

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

	}

	/**
	 * Methode qui ajoute une personne dans la BD personne
	 * @param personne
	 * @throws SQLException
	 */
	public static int addEmploye(Personne personne) throws SQLException {
		PreparedStatement preparedStatement=null;
		int id = 0;
		String insertSQL="";
		insertSQL= "INSERT INTO employe"
				+ "(NOM,PRENOM,TEL,ADRESSE) VALUES"
				+ "(?,?,?,?)";
		

		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, personne.getNom());
			preparedStatement.setString(2, personne.getPrenom());
			preparedStatement.setString(3, personne.getTelephone());
			preparedStatement.setString(4, personne.getAdresse());

			// Insertion de la ligne dans la table.
			id = preparedStatement.executeUpdate();
			//personne.setID(id); // on a l'ID de la table personne , ï¿½ vï¿½rifier.

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
	public static void addPatient(Personne personne) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO MALADE"
				+ "(NOM,PRENOM,TELEPHONE,ADRESSE) VALUES"
				+ "(?,?,?,?)";

		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, personne.getNom());
			preparedStatement.setString(2, personne.getPrenom());
			preparedStatement.setString(3, personne.getTelephone());
			preparedStatement.setString(4, personne.getAdresse());

			// Insertion de la ligne dans la table.
			int id = preparedStatement.executeUpdate();
			personne.setID(id); // on a l'ID de la table personne , ï¿½ vï¿½rifier.

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

	}

	/**
	 * Methode qui renvoie la liste des personnes
	 * @return personnes
	 * @throws SQLException
	 */
	public static /*Object [][]*/ LinkedList<Personne> selectAllPersonne() throws SQLException{
		LinkedList<Personne> personnes=new LinkedList<Personne>();
		Statement statement=null;
		try {
			statement = Connexion.getInstance().getSqlConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT ID,NOM,PRENOM,ADRESSE,TELEPHONE FROM DOCTOR AND INFIRMIER WHERE DOCTOR_ID=INFIRMIER_ID"); // pour docteur on fait une jointure
			while (result.next()){
				int id=result.getInt("ID");
				String nom=result.getString("NOM");
				String prenom=result.getString("PRENOM");
				String adresse=result.getString("ADRESSE");
				String telephone=result.getString("TELEPHONE");
				Personne p= new Personne(nom, prenom, telephone, adresse);// creer un obj avec le contenu de la table avec personne existante
				p.setID(id);
				personnes.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			if (statement!=null){
				statement.close();
			}
		}
		return personnes;
		//	Object [][] data=new Object[personnes.size()][4];
		//
		//	for(int i=0;i<personnes.size();i++){
		//		data[i][0]=personnes.get(i).getNom();
		//		data[i][1]=personnes.get(i).getPrenom();
		//		data[i][2]=personnes.get(i).getAdresse();
		//		data[i][3]=personnes.get(i).getTelephone();
		//	}
		//	return data; // acces a toute la liste .
	}

}
