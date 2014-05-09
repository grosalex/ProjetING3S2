package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
		this.label_surname.setText("Nom :");
		this.label_name.setText("Batiment :");
		this.label_phone.setText("doc_id");
		this.label_address.setText("Code");
		this.main_panel.add(second_panel);
		this.add(main_panel);
		this.save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Stub de la méthode généré automatiquement
				//Service current_service = new Service(nom, batiment, doc_id, code);
			}
		});
		// TODO Stub du constructeur généré automatiquement
	}

}
