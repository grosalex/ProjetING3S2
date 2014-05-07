package View;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.omg.CORBA.FREE_MEM;

import model.Personne;
import connexion.Connexion;
import model.Add;
import model.Doctor;
import model.NoResultException;
import model.Resultat;

public class Window extends JFrame{
	private JPanel left_panel=new JPanel();
	private JPanel rightJPanel=new JPanel();
	private JSplitPane main_split=null;
	private Menu menue = null;

	private Table main_table= null;
	private Selection select_panel=null;
	private Resultat main_resultat=null;
	public Window(){

		this.setMainProperties("Projet ING3 Semestre 2", 800, 600);
		this.menue=new Menu(this);
		this.setJMenuBar(menue);

		this.initializePanels();


		this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
        

        
	}
	
	public void setMainProperties(String title,int width, int height){
		this.setTitle(title);
		this.setSize(width,height);
	}
	public void initializePanels(){
	    this.rightJPanel.setLayout(new BorderLayout());
		this.rightJPanel.add(new ConnectionPanel(this));
		this.main_split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left_panel,rightJPanel);
		this.add(main_split);
		this.main_split.setResizeWeight(0.33);
	}
	
	public void showTableEmploye(){
		try {
			try {
				this.main_resultat=new Resultat(Connexion.getInstance(), "SELECT * FROM docteur");
			} catch (NoResultException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
			this.main_table = new Table(this.main_resultat.getResult(),this.main_resultat.getTitles(),"Docteur");
			this.rightJPanel.add(new JScrollPane(main_table),BorderLayout.CENTER);
			this.rightJPanel.setVisible(true);
			this.left_panel.add(new Selection(this.main_resultat.getTitles(), this));
			this.left_panel.setVisible(true);
			
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	public void updateTableEmployee(){
		Resultat resultat;
		try {
			resultat = new Resultat(Connexion.getInstance(), "SELECT * FROM employe");
			this.main_table.setModel(new DefaultTableModel(resultat.getResult(), resultat.getTitles()));
			this.main_table.updateUI();
		} catch (SQLException | NoResultException e) {
			e.printStackTrace();
		}

	}
	public void showResult(Resultat resultat, String type){
		this.main_table.update(resultat, type);
	}
	public void updateTable(String title, boolean action) {
		if(action){//add
			this.main_table.show(title);
		}
		else {//remove
			this.main_table.hide(title);
		}
		
	}

	
}
