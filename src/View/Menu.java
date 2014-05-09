package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import connexion.Connexion;
import model.NoResultException;
import model.Resultat;
/**
 * This the menu of the Window Class
 * @author grosalex
 *
 */
public class Menu extends JMenuBar{
	private  Window current_window=null;
	
	private JMenu add = new JMenu("Add");
	private JMenuItem add_doctor = new JMenuItem("Doctor");
	private JMenuItem add_nurse = new JMenuItem("Nurse");
	private JMenuItem add_patient = new JMenuItem("Patient");
	private JMenuItem add_hosp = new JMenuItem("Hospitalisation");
	private JMenuItem add_room= new JMenuItem("Room");
	private JMenuItem add_service = new JMenuItem("Service");
	private JMenuItem research = new JMenuItem("Research");
	private JMenu request = new JMenu("Various Requests");
	

	private JMenuItem nurse_salary_per_service = new JMenuItem("Nurse salary per service");
	private JMenuItem bed_average_in_service = new JMenuItem("Bed avarage in service");
	private JMenuItem patients_three_doc = new JMenuItem("Patients with more than 3 doctors");
	private JMenuItem nurses_patients_ratio = new JMenuItem("Nurses/Patients Ratio by Services");
	private JMenuItem doc_without_patient = new JMenuItem("Doctor without patient");
	/// TODO faire le menu deroulant qui va bien pour les requetes
	private JMenuItem history = new JMenuItem("Historise");
	
	private JMenuItem exit = new JMenuItem("Exit");
	
	
	/**
	 * The constructor
	 * @param window the main window containing the menu
	 */
	public Menu(Window window){
		
		this.current_window = window;
		this.initListener();
		this.initItems();

	}
	/**
	 * It's an internal method to init content of the menu
	 */
	public void initItems(){
		
		this.add.add(add_doctor);
		this.add.add(add_nurse);
		this.add.add(add_patient);
		this.add.add(add_hosp);
		this.add.add(add_room);
		this.add.add(add_service);
		this.add(add);
		this.add(research);

		this.request.add(nurse_salary_per_service);
		this.request.add(bed_average_in_service);
		this.request.add(patients_three_doc);
		this.request.add(nurses_patients_ratio);
		this.request.add(doc_without_patient);
		
		this.add(request);
		this.add(history);
		this.add(exit);

		
	}
	/**
	 * It's an internal method to add the listener of the menu
	 */
	public void initListener(){
		
		this.nurses_patients_ratio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					current_window.showResult(new Resultat(Connexion.getInstance(),
							"SELECT s.nom,COUNT(DISTINCT i.numero)/COUNT(DISTINCT h.no_malade) rapport_i_sur_m FROM infirmier i,hospitalisation h, service s WHERE i.code_service=h.code_service AND s.code=i.code_service GROUP BY s.nom;"),"");
				} catch (SQLException | NoResultException e1) {
					e1.printStackTrace();
				}
				current_window.updateTable("Modify", false);
				current_window.updateTable("Delete", false);
			}
		});
		
		this.patients_three_doc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 
				try {
					current_window.showResult(new Resultat(Connexion.getInstance(),
							"SELECT m.nom, m.prenom, COUNT(DISTINCT no_docteur) nb_soignant,COUNT(DISTINCT d.specialite) nb_specialite FROM malade m,soigne s,docteur d WHERE m.numero=s.no_malade AND d.numero=s.no_docteur GROUP BY m.nom HAVING COUNT(s.no_malade)>3;"),"");
				} catch (SQLException | NoResultException e1) {
					e1.printStackTrace();
				}
				current_window.updateTable("Modify", false);
				current_window.updateTable("Delete", false);
			}
		});
		
		this.exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		this.bed_average_in_service.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 
				try {
					current_window.showResult(new Resultat(Connexion.getInstance(),
							"SELECT code_service,AVG(nb_lits) nb_moyen_de_lits FROM chambre c,service s WHERE c.code_service=s.code GROUP BY code_service;"),"");
				} catch (SQLException | NoResultException e1) {
					e1.printStackTrace();
				}
				current_window.updateTable("Modify", false);
				current_window.updateTable("Delete", false);
			}
		});
		
		this.add_doctor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupAddDoctor add_doctor = new PopupAddDoctor(current_window);
				add_doctor.setVisible(true);
				
			}
		});
		this.add_nurse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupAddNurse add_nurse = new PopupAddNurse(current_window);
				add_nurse.setVisible(true);
			}
		});
		this.add_patient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupAddPatient add_patient = new PopupAddPatient(current_window);
				add_patient.setVisible(true);
			}
		});
		
		this.doc_without_patient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					current_window.showResult(new Resultat(Connexion.getInstance(),
							"SELECT e.prenom,e.nom FROM employe e,docteur d1 WHERE e.numero=d1.numero AND d1.numero NOT IN(SELECT d2.numero FROM docteur d2 WHERE EXISTS(SELECT* FROM docteur d,soigne s, hospitalisation h WHERE d.numero=s.no_docteur AND s.no_malade=h.no_malade AND d.numero=d2.numero))ORDER BY e.nom;"),"");
				} catch (SQLException | NoResultException e1) {
					e1.printStackTrace();
				}
				current_window.updateTable("Modify", false);
				current_window.updateTable("Delete", false);
				
				
			}
		});
		
		this.add_hosp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PopupAddHosp add_hosp = new PopupAddHosp(current_window);
				add_hosp.setVisible(true);
			}
		});
		this.add_room.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PopupAddRoom add_room=new PopupAddRoom(current_window);
				add_room.setVisible(true);
			}
		});
		this.add_service.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupAddService add_service = new PopupAddService(current_window);
				add_service.setVisible(true);
			}
		});
		this.research.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupResearch research=new PopupResearch(current_window);
				research.setVisible(true);
			}
		});
		
		///TODO listener des requetes de huan lee
		this.nurse_salary_per_service.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					current_window.showResult(new Resultat(Connexion.getInstance(),
							"SELECT code_service, AVG(salaire) moyenne_des_salaires FROM infirmier GROUP BY code_service;"),"");
					current_window.updateTable("Modify", false);
					current_window.updateTable("Delete", false);
				} catch (SQLException | NoResultException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		this.history.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupHistorisation histo=new PopupHistorisation(current_window);
				histo.setVisible(true);
			}
		});

	
	}
	

}
