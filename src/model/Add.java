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
			
			personne.setID(id); // on a l'ID de la table personne , � v�rifier.

		}catch (SQLException e){
			// A voir si on lance ou nouvelle exception
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
		
		return personne;

	}
	public static void addHop(Patient p, int id_doc, String code_service) throws SQLException {
		PreparedStatement preparedStatement=null;
		Resultat res;
		String insertSQL= "INSERT INTO hospitalisation"
				+ "(no_malade,code_service,no_chambre,lit,present) VALUES"
				+ "(?,?,?,?,1)";
		
		try {
			res = new Resultat(Connexion.getInstance(),"SELECT * FROM chambre WHERE lits_dispos=nb_lits AND code_service ='"+code_service+"'");
			Object[][] data = res.getResult();
			p =addPatient(p);
			System.out.println(p.getID());
			addSoigne(p, id_doc);
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, p.getID());
			preparedStatement.setString(2, code_service);
			preparedStatement.setInt(3,(int) data[0][1]);
			preparedStatement.setInt(4, 1);
			preparedStatement.executeUpdate();
			
			Connexion.getInstance().executeUpdate("UPDATE chambre SET lits_dispos=lits_dispos-1 WHERE code_service='"+code_service+
					"' AND no_chambre="+(int)data[0][1]);
		}
		catch(NoResultException e) {
			try {
				res = new Resultat(Connexion.getInstance(),"SELECT * FROM chambre WHERE lits_dispos>0 AND code_service ='"+code_service+"'");
				Object[][] data = res.getResult();
				int idChambre = (int)data[0][1];
				int nb_lits_occupes = ((int)data[0][3] - (int)data[0][4]);
				int idlit=1;
				res = new Resultat(Connexion.getInstance(),"SELECT * FROM hospitalisation WHERE no_chambre=" + idChambre + " AND code_service ='"+code_service+"'");
				Object[][] lits=res.getResult();
				for(int i=0;i<nb_lits_occupes;i++) {
					if(idlit==(int)lits[i][3]) {
						i=-1;
						idlit++;
					}
				}
				p=addPatient(p);
				addSoigne(p,id_doc);
				preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, p.getID());
				preparedStatement.setString(2, code_service);
				preparedStatement.setInt(3,(int) data[0][1]);
				preparedStatement.setInt(4, idlit);
				preparedStatement.executeUpdate();
				
				Connexion.getInstance().executeUpdate("UPDATE chambre SET lits_dispos=lits_dispos-1 WHERE code_service='"+code_service+
						"' AND no_chambre="+(int)data[0][1]);
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
	
	public static void addSoigne(Patient p, int id_doc) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO soigne"
				+ "(no_docteur,no_malade) VALUES"
				+ "(?,?)";

		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setInt(1, id_doc);
			preparedStatement.setInt(2, p.getID());

			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();

		}catch (SQLException e){
			// A voir si on lance ou nouvelle exception
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	
	public static Patient addPatient(Patient p) throws SQLException {
		Patient newP = p;
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
			ResultSet keys = preparedStatement.getGeneratedKeys();
			keys.next();
			System.out.println(keys.getInt(1));
			newP.setID(keys.getInt(1));

		}catch (SQLException e){
			// A voir si on lance ou nouvelle exception
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
		return newP;

	}
	
	public static void addService(Service s, int id_doc) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO service"
				+ "(code,nom,batiment,directeur) VALUES"
				+ "(?,?,?,?)";

		try{
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, s.getCode());
			preparedStatement.setString(2, s.getNom());
			preparedStatement.setString(3, s.getBatiment());
			preparedStatement.setInt(4,id_doc);

			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();

		}catch (SQLException e){
			// A voir si on lance ou nouvelle exception
			e.printStackTrace();
		}
		// meme en cas de pb on passe dans le finally
		finally{
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		}
	}
	
	public static void addChambre(Chambre c, String code_service, int surveillant) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO chambre"
				+ "(code_service,no_chambre,surveillant,nb_lits,lits_dispos) VALUES"
				+ "(?,?,?,?,?)";

		try{
			Resultat res = new Resultat(Connexion.getInstance(),"SELECT MAX(no_chambre) FROM chambre WHERE code_service='"+code_service+"'");
			Object[][] data = res.getResult();
			int no_chambre = (int)data[0][0]+1;
			
			preparedStatement = Connexion.getInstance().getSqlConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); //
			preparedStatement.setString(1, code_service);
			preparedStatement.setInt(2, no_chambre);
			preparedStatement.setInt(3, surveillant);
			preparedStatement.setInt(4,c.getNb_lits());
			preparedStatement.setInt(5,c.getNb_lits());

			// Insertion de la ligne dans la table.
			preparedStatement.executeUpdate();

		}catch (SQLException e){
			// A voir si on lance ou nouvelle exception
			e.printStackTrace();
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
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
