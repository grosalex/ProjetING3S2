package View;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import Recherche.Recherche;
import model.Add;
import connexion.Connexion;

public class Window extends JFrame{
	private JPanel left_panel=new JPanel();
	private JPanel rightJPanel=new JPanel();
	private JSplitPane main_split=null;
	private Menu menue = new Menu();
	private Table main_table= null;
	private Connexion ma_connection= null;
	private JTextArea left= new JTextArea();
	public Window(){
		
		///Connection
		try{
			this.ma_connection = new Connexion("abruneau", "ab[0AB05", "abruneau-rw", "SQ3EdSFm");
		}catch(SQLException e){
			System.out.println("Sql excption at connection");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		this.setTitle("Projet ING3 Semestre 2");
		this.setSize(800,600);		
		this.setJMenuBar(menue);
		/*
		String [] title= {"Nom","Prenom","Adresse","Telephone"}; 
		ArrayList<String> temp = new ArrayList<String>();
		Recherche ma_recherche = new Recherche(ma_connection);
		try{
		 temp = ma_connection.remplirChampsRequete(ma_recherche.requetes.get(6));
		}catch(SQLException e){
			System.out.println("problem de sql");
		}
		Object [][] data = new Object[temp.size()][4];

		for(int i=0;i<temp.size();i++){
			data[i][0]=temp.get(i);
			data[i][1]=temp.get(i);
			data[i][2]=temp.get(i);
			data[i][3]=temp.get(i);

		}
		*/
		
		//this.main_table = new Table(data,title);
		//this.rightJPanel.add(this.main_table);
		ArrayList<String> temp = new ArrayList<String>();
		Recherche ma_recherche = new Recherche(ma_connection);
		try{
			 temp = ma_connection.remplirChampsRequete(ma_recherche.requetes.get(6));
			}catch(SQLException e){
				System.out.println("problem de sql");
			}
		for(int i=0;i<temp.size();i++){
			this.left.append(temp.get(i));
		}
		this.left_panel.add(this.left);
		this.main_split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left_panel,rightJPanel);
		this.add(main_split);
		this.main_split.setResizeWeight(0.33);

		
		this.setVisible(true);
	}

}
