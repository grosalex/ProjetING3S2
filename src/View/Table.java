package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import connexion.Connexion;
import model.Doctor;
import model.Infirmier;
import model.NoResultException;
import model.Patient;
import model.Resultat;
import model.Update;

//on a un problème pour les listeners 
/*
 * deux solution je me fixe un attribut string qui contient le type et je creer l'objet qui va bien
 */
public class Table extends JTable {
	private String type=null;
	public Table(Object [][] data, String [] title,final String type) {
		super(data,title);
		this.type=type;
		this.setIntercellSpacing(new Dimension(5,5));

		this.addButton();
		
	}
	public void addButton(){
		// comming from http://tips4java.wordpress.com/2009/07/12/table-button-column/
		Action modify = new AbstractAction()
		{
		    @Override
			public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("test");
		    	int modelRow = Integer.valueOf(e.getActionCommand());
		    	TableModel model = getModel();
		    	if(type.equals("docteur")){
		    		int id=(int) model.getValueAt(modelRow, 0);
		    		String nom=(String) model.getValueAt(modelRow, 1);
		    		String prenom=(String) model.getValueAt(modelRow, 2);
		    		String telephone=(String) model.getValueAt(modelRow, 3);
		    		String adresse=(String) model.getValueAt(modelRow, 4);
		    		String specialite=(String) model.getValueAt(modelRow, 5);
		    		System.out.println("Docteur id =  "+ id + nom + prenom + telephone + adresse + specialite);

		    		Doctor upDoctor = new Doctor(id, nom, prenom, adresse, telephone, specialite);
		    		try {
						Update.Doctor(upDoctor);
						try {
							update(new Resultat(Connexion.getInstance(), "SELECT employe.numero, nom, prenom, adresse, tel, specialite FROM docteur, employe WHERE docteur.numero = employe.numero"), "docteur");
						} catch (NoResultException e1) {
							// TODO Bloc catch généré automatiquement
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						// TODO Bloc catch généré automatiquement
						e1.printStackTrace();
					}
		    	}
		    	else if(type.equals("infirmier")){
		    		int id= (int) model.getValueAt(modelRow, 0);
		    		String nom = (String) model.getValueAt(modelRow, 1);
		    		String prenom = (String) model.getValueAt(modelRow, 2);
		    		String telephone = (String) model.getValueAt(modelRow, 3);
		    		String adresse = (String) model.getValueAt(modelRow, 4);
		    		String rotation = (String) model.getValueAt(modelRow, 5);
		    		int code_Service = (int) model.getValueAt(modelRow, 6);
		    		float salaire = (float) model.getValueAt(modelRow, 7);
		    		Infirmier upInfirmier = new Infirmier(id, nom, prenom, telephone, adresse, rotation, code_Service, salaire);
		    		try {
						Update.Nurse(upInfirmier);
						try {
							update(new Resultat(Connexion.getInstance(), "SELECT * FROM infirmier"), "infirmier");
						} catch (NoResultException e1) {
							// TODO Bloc catch généré automatiquement
							e1.printStackTrace();
						}

					} catch (SQLException e1) {
						// TODO Bloc catch généré automatiquement
						e1.printStackTrace();
					}
		    	}
		    	else if(type.equals("malade")){
		    		int id=(int) model.getValueAt(modelRow, 0);
		    		String nom=(String) model.getValueAt(modelRow, 1);
		    		String prenom=(String) model.getValueAt(modelRow, 2);
		    		String telephone=(String) model.getValueAt(modelRow, 3);
		    		String adresse=(String) model.getValueAt(modelRow, 4);
		    		String mutuelle=(String) model.getValueAt(modelRow, 5);
		    		System.out.println("malade id =  "+ id + nom + prenom + telephone + adresse + mutuelle);

		    		Patient up_patient = new Patient(nom, prenom, adresse, telephone, id, mutuelle);
		    		try {
						Update.Patient(up_patient);
						try {
							update(new Resultat(Connexion.getInstance(), "SELECT * FROM malade"), "malade");
						} catch (NoResultException e1) {
							// TODO Bloc catch généré automatiquement
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						// TODO Bloc catch généré automatiquement
						e1.printStackTrace();
					}
		    	}
		    }
		};
		Action delete = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		ButtonColumn deleteButtonColumn = new ButtonColumn(this, modify, this.getColumnCount()-2);
		ButtonColumn modifyButtonColumn = new ButtonColumn(this, delete, this.getColumnCount()-1);
	}
	public void hide(String title){
		for(int i=0;i<getColumnCount();i++){
			if(this.getColumnName(i).equals(title)){
				this.getColumnModel().getColumn(i).setMinWidth(0);
				this.getColumnModel().getColumn(i).setMaxWidth(0);
			}
		}

		this.updateUI();
		
	}
	public void show(String title){
		for(int i=0;i<getColumnCount();i++){
			if(this.getColumnName(i).equals(title)){
				this.getColumnModel().getColumn(i-1).setMinWidth(this.getColumnModel().getColumn(i-1).getWidth()-50);
				this.getColumnModel().getColumn(i-1).setMaxWidth(this.getColumnModel().getColumn(i-1).getWidth()-50);
				this.getColumnModel().getColumn(i+1).setMinWidth(this.getColumnModel().getColumn(i+1).getWidth()-50);
				this.getColumnModel().getColumn(i+1).setMaxWidth(this.getColumnModel().getColumn(i+1).getWidth()-50);
				this.getColumnModel().getColumn(i).setMinWidth(100);
				this.getColumnModel().getColumn(i).setMaxWidth(100);
				this.getColumnModel().getColumn(i).setWidth(80);
			}
		}

		this.updateUI();
	}
	public void update(Resultat resultat, String type){
		this.type=type;
		this.setModel(new DefaultTableModel(resultat.getResult(), resultat.getTitles()));
		this.addButton();
		this.updateUI();

	}

}
