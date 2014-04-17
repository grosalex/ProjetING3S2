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

public class Popup extends JDialog{
	private JLabel label_surname = new JLabel("Surname:");
	private JLabel label_name = new JLabel("Name:");
	private JLabel label_phone = new JLabel("Phone:");
	private JPanel main_panel =  new JPanel();
	private JPanel second_panel = new JPanel();
	private JTextField surname= new JTextField();
	private JTextField name = new JTextField();
	private JTextField phone = new JTextField();
	private JButton cancel = new JButton("cancel");
	private JButton save = new JButton("save");
	
	public Popup(){
		this.setSize(300,300);
		this.main_panel.setLayout(new BoxLayout(this.main_panel,BoxLayout.PAGE_AXIS));
		this.second_panel.setLayout(new BoxLayout(this.second_panel, BoxLayout.LINE_AXIS));
		this.main_panel.add(label_surname);
		this.main_panel.add(surname);
		this.main_panel.add(label_name);
		this.main_panel.add(name);
		this.main_panel.add(label_phone);
		this.main_panel.add(phone);
		
		this.cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		this.second_panel.add(cancel);
		this.second_panel.add(save);
		this.main_panel.add(second_panel);
		this.add(this.main_panel);
	
	}

}
