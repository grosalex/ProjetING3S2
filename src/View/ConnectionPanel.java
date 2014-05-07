package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Add;
import model.Doctor;
import connexion.Connexion;

public class ConnectionPanel extends JPanel{
	//username pwd db username dbpws
	private JLabel label_username=new JLabel("Ece username :");
	private JLabel label_password = new JLabel("password :");
	private JLabel label_db_username = new JLabel("Data Base Username :");
	private JLabel label_db_pwd = new JLabel("Data Base password :");
	
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JTextField db_username = new JTextField();
	private JPasswordField db_pwd = new JPasswordField();
	
	private JPanel main=new JPanel();
	private JPanel second=new JPanel();
	
	private JButton local = new JButton("Local connection");
	private JButton connect = new JButton("Connect");
	private Window input=null;
	public ConnectionPanel(final Window input){
		this.input=input;
		this.setSize(600,300);
		this.main.setLayout(new BoxLayout(this.main, BoxLayout.PAGE_AXIS));
		this.second.setLayout(new BoxLayout(this.second, BoxLayout.LINE_AXIS));
		
		this.local.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Connexion("wmarques", "Vi.Cati91", "wmarques-rw", "sEaDRBD2");
					
					//Testing addDoctor
					Doctor d = new Doctor("bernard","michel","0123456789",
			        		"10 avenue Coquelicots 75015 Paris","cardiologue");
					Add.addDoctor(d);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Bloc catch généré automatiquement
					e.printStackTrace();
				}
				setVisible(false);
				input.showTableEmploye();
			}
		});
		
		this.connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					new Connexion(username.getText(), new String(password.getPassword()), db_username.getText(),new String(db_pwd.getPassword()) );
				}catch(SQLException | ClassNotFoundException s){
					
				}
				setVisible(false);
				input.showTableEmploye();
			}
		});
		this.second.add(this.local);
		this.second.add(this.connect);
		
		this.main.add(label_username);
		this.main.add(username);
		this.main.add(label_password);
		this.main.add(password);
		this.main.add(label_db_username);
		this.main.add(db_username);
		this.main.add(label_db_pwd);
		this.main.add(db_pwd);
		this.main.add(second);
		this.add(main);
		this.setVisible(true);
	}
		
	
}
