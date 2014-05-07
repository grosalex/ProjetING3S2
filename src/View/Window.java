package View;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;


import model.Personne;
import connexion.Connexion;

import model.Add;
import model.Doctor;
import model.Resultat;

public class Window extends JFrame{
	private JPanel left_panel=new JPanel();
	private JPanel rightJPanel=new JPanel();
	private JSplitPane main_split=null;
	private Menu menue = null;

	private Table main_table= null;
	private Selection select_panel=null;
	private Resultat main_resultat=null;
	public Window(){

		this.setMainProperties("Projet ING3 Semestre 2", 800, 600);
		this.menue=new Menu(this);
		this.setJMenuBar(menue);

		this.initializePanels();


		this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
        
        Doctor d = new Doctor("bernard","michel","0123456789",
        		"10 avenue Coquelicots 75015 Paris","cardiologue");
        try {
			Add.addDoctor(d);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public void setMainProperties(String title,int width, int height){
		this.setTitle(title);
		this.setSize(width,height);
	}
	public void initializePanels(){
	    this.rightJPanel.setLayout(new BorderLayout());
		this.rightJPanel.add(new ConnectionPanel(this));
		this.main_split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left_panel,rightJPanel);
		this.add(main_split);
		this.main_split.setResizeWeight(0.33);
	}
	
	public void showTableEmploye(){
		try {
			this.main_resultat=new Resultat(Connexion.getInstance(), "SELECT * FROM employe");
			this.main_table = new Table(this.main_resultat.getResult(),this.main_resultat.getTitles());
			this.rightJPanel.add(new JScrollPane(main_table),BorderLayout.CENTER);
			this.rightJPanel.setVisible(true);
			this.left_panel.add(new Selection(this.main_resultat.getTitles(), this));
			this.left_panel.setVisible(true);
			
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	public void updateTable(String title, boolean action) {
		if(action){//add
			
		}
		else {//remove
			this.main_table.hide(title);
		}
		
	}

	class MaTableModel extends DefaultTableModel {

		private LinkedList<Personne> list;
		private LinkedList<String> colonnes = new LinkedList<String>();
		public MaTableModel(LinkedList<Personne> list){
			this.list = list;
			colonnes.add("Nom");
			colonnes.add("Pr�nom");
			colonnes.add("T�l�phone");
			colonnes.add("Adresse");
		}

		@Override
		public int getColumnCount() {
			return colonnes.size();
		}

		@Override
		public String getColumnName(int index) {
			return colonnes.get(index);
		}

		@Override
		public int getRowCount() {
			if(list == null)
				return 0;
			return list.size();
		}

		@Override
		public boolean isCellEditable(int ligne, int colonne) {
			return false;
		}

		@Override
		public Object getValueAt(int ligne, int colonne) {
			Personne personne = list.get(ligne);
			switch(colonne) {
			case 0:
				return personne.getNom();
			case 1:
				return personne.getPrenom();
			case 2:
				return personne.getTelephone();
			case 3:
				return personne.getAdresse();
			}
			return null;
		}

	}

}
