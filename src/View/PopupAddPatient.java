package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import model.Add;
import model.Patient;
/**
 * 
 * @author grosalex
 * This is the popup where users will be able to add a patient. It's extending the addDoctor popup cause there is the same sort of information
 *
 */
public class PopupAddPatient extends PopupAddDoctor{
	/**
	 * Default constructor
	 * @param current_window main window where the added information will be print
	 */
	public PopupAddPatient(final Window current_window) {
		super(current_window);
		this.label_speciality.setText("mutual");
		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Add.addPatient(new Patient(name.getText(), surname.getText(), phone.getText(), address.getText(),0, speciality.getText()));
					setVisible(false);
					current_window.updateTableEmployee();
				} catch (SQLException e) {
					// TODO Bloc catch généré automatiquement
					e.printStackTrace();
				}	
			}
		});
		
	}

}
