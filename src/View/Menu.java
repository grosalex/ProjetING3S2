package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	private JMenu file=new JMenu("file");
	
	private JMenu add = new JMenu("add");
	private JMenuItem add_doctor = new JMenuItem("doctor");
	private JMenuItem add_nurse = new JMenuItem("nurse");
	private JMenuItem add_patient = new JMenuItem("patient");
	private JMenuItem add_appointment = new JMenuItem("appointment");
	private JMenuItem research = new JMenuItem("research (readonly)");
	private JMenu request = new JMenu("available request");
	
	private JMenuItem maaf = new JMenuItem("MAAF");
	private JMenuItem night_nurse = new JMenuItem("Nurse working at night");
	private JMenuItem service_description = new JMenuItem("service description");
//	private JMenuItem 
	/// TODO faire le menu deroulant qui va bien pour les requetes
	private JMenuItem history = new JMenuItem("history");
	private JMenuItem personal_research =  new JMenuItem("personal research");
	
	private JMenuItem exit = new JMenuItem("exit");
	
	private JMenuItem save=new JMenuItem("save");

	
	private JMenuItem save_as=new JMenuItem("save_as");
	private JMenuItem print = new JMenuItem("print");
	
	public Menu(){
		this.exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		this.add_doctor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupAddDoctor add_doctor = new PopupAddDoctor();
				add_doctor.setVisible(true);
				
			}
		});
		this.add_nurse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupAddNurse add_nurse = new PopupAddNurse();
				add_nurse.setVisible(true);
			}
		});
		this.add_patient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupAddPatient add_patient = new PopupAddPatient();
				add_patient.setVisible(true);
			}
		});
		
		this.add_appointment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Stub de la méthode généré automatiquement
				
			}
		});
		
		this.research.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupResearch research=new PopupResearch();
				research.setVisible(true);
			}
		});
		this.history.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Stub de la méthode généré automatiquement
				
			}
		});
		this.personal_research.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Stub de la méthode généré automatiquement
				
			}
		});
		this.save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Stub de la méthode généré automatiquement
				
			}
		});
		this.save_as.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		this.print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Stub de la méthode généré automatiquement
				
			}
		});
		this.request.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Stub de la méthode généré automatiquement
				
			}
		});
		
		this.add.add(add_doctor);
		this.add.add(add_nurse);
		this.add.add(add_patient);
		this.add.add(add_appointment);
		this.file.add(add);
		this.file.add(research);
		this.file.add(request);
		this.file.add(history);
		this.file.add(personal_research);
		this.file.add(exit);
		this.add(file);
		this.add(save);
		this.add(save_as);
		this.add(print);
	}
	

}
