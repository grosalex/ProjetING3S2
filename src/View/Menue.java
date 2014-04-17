package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menue extends JMenuBar{
	private JMenu file=new JMenu("file");
	
	private JMenuItem add = new JMenuItem("add");
	private JMenuItem exit = new JMenuItem("exit");
	
	private JMenu edit=new JMenu("edit");
	private JMenuItem doctor=new JMenuItem("doctor");
	private JMenuItem nurse = new JMenuItem("nurse");
	
	private JMenu help=new JMenu("help");
	private JMenuItem gtfo = new JMenuItem("gtfo");
	
	public Menue(){
		this.exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		this.add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Popup add_popup = new Popup();
				add_popup.setVisible(true);;
				
			}
		});
		this.file.add(add);
		this.file.add(exit);
		this.add(file);
		this.edit.add(doctor);
		this.edit.add(nurse);
		this.add(edit);
		this.help.add(gtfo);
		this.add(help);
		
	}

}
