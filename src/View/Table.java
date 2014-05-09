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

/**
 * 
 * @author grosalex
 *	This is the class that manage the output of the data in a Table.
 *	It's extending JTable.
 *
 */
public class Table extends JTable {
	private String type=null;
	/**
	 * 
	 * @param data is the data going to feed the table
	 * @param title is the titles of the columns
	 * @param type is a string telling the type of data shown
	 */
	public Table(Object [][] data, String [] title,final String type) {
		super(data,title);
		this.type=type;
		this.setIntercellSpacing(new Dimension(5,5));

		this.addButton();
		
	}
	/**
	 * is an internal method to manage the button shown in the table
	 */
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
						Drop.Doctor(upDoctor);
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
		ButtonColumn deleteButtonColumn = new ButtonColumn(this, modify, this.getColumnCount()-2);
		ButtonColumn modifyButtonColumn = new ButtonColumn(this, delete, this.getColumnCount()-1);
	}
	
	/**
	 * is the method wich hide the column named after "title" params
	 * @param title title of the column which is going to be hidden
	 */
	public void hide(String title){
		for(int i=0;i<getColumnCount();i++){
			if(this.getColumnName(i).equals(title)){
				this.getColumnModel().getColumn(i).setMinWidth(0);
				this.getColumnModel().getColumn(i).setMaxWidth(0);
			}
		}

		this.updateUI();
		
	}
	/**
	 * is the opposite method of hide
	 * @param title of the column wich is going to come back from hidding
	 */
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
	
	/**
	 * is the way to update the content of the table
	 * @param resultat class containing what is needed to feed the table see Resultat Javadoc for more info
	 * @param type is the type of data shown
	 */
	public void update(Resultat resultat, String type){
		this.type=type;
		this.setModel(new DefaultTableModel(resultat.getResult(), resultat.getTitles()));
		this.addButton();
		this.updateUI();

	}

}
