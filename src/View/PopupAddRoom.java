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
 * 
 * @author grosalex
 * This is the popup where users will be able to add a room. It's extending the addPerson popup
 */
public class PopupAddRoom extends JDialog{
	private Window current_window=null;
	private JPanel main_panel=new JPanel();
	private JLabel label_code=new JLabel("Code service :");
	private JLabel label_surveillant = new JLabel("Surveilant");
	private JLabel label_bed = new JLabel("Beds :");
	
	private JTextField code = new JTextField();
	private JTextField surveilant = new JTextField();
	private JTextField bed = new JTextField();
	
	private JButton cancel=new JButton("cancel");
	private JButton save = new JButton("save");
	/**
	 * Default constructor
	 * @param current_window main window where the added information will be print
	 */
	public PopupAddRoom(Window window) {
		this.current_window=window;
		this.setSize(600,300);

		this.main_panel.setLayout(new BoxLayout(this.main_panel,BoxLayout.PAGE_AXIS));
		
		this.main_panel.add(label_code);
		this.main_panel.add(code);
		this.main_panel.add(label_surveillant);
		this.main_panel.add(surveilant);
		this.main_panel.add(label_bed);
		this.main_panel.add(bed);
		this.main_panel.add(cancel);
		this.main_panel.add(save);
		this.add(main_panel);
		this.save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Stub de la méthode généré automatiquement
				
			}
		});
		this.cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Stub de la méthode généré automatiquement
				setVisible(false);
			}
		});

		//Chambre chambre = new Chambre(code_service, surveillant, nb_lits)
	}

}
