package View;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class PopupAddNurse extends PopupAddPerson{
	private JLabel label_code_service = new JLabel("code service:");
	private JTextField code_service = new JTextField();
	
	private JLabel label_rotation = new JLabel("rotation:");
	private JTextField rotation = new JTextField();
	
	private JLabel label_salaire = new JLabel("salaire:");
	private JTextField salaire = new JTextField();
	public PopupAddNurse(Window current_window){
		super(current_window);
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
