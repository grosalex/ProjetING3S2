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

import connexion.Connexion;
import model.Add;
import model.Historisation;
import model.NoResultException;
import model.Patient;
import model.Resultat;

public class PopupAddHosp extends JDialog{
	
	private JPanel main_panel=new JPanel();
	private JPanel second_panel = new JPanel();
	private JLabel label_id = new JLabel("Patient ID");
	protected JTextField id = new JTextField();
	private JLabel label_code_service = new JLabel("Service Code");
	private JTextField code_service = new JTextField();
	private JButton cancel = new JButton("Cancel");
	protected JButton save = new JButton("Save");
	private Window current_window=null;

	public PopupAddHosp(Window window) {
		this.current_window=window;
		this.setSize(600,300);
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
					Add.addHop(new Patient(Integer.parseInt(id.getText())),code_service.getText());
					setVisible(false);
					current_window.updateTableEmployee();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		this.main_panel.setLayout(new BoxLayout(this.main_panel, BoxLayout.PAGE_AXIS));
		this.second_panel.setLayout(new BoxLayout(this.second_panel, BoxLayout.LINE_AXIS));
		this.main_panel.add(label_id);
		this.main_panel.add(id);
		this.main_panel.add(label_code_service);
		this.main_panel.add(code_service);
		this.second_panel.add(cancel);
		this.second_panel.add(save);
		this.main_panel.add(this.second_panel);
		this.add(this.main_panel);

		this.setVisible(true);
	}


}
