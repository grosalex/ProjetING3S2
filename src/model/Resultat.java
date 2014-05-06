package model;


import java.util.ArrayList;
import java.sql.*;

import connexion.Connexion;

public class Resultat {
	private int nbCol;
	private ArrayList<String> titles;
	private Object[][] result;
	private ResultSet rset;
	private ResultSetMetaData rsetMeta;
	private Statement stmt;
	Connexion c;
	
	public Resultat(Connexion c, String requete) throws SQLException {
		this.c = c;
		Object data[][];
		int nbLigne;
		int j=0;
		
		stmt = c.getSqlConnection().createStatement();
		rset = stmt.executeQuery(requete);
		rsetMeta = rset.getMetaData();
		nbCol = rsetMeta.getColumnCount();
		rset.last();
		nbLigne = rset.getRow()+1;
		rset.beforeFirst();
		data = new String[nbLigne][nbCol];

		for(int i=0;i<nbCol;i++) {
			titles.add(rsetMeta.getColumnLabel(i));
		}

		do {
			for(int i=0;i<nbCol;i++) {
				result[j][i] = rset.getObject(i);
				j++;
			}	
		}while(rset.next());
	}

	public Object[][] returnData(String requete) throws SQLException {
		Object data[][];
		int nbCol, nbLigne;
		int j=0;
		rset = stmt.executeQuery(requete);
		rsetMeta = rset.getMetaData();
		nbCol = rsetMeta.getColumnCount();
		rset.last();
		nbLigne = rset.getRow()+1;
		rset.beforeFirst();
		data = new String[nbLigne][nbCol];

		for(int i=0;i<nbCol;i++) {
			data[0][i] = rsetMeta.getColumnLabel(i);
		}

		do {
			for(int i=0;i<nbCol;i++) {
				j++;
				data[j][i] = rset.getObject(i);
			}	
		}while(rset.next());
		return data;
	}

	public int getNbCol() {
		return nbCol;
	}

	public ArrayList<String> getTitles() {
		return titles;
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
