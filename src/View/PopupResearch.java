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
	//global
	private JLabel label_surname=new JLabel("surname:");
	private JTextField surname=new JTextField();
	private JLabel label_name = new JLabel("name");
	private JTextField name = new JTextField();
	private JLabel label_phone = new JLabel("telephone");
	private JTextField phone = new JTextField();
	private JLabel label_address = new JLabel("address:");
	private JTextField address = new JTextField();
	//doctor
	protected JLabel label_speciality = new JLabel("speciality:");
	protected JTextField speciality = new JTextField();
	//patient
	protected JLabel label_mutual = new JLabel("mutual");
	protected JTextField mutual = new JTextField();
	//infirmier
	private JLabel label_code_service = new JLabel("code service:");
	private JTextField code_service = new JTextField();
	private JLabel label_rotation = new JLabel("rotation:");
	private JTextField rotation = new JTextField();
	private JLabel label_salaire = new JLabel("salaire:");
	private JTextField salaire = new JTextField();
	private JComboBox combo=new JComboBox();
	private JButton cancel = new JButton("cancel");
	private JButton search = new JButton("search");
	
	//room
	private JLabel label_room_code_service= new JLabel("Code service :");
	private JLabel label_surveillant_id = new JLabel("Surveillant id");
	private JLabel label_nb_bed =  new JLabel("Bed number :");
	private JLabel label_bed_available = new JLabel("Available bed number :");

	private JTextField room_code_service=new JTextField();
	private JTextField room_surveillant = new JTextField();
	private JTextField room_bed_nb=new JTextField();
	private JTextField room_bed_available=new JTextField();

	//service
	private JLabel label_service_nom=new JLabel("Nom :");
	private JLabel label_service_batiment = new JLabel("Batiment :");
	private JLabel label_sercice_doc_id = new JLabel("Doc_id :");
	private JLabel label_service_code = new JLabel("Code : ");
	
	private JTextField service_nom = new JTextField();
	private JTextField service_batiment= new JTextField();
	private JTextField service_doc_id = new JTextField();
	private JTextField service_code = new JTextField();

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
				
/*			      else if(combo.getSelectedItem()=="Hospitalisation")
			      {
			    	  String no_malade=
			    	  requete=("SELECT* FROM hospitalisation WHERE no_malade LIKE '%"+no_malade+ "%' AND code_service LIKE '%"+code_service+  "%' AND no_chambre LIKE '%"+ no_chambre+ "%' AND lit LIKE '%"+ lit+";");
			      }
*/
			      else if(combo.getSelectedItem()=="Service")
			      {
			    	  //requete=("SELECT* FROM service WHERE code LIKE '%"+ code + "%' nom LIKE '%"+ nom + "%' batiment LIKE '%"+ batiment +"%' directeur LIKE '%"+ directeur +";");
			      }
			     
			      else if(combo.getSelectedItem()=="Room")
			      {
			    	  //requete=("SELECT* FROM chambre WHERE no_chambre LIKE '%"+ no_chambre + "%' code_service LIKE '%"+ code_service + "%' surveillant LIKE '%"+ surveillant +"%' nb_lits LIKE '%"+ nb_lits +";");
			      }
/*
			      else if(combo.getSelectedItem()=="Personel")
			      {
			    	  requete=("SELECT* FROM employe WHERE nom LIKE '%" + nom +  "%' AND prenom LIKE '%" + prenom + "%' AND adresse LIKE '%" + adresse +
								 "%' AND tel LIKE '%"+ tel+";");
			   
			      }
			      else if(combo.getSelectedItem()=="Treatment")
			      {
			    	  requete=("SELECT* FROM soigne WHERE ");
			      }
				
				*/
				
				try {
					System.out.println(requete);
					try {
						current_window.showResult(new Resultat(Connexion.getInstance(), requete),type);
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
		
		
		this.main_panel.add(panel_doctor);
		this.main_panel.add(panel_nurse);
		this.main_panel.add(panel_patient);
		this.main_panel.add(panel_room);
		this.main_panel.add(panel_service);
		this.main_panel.add(cancel);
		this.main_panel.add(search);
		this.add(main_panel);

		this.setVisible(true);
		panel_room.setVisible(false);
		panel_nurse.setVisible(false);
		panel_patient.setVisible(false);
		panel_service.setVisible(false);
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
		      }
		      else if(combo.getSelectedItem()=="Patient"){
		    	  panel_doctor.setVisible(false);
		    	  base.setVisible(true);

		    	  panel_nurse.setVisible(false);
		    	  panel_patient.setVisible(true);	
		    	  panel_room.setVisible(false);
		    	  panel_service.setVisible(false);}
		      else if (combo.getSelectedItem()=="Nurse") {
		    	  panel_doctor.setVisible(false);		
		    	  base.setVisible(true);

		    	  panel_nurse.setVisible(true);
		    	  panel_patient.setVisible(false);		
		    	  panel_room.setVisible(false);
		    	  panel_service.setVisible(false);
		    	  }
		      else if (combo.getSelectedItem()=="Room") {
		    	  panel_doctor.setVisible(false);
		    	  panel_nurse.setVisible(false);
		    	  panel_patient.setVisible(false);		
		    	  panel_room.setVisible(true);
		    	  panel_service.setVisible(false);
		    	  base.setVisible(false);

			}
		      else if (combo.getSelectedItem()=="Service") {
		    	  panel_doctor.setVisible(false);
		    	  panel_nurse.setVisible(false);
		    	  panel_patient.setVisible(false);		
		    	  panel_room.setVisible(false);
		    	  panel_service.setVisible(true);
		    	  base.setVisible(false);

			}
		}
	}

}
