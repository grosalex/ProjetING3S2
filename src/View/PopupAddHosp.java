package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Add;
import model.Patient;

public class PopupAddHosp extends PopupAddPerson{
	private JLabel mutuel_label = new JLabel("mutual");
	private JLabel doctor_id_label=new JLabel("Id doctor :");
	private JLabel service_id_label = new JLabel("Id service :");
	private JTextField mutuel = new JTextField();
	private JTextField doctor_id = new JTextField();
	private JTextField service_id = new JTextField();
	public PopupAddHosp(Window current_window) {
		super(current_window);


		this.main_panel.remove(this.second_panel);
		this.main_panel.add(mutuel_label);
		this.main_panel.add(mutuel);
		this.main_panel.add(doctor_id_label);
		this.main_panel.add(doctor_id);
		this.main_panel.add(service_id_label);
		this.main_panel.add(service_id);
		this.main_panel.add(this.second_panel);

		this.add(this.main_panel);
		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Patient current_patient = new Patient(name.getText(), surname.getText(), phone.getText(), address.getText(),0, mutuel.getText());
					Add.addHop(current_patient, Integer.parseInt(doctor_id.getText()), service_id.getText());
					setVisible(false);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}

}
