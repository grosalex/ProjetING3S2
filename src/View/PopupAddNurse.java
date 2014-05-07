package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Add;
import model.Infirmier;

public class PopupAddNurse extends PopupAddPerson{
	private JLabel label_code_service = new JLabel("code service:");
	private JTextField code_service = new JTextField();
	
	private JLabel label_rotation = new JLabel("rotation:");
	private JTextField rotation = new JTextField();
	
	private JLabel label_salaire = new JLabel("salaire:");
	private JTextField salaire = new JTextField();
	public PopupAddNurse(final Window current_window){
		super(current_window);
		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Add.addNurse(new Infirmier(0, surname.getText(), name.getText(),phone.getText(), address.getText(), 
							rotation.getText(), Integer.parseInt(code_service.getText()), Float.parseFloat(salaire.getText())));
					setVisible(false);
					current_window.updateTableEmployee();
				} catch (NumberFormatException | SQLException e) {
					// TODO Bloc catch généré automatiquement
					e.printStackTrace();
				}
			}
		});
		this.main_panel.add(label_code_service);
		this.main_panel.add(code_service);
		this.main_panel.add(label_rotation);
		this.main_panel.add(rotation);
		this.main_panel.add(label_salaire);
		this.main_panel.add(salaire);
		
		this.main_panel.add(second_panel);
		this.add(main_panel);
	}

}
