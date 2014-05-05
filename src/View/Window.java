package View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Add;
import model.Personne;
import connexion.Connexion;

public class Window extends JFrame{
	private JPanel left_panel=new JPanel();
	private JPanel rightJPanel=new JPanel();
	private JSplitPane main_split=null;
	private Menu menue = new Menu();
	private JTable main_table= null;
	//private Connexion ma_connection= null;
	public Window(){

		///Connection
		try{
			try {
				new Connexion("abruneau", "ab[0AB05", "abruneau-rw", "SQ3EdSFm");
			} catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		} catch(SQLException e){
			System.out.println("Sql excption at connection");
		}
		this.setTitle("Projet ING3 Semestre 2");
		this.setSize(800,600);		
		this.setJMenuBar(menue);
		//String [] title= {"Nom","Prenom","Adresse","Telephone"}; 
		LinkedList<Personne> data = null;
		try{
			data = Add.selectAllPersonne();
		}catch(SQLException e){
			System.out.println("problem de sql");
		}
		this.main_table = new JTable(new MaTableModel(data));
		this.rightJPanel.add(this.main_table);
		this.main_split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left_panel,rightJPanel);
		this.add(main_split);
		this.main_split.setResizeWeight(0.33);


		this.setVisible(true);
		
		// pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
	}

	class MaTableModel extends DefaultTableModel {

		private LinkedList<Personne> list;
		private LinkedList<String> colonnes = new LinkedList<String>();
		public MaTableModel(LinkedList<Personne> list){
			this.list = list;
			colonnes.add("Nom");
			colonnes.add("Prénom");
			colonnes.add("Téléphone");
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
