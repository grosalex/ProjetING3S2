package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.NoResultException;
import model.Resultat;
import connexion.Connexion;

/**
 * This is the popup to make research
 * @author grosalex
 *
 */
public class PopupResearch extends JDialog{
	private JPanel main_panel=new JPanel();
	private JPanel base=new JPanel();
	private JPanel panel_doctor=new JPanel();
	private JPanel panel_patient=new JPanel();
	private JPanel panel_nurse=new JPanel();
	private JPanel panel_room = new JPanel();
	private JPanel panel_service = new JPanel();
	private JPanel panel_hospi = new JPanel();
	private JPanel panel_histo = new JPanel();
	private JPanel panel_followup = new JPanel();
	//global
	private JLabel label_surname=new JLabel("Surname");
	private JTextField surname=new JTextField();
	private JLabel label_name = new JLabel("Name");
	private JTextField name = new JTextField();
	private JLabel label_phone = new JLabel("Phone");
	private JTextField phone = new JTextField();
	private JLabel label_address = new JLabel("Address");
	private JTextField address = new JTextField();
	//doctor
	protected JLabel label_speciality = new JLabel("Speciality");
	protected JTextField speciality = new JTextField();
	//patient
	protected JLabel label_mutual = new JLabel("Mutual Fund");
	protected JTextField mutual = new JTextField();
	//infirmier
	private JLabel label_code_service = new JLabel("Service Code");
	private JTextField code_service = new JTextField();
	private JLabel label_rotation = new JLabel("Rotation");
	private JTextField rotation = new JTextField();
	private JLabel label_salaire = new JLabel("Salary");
	private JTextField salaire = new JTextField();
	private JComboBox combo=new JComboBox();
	private JButton cancel = new JButton("Cancel");
	private JButton search = new JButton("Search");

	//room
	private JLabel label_room_code_service= new JLabel("Service Code");
	private JLabel label_surveillant_id = new JLabel("Supervisor ID");
	private JLabel label_nb_bed =  new JLabel("Total bed number");
	private JLabel label_bed_available = new JLabel("Number of available Beds");

	private JTextField room_code_service=new JTextField();
	private JTextField room_surveillant = new JTextField();
	private JTextField room_bed_nb=new JTextField();
	private JTextField room_bed_available=new JTextField();

	//service
	private JLabel label_service_nom=new JLabel("Name");
	private JLabel label_service_batiment = new JLabel("Building");
	private JLabel label_sercice_doc_id = new JLabel("Director ID");
	private JLabel label_service_code = new JLabel("Code");

	private JTextField service_nom = new JTextField();
	private JTextField service_batiment= new JTextField();
	private JTextField service_doc_id = new JTextField();
	private JTextField service_code = new JTextField();

	//hospitalisation
	private JLabel label_num_malade = new JLabel("Patient ID");
	private JLabel label_num_chambre = new JLabel("Room Number");
	private JLabel label_num_service = new JLabel("Service Code");
	private JTextField hosp_num_malade = new JTextField();
	private JTextField hosp_num_chambre = new JTextField();
	private JTextField hosp_code_service = new JTextField();

	//hospitalisation old
	private JLabel label_histo_num_malade = new JLabel("Patient ID");
	private JLabel label_histo_num_chambre = new JLabel("Room Number");
	private JLabel label_histo_num_service = new JLabel("Service Code");
	private JTextField histo_num_malade = new JTextField();
	private JTextField histo_num_chambre = new JTextField();
	private JTextField histo_code_service = new JTextField();
	
	//Followup
	private JLabel label_followup_num_malade = new JLabel("Patient ID");
	private JLabel label_followup_num_doc = new JLabel("Doctor ID");
	private JTextField followup_num_malade = new JTextField();
	private JTextField followup_num_doc = new JTextField();

	private Window current_window=null;
	/**
	 * Constructo
	 * @param current is the window where results of the search are going to be print
	 */
	public PopupResearch(Window current) {
		this.current_window=current;
		this.setSize(400,400);
		combo.addActionListener(new ItemAction());
		combo.addItem("Doctor");
		combo.addItem("Patient");
		combo.addItem("Nurse");
		combo.addItem("Service");
		combo.addItem("Room");
		combo.addItem("Followup");
		combo.addItem("Hospitalisation");
		combo.addItem("Old Hospitalisation");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String requete=null;
				String type=null;
				String nom = surname.getText();
				String prenom = name.getText();
				String adresse = address.getText();
				String tel= phone.getText();

				if(combo.getSelectedItem()=="Doctor"){
					type="docteur";
					String specialite = speciality.getText();
					requete=("SELECT employe.numero ,nom, prenom, adresse, tel, specialite FROM employe,docteur WHERE "+
							"employe.nom LIKE '%" + nom +  "%' AND employe.prenom LIKE '%" + prenom + "%' AND employe.adresse LIKE '%" + adresse +
							"%' AND employe.tel LIKE '%"+ tel +  "%' AND docteur.specialite LIKE '%" + specialite +"%' AND employe.numero=docteur.numero;");
				}
				else if(combo.getSelectedItem()=="Patient"){
					type="malade";
					String mutuelle = mutual.getText();
					requete=("SELECT* FROM malade WHERE numero LIKE '%"+ nom + "%' AND prenom LIKE '%" + prenom +
							"%' AND tel LIKE '%" + tel + "%' AND adresse LIKE '%" + adresse+"%' AND mutuelle LIKE '%"+mutuelle+"%' ;");
				}

				else if (combo.getSelectedItem()=="Nurse") {
					type="infirmier";

					requete= ("SELECT employe.numero ,nom, prenom, adresse, tel, code_service,rotation,salaire FROM employe,infirmier WHERE "+
							"employe.nom LIKE '%" + nom +  "%' AND employe.prenom LIKE '%" + prenom + "%' AND employe.adresse LIKE '%" + adresse +
							"%' AND employe.tel LIKE '%"+ tel +  "%' AND infirmier.code_service LIKE '%"+code_service.getText()
							+ "%' AND infirmier.rotation LIKE '%" + rotation.getText()+"%' AND infirmier.salaire LIKE '%"+ salaire.getText()
							+ "%' AND employe.numero=infirmier.numero;");

				}

				else if(combo.getSelectedItem()=="Hospitalisation")
				{
					type="Hospi";
					requete=("SELECT * FROM hospitalisation WHERE no_malade LIKE '%"+ hosp_num_malade.getText() + "%' AND code_service LIKE '%"+ hosp_code_service.getText() +  "%' AND no_chambre LIKE '%"+ hosp_num_chambre.getText() + "%';");
				}

				else if(combo.getSelectedItem()=="Service")
				{
					type="Service";
					requete=("SELECT * FROM service WHERE code LIKE '%"+ code_service.getText() + "%' AND nom LIKE '%"+ service_nom.getText() + "%' AND batiment LIKE '%"+ service_batiment.getText() +"%' AND directeur LIKE '%"+ service_doc_id.getText() +"%';");
				}

				else if(combo.getSelectedItem()=="Room")
				{
					type="Room";
					requete=("SELECT * FROM chambre WHERE code_service LIKE '%"+ room_code_service.getText() + "%' AND surveillant LIKE '%"+ room_surveillant.getText() +"%' AND nb_lits LIKE '%"+ room_bed_nb.getText() +"%';");

				}
				
				else if(combo.getSelectedItem()=="Old Hospitalisation")
				{
					type="Hospi";
					requete=("SELECT * FROM hospitalisation_old WHERE no_malade LIKE '%"+ histo_num_malade.getText() + "%' AND code_service LIKE '%"+ histo_code_service.getText() +  "%' AND no_chambre LIKE '%"+ histo_num_chambre.getText() + "%';");
				}
				
				else if(combo.getSelectedItem()=="Followup")
				{
					type="Followup";
					requete="SELECT * FROM soigne WHERE no_malade LIKE '%"+followup_num_malade.getText()+"%' AND no_docteur LIKE '%"+followup_num_doc.getText()+"%'";
				}


				try {
					System.out.println(requete);
					try {
						current_window.showResult(new Resultat(Connexion.getInstance(), requete),type);
						if(type.equals("Hospi")) {
							current_window.updateTable("Modify", false);
						}
						if(type.equals("Followup")) {
							current_window.updateTable("Modify", false);
						}
					} catch (NoResultException e1) {
						// TODO Bloc catch généré automatiquement
						e1.printStackTrace();
					}
					setVisible(false);
				} catch (SQLException e1) {
					// TODO Bloc catch généré automatiquement
					e1.printStackTrace();
				}
				//Resultat result = new Resultat(Connexion.getInstance(), requete);

			}
		});
		this.main_panel.setLayout(new BoxLayout(this.main_panel,BoxLayout.PAGE_AXIS));
		this.main_panel.add(combo);
		this.base.setLayout(new BoxLayout(this.base, BoxLayout.PAGE_AXIS));
		this.base.add(label_surname);
		this.base.add(surname);
		this.base.add(label_name);
		this.base.add(name);
		this.base.add(label_phone);
		this.base.add(phone);
		this.base.add(label_address);
		this.base.add(address);
		this.main_panel.add(base);

		this.panel_doctor.setLayout(new BoxLayout(this.panel_doctor,BoxLayout.PAGE_AXIS));
		this.panel_doctor.add(label_speciality);
		this.panel_doctor.add(speciality);

		this.panel_patient.setLayout(new BoxLayout(this.panel_patient,BoxLayout.PAGE_AXIS));
		this.panel_patient.add(label_mutual);
		this.panel_patient.add(mutual);


		this.panel_nurse.setLayout(new BoxLayout(this.panel_nurse,BoxLayout.PAGE_AXIS));
		this.panel_nurse.add(label_code_service);
		this.panel_nurse.add(code_service);
		this.panel_nurse.add(label_salaire);
		this.panel_nurse.add(salaire);
		this.panel_nurse.add(label_rotation);
		this.panel_nurse.add(rotation);

		this.panel_room.setLayout(new BoxLayout(this.panel_room, BoxLayout.PAGE_AXIS));
		this.panel_room.add(label_room_code_service);
		this.panel_room.add(room_code_service);
		this.panel_room.add(label_surveillant_id);
		this.panel_room.add(room_surveillant);
		this.panel_room.add(label_nb_bed);
		this.panel_room.add(room_bed_nb);
		this.panel_room.add(label_bed_available);
		this.panel_room.add(room_bed_available);

		this.panel_service.setLayout(new BoxLayout(this.panel_service, BoxLayout.PAGE_AXIS));
		this.panel_service.add(label_service_nom);
		this.panel_service.add(service_nom);
		this.panel_service.add(label_service_batiment);
		this.panel_service.add(service_batiment);
		this.panel_service.add(label_sercice_doc_id);
		this.panel_service.add(service_doc_id);
		this.panel_service.add(label_service_code);
		this.panel_service.add(service_code);

		this.panel_hospi.setLayout(new BoxLayout(this.panel_hospi,BoxLayout.PAGE_AXIS));
		this.panel_hospi.add(label_num_malade);
		this.panel_hospi.add(hosp_num_malade);
		this.panel_hospi.add(label_num_chambre);
		this.panel_hospi.add(hosp_num_chambre);
		this.panel_hospi.add(label_num_service);
		this.panel_hospi.add(hosp_code_service);
		
		this.panel_histo.setLayout(new BoxLayout(this.panel_histo,BoxLayout.PAGE_AXIS));
		this.panel_histo.add(label_histo_num_malade);
		this.panel_histo.add(histo_num_malade);
		this.panel_histo.add(label_histo_num_chambre);
		this.panel_histo.add(histo_num_chambre);
		this.panel_histo.add(label_histo_num_service);
		this.panel_histo.add(histo_code_service);
		
		this.panel_followup.setLayout(new BoxLayout(this.panel_followup, BoxLayout.PAGE_AXIS));
		this.panel_followup.add(label_followup_num_malade);
		this.panel_followup.add(followup_num_malade);
		this.panel_followup.add(label_followup_num_doc);
		this.panel_followup.add(followup_num_doc);
		
		
		this.main_panel.add(panel_doctor);
		this.main_panel.add(panel_nurse);
		this.main_panel.add(panel_patient);
		this.main_panel.add(panel_room);
		this.main_panel.add(panel_service);
		this.main_panel.add(panel_hospi);
		this.main_panel.add(panel_histo);
		this.main_panel.add(panel_followup);
		this.main_panel.add(cancel);
		this.main_panel.add(search);
		this.add(main_panel);

		this.setVisible(true);
		panel_room.setVisible(false);
		panel_nurse.setVisible(false);
		panel_patient.setVisible(false);
		panel_service.setVisible(false);
		panel_hospi.setVisible(false);
	}
	/**
	 * This is where the event react to the combo box
	 * @author grosalex
	 *
	 */
	class ItemAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(combo.getSelectedItem()=="Doctor"){
				panel_doctor.setVisible(true);
				base.setVisible(true);
				panel_nurse.setVisible(false);
				panel_patient.setVisible(false);
				panel_room.setVisible(false);
				panel_service.setVisible(false);
				panel_hospi.setVisible(false);
				panel_histo.setVisible(false);
				panel_followup.setVisible(false);
			}
			else if(combo.getSelectedItem()=="Patient"){
				panel_doctor.setVisible(false);
				base.setVisible(true);

				panel_nurse.setVisible(false);
				panel_patient.setVisible(true);	
				panel_room.setVisible(false);
				panel_service.setVisible(false);
				panel_hospi.setVisible(false);
				panel_histo.setVisible(false);
				panel_followup.setVisible(false);
			}

			else if (combo.getSelectedItem()=="Nurse") {
				panel_doctor.setVisible(false);		
				base.setVisible(true);
				panel_hospi.setVisible(false);

				panel_nurse.setVisible(true);
				panel_patient.setVisible(false);		
				panel_room.setVisible(false);
				panel_service.setVisible(false);
				panel_histo.setVisible(false);
				panel_followup.setVisible(false);
			}
			else if (combo.getSelectedItem()=="Room") {
				panel_doctor.setVisible(false);
				panel_nurse.setVisible(false);
				panel_patient.setVisible(false);		
				panel_room.setVisible(true);
				panel_service.setVisible(false);
				base.setVisible(false);
				panel_hospi.setVisible(false);
				panel_histo.setVisible(false);
				panel_followup.setVisible(false);

			}
			else if (combo.getSelectedItem()=="Service") {
				panel_doctor.setVisible(false);
				panel_nurse.setVisible(false);
				panel_patient.setVisible(false);		
				panel_room.setVisible(false);
				panel_service.setVisible(true);
				base.setVisible(false);
				panel_hospi.setVisible(false);
				panel_histo.setVisible(false);
				panel_followup.setVisible(false);

			}
			else if (combo.getSelectedItem()=="Hospitalisation") {
				panel_doctor.setVisible(false);
				panel_nurse.setVisible(false);
				panel_patient.setVisible(false);		
				panel_room.setVisible(false);
				panel_service.setVisible(false);
				base.setVisible(false);
				panel_hospi.setVisible(true);
				panel_histo.setVisible(false);
				panel_followup.setVisible(false);
			}
			else if (combo.getSelectedItem()=="Old Hospitalisation") {
				panel_doctor.setVisible(false);
				panel_nurse.setVisible(false);
				panel_patient.setVisible(false);		
				panel_room.setVisible(false);
				panel_service.setVisible(false);
				base.setVisible(false);
				panel_hospi.setVisible(false);
				panel_histo.setVisible(true);
				panel_followup.setVisible(false);
			}
			
			else if(combo.getSelectedItem()=="Followup") {
				panel_doctor.setVisible(false);
				panel_nurse.setVisible(false);
				panel_patient.setVisible(false);		
				panel_room.setVisible(false);
				panel_service.setVisible(false);
				base.setVisible(false);
				panel_hospi.setVisible(false);
				panel_histo.setVisible(false);
				panel_followup.setVisible(true);
			}
		}
	}

}
