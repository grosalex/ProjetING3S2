package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import connexion.Connexion;
import model.Add;
import model.NoResultException;
import model.Patient;
import model.Resultat;

public class PopupAddHosp extends PopupAddPatient{
	private JLabel doctor_id_label=new JLabel("Id doctor :");
	private JLabel service_id_label = new JLabel("Id service :");
	private JTextField doctor_id = new JTextField();
	private JTextField service_id = new JTextField();
	public PopupAddHosp(Window current_window) {
		super(current_window);
		this.main_panel.remove(this.second_panel);
		this.main_panel.add(doctor_id_label);
		this.main_panel.add(doctor_id);
		this.main_panel.add(service_id_label);
		this.main_panel.add(service_id);
		this.main_panel.add(this.second_panel);
		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Patient current_patient = new Patient(name.getText(), surname.getText(), phone.getText(), address.getText(),0, speciality.getText());
					Add.addHop(current_patient, Integer.parseInt(doctor_id.getText()), service_id.getText());
/*					String requete_doc = "SELECT employe.numero ,nom, prenom, adresse, tel, specialite FROM employe,docteur WHERE employe.id="+doctor_id.getText();
					String requete_service = "SELECT code ,nom, batiment, directeur FROM service WHERE code="+service_id.getText();
					try {
						Resultat doc_resultat = new Resultat(Connexion.getInstance(), requete_doc);
						Resultat service_resultat = new Resultat(Connexion.getInstance(), requete_service);
						Doctor current_doc=new Doctor(doc_resultat.getResult()[0][0]);						
					} catch (NoResultException e1) {
						// TODO Bloc catch généré automatiquement
						e1.printStackTrace();
					}
					*/
					setVisible(false);
					
				} catch (SQLException e1) {
					// TODO Bloc catch généré automatiquement
					e1.printStackTrace();
				}
				
			}
		});
	}

}
