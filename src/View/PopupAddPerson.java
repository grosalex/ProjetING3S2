	package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * this popup is used as a based to create add popup
 * @author grosalex
 *
 */
public class PopupAddPerson extends JDialog{
	Window current_window=null;
	protected JPanel main_panel =  new JPanel();
	protected JPanel second_panel = new JPanel();
	
	protected JLabel label_surname = new JLabel("Surname:");
	protected JLabel label_name = new JLabel("Name:");
	protected JLabel label_phone = new JLabel("Phone:");
	protected JLabel label_address = new JLabel("address:");
	
	protected JTextField surname= new JTextField();
	protected JTextField name = new JTextField();
	protected JTextField phone = new JTextField();
	protected JTextField address = new JTextField();
	protected JButton cancel = new JButton("cancel");
	protected JButton save = new JButton("save");
	
	/**
	 * 
	 * @param current is the main window. It's used to update view after adding the data
	 */
	public PopupAddPerson(Window current){
		this.current_window=current;
		this.setSize(600,300);
		this.main_panel.setLayout(new BoxLayout(this.main_panel,BoxLayout.PAGE_AXIS));
		this.second_panel.setLayout(new BoxLayout(this.second_panel, BoxLayout.LINE_AXIS));

		this.main_panel.add(label_surname);
		this.main_panel.add(surname);
		this.main_panel.add(label_name);
		this.main_panel.add(name);
		this.main_panel.add(label_phone);
		this.main_panel.add(phone);
		this.main_panel.add(label_address);
		this.main_panel.add(address);
		
		this.cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		this.second_panel.add(cancel);
		this.second_panel.add(save);	
	}

}
