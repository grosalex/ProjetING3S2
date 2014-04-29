	package View;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PopupAddPerson extends JDialog{
	protected JPanel main_panel =  new JPanel();
	protected JPanel second_panel = new JPanel();
	
	private JLabel label_surname = new JLabel("Surname:");
	private JLabel label_name = new JLabel("Name:");
	private JLabel label_phone = new JLabel("Phone:");
	private JLabel label_address = new JLabel("address:");

	protected JTextField surname= new JTextField();
	protected JTextField name = new JTextField();
	protected JTextField phone = new JTextField();
	protected JTextField address = new JTextField();
	protected JButton cancel = new JButton("cancel");
	protected JButton save = new JButton("save");
	
	public PopupAddPerson(){
		this.setSize(300,300);
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
