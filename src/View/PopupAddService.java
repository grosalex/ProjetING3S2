package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import model.Add;
import model.Service;
/**
 * 
 * @author grosalex
 *This is the popup where users will be able to add a doctor. It's extending the addPerson popup
 */
public class PopupAddService extends PopupAddPerson{
	/**
	 * Default constructor
	 * @param current_window main window where the added information will be print
	 */
	public PopupAddService(Window window) {
		super(window);
		
		this.label_surname.setText("Name");
		this.label_name.setText("Building");
		this.label_phone.setText("Director ID (Must be a doctor)");
		this.label_address.setText("Code");
		this.main_panel.add(second_panel);
		this.add(main_panel);
		this.save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Service current_service = new Service(nom, batiment, doc_id, code);
				try {
					Add.addService(new Service(surname.getText(),name.getText(),Integer.parseInt(phone.getText()),address.getText()));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
