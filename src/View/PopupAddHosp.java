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

public class PopupAddHosp extends PopupAddPerson{
	private JLabel mutuel_label = new JLabel("Mutual fund");
	private JLabel service_id_label = new JLabel("Service Code");
	private JTextField mutuel = new JTextField();
	private JTextField service_id = new JTextField();
	public PopupAddHosp(Window current_window) {
		super(current_window);


		this.main_panel.remove(this.second_panel);
		this.main_panel.add(mutuel_label);
		this.main_panel.add(mutuel);
		this.main_panel.add(service_id_label);
		this.main_panel.add(service_id);
		this.main_panel.add(this.second_panel);

		this.add(this.main_panel);
		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Patient current_patient = new Patient(name.getText(), surname.getText(), phone.getText(), address.getText(),0, mutuel.getText());
					Resultat testdoc = new Resultat(Connexion.getInstance(),
							"SELECT * FROM malade WHERE numero="+current_patient.getID());
					Add.addHop(current_patient, service_id.getText());
					setVisible(false);
				} catch (SQLException | NoResultException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}

}
