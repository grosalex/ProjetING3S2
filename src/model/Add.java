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

import connexion.Connexion;

// creer l'objet personne et docteur avant . 

/**
 * Classe qui regroupe les diff�rentes m�thodes d'ajout
 * @author BERTRAND , BRUNEAU , BAO , MARQUES
 *
 */
public class Add {

	// ce cons ne peut etre apple�

	public Add() {
	}
	/**
	 * Methode qui ajoute les champs d'un docteur dans la BD docteur
	 * @param doctor
	 * @throws SQLException
	 */
	public static void addDoctor(Doctor doctor) throws SQLException {
		Personne p = addEmploye(doctor);
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO docteur " 
				+ "(numero, specialite) VALUES"
				+ "(?,?)";
		// delete from doctor where ID_personne = ? 
		//
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL);
			preparedStatement.setInt(1, p.getID()); 
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
	 * Methode qui ajoute les champs d'une infirmiere dans la table infirmiere extends personne
	 * @param infirmier
	 * @throws SQLException
	 */
	public static void addNurse(Infirmier infirmier) throws SQLException {
		Personne p = addEmploye(infirmier);
		
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO infirmier " 
				+ "(numero, code_service,rotation,salaire) VALUES"
				+ "(?,?,?,?)";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL);
			preparedStatement.setInt(1, p.getID()); 
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
		Personne p = addEmploye(personnel);
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO PERSONNEL " 
				+ "(ID_PERSONNE, METIER) VALUES"
				+ "(?,?)";
		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL);
			preparedStatement.setInt(1, p.getID()); 
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
	public static Personne addEmploye(Personne personne) throws SQLException {
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
			preparedStatement.executeUpdate();
			ResultSet keys = preparedStatement.getGeneratedKeys();
			keys.next();
			id = keys.getInt(1);
			System.out.println(id);
			
			personne.setID(id); // on a l'ID de la table personne , � v�rifier.

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
		
		return personne;

	}
	public static void addHop(Patient p, Doctor d, Service s) throws SQLException {
		PreparedStatement preparedStatement=null;
		Resultat res;
		String insertSQL= "INSERT INTO hospitalisation"
				+ "(no_malade,code_service,no_chambre,lit,present) VALUES"
				+ "(?,?,?,?,1)";
		
		try {
			res = new Resultat(Connexion.getInstance(),"SELECT * FROM chambre WHERE lits_dispos=nb_lits AND code_service ="+s.getCode());
			Object[][] data = res.getResult();
			addPatient(p);
			addSoigne(p, d);
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, p.getID());
			preparedStatement.setString(2, s.getCode());
			preparedStatement.setInt(3,(int) data[0][1]);
			preparedStatement.setInt(4, 1);
			preparedStatement.executeUpdate();
			
			Connexion.getInstance().executeUpdate("UPDATE chambre SET lits_dispos=lits_dispos-1 WHERE code_service="+s.getCode()+
					" AND no_chambre="+(int)data[0][1]);
		}
		catch(NoResultException e) {
			try {
				res = new Resultat(Connexion.getInstance(),"SELECT * FROM chambre WHERE lits_dispos>0 AND code_service ="+s.getCode());
				Object[][] data = res.getResult();
				int idChambre = (int)data[0][1];
				int nb_lits_occupes = ((int)data[0][3] - (int)data[0][4]);
				int idlit=1;
				res = new Resultat(Connexion.getInstance(),"SELECT * FROM hospitalisation WHERE no_chambre=" + idChambre + " AND code_service ="+s.getCode());
				Object[][] lits=res.getResult();
				for(int i=0;i<nb_lits_occupes;i++) {
					if(idlit==(int)lits[i][3]) {
						i=-1;
						idlit++;
					}
				}
				addPatient(p);
				addSoigne(p,d);
				preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, p.getID());
				preparedStatement.setString(2, s.getCode());
				preparedStatement.setInt(3,(int) data[0][1]);
				preparedStatement.setInt(4, idlit);
				preparedStatement.executeUpdate();
				
				Connexion.getInstance().executeUpdate("UPDATE chambre SET lits_dispos=lits_dispos-1 WHERE code_service="+s.getCode()+
						" AND no_chambre="+(int)data[0][1]);
			} catch (NoResultException e1) {
				e1.printStackTrace();
			}
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}

	}
	
	public static void addSoigne(Patient p, Doctor d) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO soigne"
				+ "(no_docteur,no_soigne) VALUES"
				+ "(?,?)";

		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setInt(1, d.getID());
			preparedStatement.setInt(2, p.getID());

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
	
	public static void addPatient(Patient p) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO malade"
				+ "(nom,prenom,tel,adresse,mutuelle) VALUES"
				+ "(?,?,?,?,?)";

		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, p.getNom());
			preparedStatement.setString(2, p.getPrenom());
			preparedStatement.setString(3, p.getTelephone());
			preparedStatement.setString(4, p.getAdresse());
			preparedStatement.setString(5, p.getMutuelle());

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

}
