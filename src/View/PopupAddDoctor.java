package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Add;
import model.Doctor;

public class PopupAddDoctor extends PopupAddPerson{
	protected JLabel label_speciality = new JLabel("speciality:");
	protected JTextField speciality = new JTextField();
	public PopupAddDoctor(final Window current_window) {
		super(current_window);
		this.main_panel.add(label_speciality);
		this.main_panel.add(speciality);
		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					Add.addDoctor(new Doctor(surname.getText(), name.getText(), phone.getText(), address.getText(), speciality.getText()));
					//Testing addDoctor
					//Doctor d = new Doctor("bernard","michel","0123456789","10 avenue Coquelicots 75015 Paris","cardiologue");
					//Add.addDoctor(d);
					setVisible(false);
					current_window.updateTableEmployee();
				}catch(SQLException e){
					System.out.println("SQL ISSU AT THE DOCTOR'S ADDITION");
				}
			}
		});
		this.main_panel.add(second_panel);
		this.add(this.main_panel);
	}
}
