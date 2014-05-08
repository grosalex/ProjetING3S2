package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.xml.ws.Service;

public class PopupAddService extends PopupAddPerson{

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
