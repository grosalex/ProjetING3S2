package model;

// =================== Classe d'ajout des diff�rents champs dans la BD ===============
// Produit par Mr Marques William    Chef de Projet
//                Bruneau Alexandre
//                Bertrand K�vin
//                Bao Huanley
//====================================================================================

/////////////////////////////////////////////////////////////////////////////////////////////////
// ATTENTION : J'ai fait les diif�rentes classes d'ajout , il ne manque plus que les listes
// (j'ai d�j� fait celle des personnes , je continuerai a faire les autres dans l'aprem et demain
// Il me faudrait juste quelques pr�cisions sur les rdv . 

// Dites moi ce que vous en pensez
/////////////////////////////////////////////////////////////////////////////////////////////////


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

// creer l'objet personne et docteur avant . 

/**
 * Classe qui regroupe les diff�rentes m�thodes d'ajout
 * @author BERTRAND , BRUNEAU , BAO , MARQUES
 *
 */
public class Add {
	Connection con=null;
	Statement stmt = null;
	// Pour cr�er l'obj connection pour notre exemple , a ne pas reporter
	public Add() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3305/abruneau","abruneau-rw","SQ3EdSFm");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// tu dois recup l'exception et afficher un message impossible de creer un docteur
	/**
	 * Methode qui ajoute les champs d'un docteur dans la BD docteur
	 * @param doctor
	 * @throws SQLException
	 */
	public void addDoctor(Doctor doctor) throws SQLException {
		addPersonne(doctor);
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO DOCTOR " 
						+ "(ID_PERSONNE, SPECIALITE) VALUES"
						+ "(?,?)";
		try{
			preparedStatement = con.prepareStatement(insertSQL);
			preparedStatement.setInt(0, doctor.getID()); 
			preparedStatement.setString(1, doctor.getSpecialite());
			
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
	 * M�thode qui ajoute les champs d'une infirmiere dans la table infirmiere extends personne
	 * @param infirmier
	 * @throws SQLException
	 */
	public void addNurse(Infirmier infirmier) throws SQLException {
		addPersonne(infirmier);
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO INFIRMIER " 
						+ "(ID_PERSONNE, CODE_SERVICE,ROTATION,SALAIRE) VALUES"
						+ "(?,?,?,?)";
		try{
			preparedStatement = con.prepareStatement(insertSQL);
			preparedStatement.setInt(0, infirmier.getID()); 
			preparedStatement.setInt(1, infirmier.getCode_Service());
			preparedStatement.setString(2, infirmier.getRotation());
			preparedStatement.setFloat(3, infirmier.getSalaire());
			
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
	// A ne pas oublier dans personne , la collonne ID soit de type automatique
	// ID MEDIUMINT NOT NULL AUTO_INCREMENT
	// iD cl� primaire faire le lien entre personne et doctor , ou NUMERO ??
	
	/**
	 * M�thode qui ajoute les champs d'un membre du personnel dans la table personnel extends personne 
	 * @param personnel
	 * @throws SQLException
	 */
	public void addPersonnel(Personnel personnel) throws SQLException {
		addPersonne(personnel);
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO PERSONNEL " 
						+ "(ID_PERSONNE, METIER) VALUES"
						+ "(?,?)";
		try{
			preparedStatement = con.prepareStatement(insertSQL);
			preparedStatement.setInt(0, personnel.getID()); 
			preparedStatement.setString(1, personnel.getMetier());
			
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
	 * M�thode qui ajoute une personne dans la BD personne
	 * @param personne
	 * @throws SQLException
	 */
	public void addPersonne(Personne personne) throws SQLException {
		PreparedStatement preparedStatement=null;
		String insertSQL= "INSERT INTO PERSONNE " 
						+ "(NOM,PRENOM,TELEPHONE,ADRESSE) VALUES"
						+ "(?,?,?,?)";
		try{
			preparedStatement = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); // 
			preparedStatement.setString(0, personne.getNom());
			preparedStatement.setString(1, personne.getPrenom());
			preparedStatement.setString(2, personne.getTelephone());
			preparedStatement.setString(3, personne.getAdresse());
			
			// Insertion de la ligne dans la table.
			int id = preparedStatement.executeUpdate();
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
		
	}
	
	// A partir de la , ce sont les listes , j'ai fait celle qui renvoie la liste des personnes 
	// Je continuerai demain pour faire la liste des docteurs , infirmiers , personnel
	// Pour l'instant je ne fais que celle la. 
	/**
	 * M�thode qui renvoie la liste des personnes
	 * @return personnes
	 * @throws SQLException
	 */
	public Object [][] selectAllPersonne() throws SQLException{
	LinkedList<Personne> personnes=new LinkedList<Personne>();
	Statement statement=null;
	try {
		 statement = con.createStatement();
		 ResultSet result = statement.executeQuery("SELECT NOM,PRENOM,ADRESSE,TELEPHONE FROM PERSONNE"); // pour docteur on fait une jointure
		 while (result.next()){
			 String nom=result.getString("NOM");
			 String prenom=result.getString("PRENOM");
			 String adresse=result.getString("ADRESSE");
			 String telephone=result.getString("TELEPHONE");
			 Personne p= new Personne(nom, prenom, telephone, adresse);// creer un obj avec le contenu de la table avec personne existante
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
	Object [][] data=new Object[personnes.size()][4];

	for(int i=0;i<personnes.size();i++){
		data[i][0]=personnes.get(i).getNom();
		data[i][1]=personnes.get(i).getPrenom();
		data[i][2]=personnes.get(i).getAdresse();
		data[i][3]=personnes.get(i).getTelephone();
	}
	return data; // acces a toute la liste .
	}
	
}