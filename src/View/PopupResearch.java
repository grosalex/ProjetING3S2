package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connexion.Connexion;
import Recherche.Recherche;

public class PopupResearch extends JDialog{
	private JPanel main_panel=new JPanel();
	private JPanel panel_doctor=new JPanel();
	private JPanel panel_patient=new JPanel();
	private JPanel panel_nurse=new JPanel();
	//global
	private JLabel label_surname=new JLabel("surname:");
	private JTextField surname=new JTextField();
	private JLabel label_name = new JLabel("name");
	private JTextField name = new JTextField();
	private JLabel label_phone = new JLabel("telephone");
	private JTextField phone = new JTextField();
	private JLabel label_address = new JLabel("address:");
	private JTextField address = new JTextField();
	//doctor
	protected JLabel label_speciality = new JLabel("speciality:");
	protected JTextField speciality = new JTextField();
	//patient
	protected JLabel label_mutual = new JLabel("mutual");
	protected JTextField mutual = new JTextField();
	//infirmier
	private JLabel label_code_service = new JLabel("code service:");
	private JTextField code_service = new JTextField();
	private JLabel label_rotation = new JLabel("rotation:");
	private JTextField rotation = new JTextField();
	private JLabel label_salaire = new JLabel("salaire:");
	private JTextField salaire = new JTextField();
	private JComboBox combo=new JComboBox();
	private JButton cancel = new JButton("cancel");
	private JButton search = new JButton("search");
	public PopupResearch() {
		this.setSize(400,400);
		combo.addActionListener(new ItemAction());
		combo.addItem("Doctor");
		combo.addItem("Patient");
		combo.addItem("Nurse");
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				///todo recherche
				//name.getText() pour avoir la string contenu dans le champ name
				
				Recherche current = new Recherche(Connexion.getInstance());
				//current.rechercher(, 0)
			}
		});
		this.main_panel.setLayout(new BoxLayout(this.main_panel,BoxLayout.PAGE_AXIS));
		this.main_panel.add(combo);
		this.main_panel.add(label_surname);
		this.main_panel.add(surname);
		this.main_panel.add(label_name);
		this.main_panel.add(name);
		this.main_panel.add(label_phone);
		this.main_panel.add(phone);
		this.main_panel.add(label_address);
		this.main_panel.add(address);
		
		this.panel_doctor.setLayout(new BoxLayout(this.panel_doctor,BoxLayout.PAGE_AXIS));
		this.panel_doctor.add(label_speciality);
		this.panel_doctor.add(speciality);
		
		this.panel_patient.setLayout(new BoxLayout(this.panel_patient,BoxLayout.PAGE_AXIS));
		this.panel_patient.add(label_mutual);
		this.panel_patient.add(mutual);
		
		
		this.panel_nurse.setLayout(new BoxLayout(this.panel_nurse,BoxLayout.PAGE_AXIS));
		this.panel_nurse.add(label_code_service);
		this.panel_nurse.add(code_service);
		this.panel_nurse.add(label_salaire);
		this.panel_nurse.add(salaire);
		this.panel_nurse.add(label_rotation);
		this.panel_nurse.add(rotation);
		
		
		this.main_panel.add(panel_doctor);
		this.main_panel.add(panel_nurse);
		this.main_panel.add(panel_patient);
		this.main_panel.add(cancel);
		this.main_panel.add(search);
		this.add(main_panel);

		this.setVisible(true);
		panel_nurse.setVisible(false);
		panel_patient.setVisible(false);
	}
	class ItemState implements ItemListener{
	    @Override
		public void itemStateChanged(ItemEvent e) {
	      System.out.println("événement déclenché sur : " + e.getItem());
	    }               
	  }
	class ItemAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		      if(combo.getSelectedItem()=="Doctor"){
		    	  panel_doctor.setVisible(true);
		    	  panel_nurse.setVisible(false);
		    	  panel_patient.setVisible(false);
		      }
		      else if(combo.getSelectedItem()=="Patient"){
		    	  panel_doctor.setVisible(false);
		    	  panel_nurse.setVisible(false);
		    	  panel_patient.setVisible(true);		      }
		      else if (combo.getSelectedItem()=="Nurse") {
		    	  panel_doctor.setVisible(false);
		    	  panel_nurse.setVisible(true);
		    	  panel_patient.setVisible(false);			}
		}
	}

}
