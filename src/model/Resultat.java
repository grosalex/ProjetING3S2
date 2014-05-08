package model;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connexion.Connexion;

public class Resultat {
	private int nbCol;
	private ArrayList<String> titles;
	private Object[][] result;
	private ResultSet rset;
	private ResultSetMetaData rsetMeta;
	private Statement stmt;
	Connexion c;
	
	public Resultat(Connexion c, String requete) throws SQLException, NoResultException {
		this.c = c;
		int nbLigne;
		int j=0;
		titles=new ArrayList<String>();
		stmt = c.getSqlConnection().createStatement();
		rset = stmt.executeQuery(requete);
		if (!rset.isBeforeFirst() ) {
			System.out.println("Result vide !");
			throw new NoResultException(); 
		}
		rsetMeta = rset.getMetaData();
		nbCol = rsetMeta.getColumnCount();
		rset.last();
		nbLigne = rset.getRow();
		rset.first();
		result=new Object[nbLigne][nbCol+2];

		for(int i=1;i<=nbCol;i++) {
			titles.add(rsetMeta.getColumnLabel(i));
		}
		titles.add("Modify");
		titles.add("Delete");
		do {
			
			for(int i=0;i<nbCol;i++) {
				result[j][i] = rset.getObject(i+1);
			}
			
			result[j][nbCol] = "Modify";
			result[j][nbCol+1] = "Delete";
			j++;

		}while(rset.next());
	}

	public int getNbCol() {
		return nbCol;
	}

	public String[] getTitles() {
		String [] retour=new String[titles.size()];
		for(int i=0;i<titles.size();i++){
			retour[i]=titles.get(i);
		}
		return retour;
	}

	public Object[][] getResult() {
		return result;
	}

	public ResultSet getRset() {
		return rset;
	}

	public ResultSetMetaData getRsetMeta() {
		return rsetMeta;
	}

	public Statement getStmt() {
		return stmt;
	}

	public Connexion getC() {
		return c;
	}
}
