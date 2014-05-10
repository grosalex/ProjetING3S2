package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Add;

public class PopupAddFollowUp extends JDialog {
	Window current_window=null;
	private JPanel main_panel =  new JPanel();

	private JLabel label_patient_id = new JLabel("Patient ID");
	private JLabel label_doc_id = new JLabel("Doctor ID");

	private JTextField patient_id = new JTextField();
	private JTextField doc_id = new JTextField();

	private JButton cancel = new JButton("cancel");
	private JButton save = new JButton("save");

	public PopupAddFollowUp(Window current) {
		this.current_window=current;
		this.setSize(600,300);
		this.main_panel.setLayout(new BoxLayout(this.main_panel,BoxLayout.PAGE_AXIS));

		this.main_panel.add(label_patient_id);
		this.main_panel.add(patient_id);
		this.main_panel.add(label_doc_id);
		this.main_panel.add(doc_id);

		this.cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Add.addFollowUp(Integer.parseInt(doc_id.getText()), Integer.parseInt(patient_id.getText()));
					setVisible(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		this.main_panel.add(cancel);
		this.main_panel.add(save);	
		this.add(main_panel);
	}
}
