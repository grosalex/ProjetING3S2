package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;





import model.Add;
import connexion.Connexion;

public class Window extends JFrame{
	private JPanel left_panel=new JPanel();
	private JPanel rightJPanel=new JPanel();
	private JSplitPane main_split=null;
	private Menu menue = new Menu();
	private Table main_table= null;
	private Connexion ma_connection= null;
	private Connection con=null;
	public Window(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "1234");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		///Connection
		/*try{
			this.ma_connection = new Connexion("abruneau", "ab[0AB05", "abruneau-rw", "SQ3EdSFm");
		}catch(SQLException e){
			System.out.println("Sql excption at connection");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}*/
		this.setTitle("Projet ING3 Semestre 2");
		this.setSize(800,600);		
		this.setJMenuBar(menue);
		String [] title= {"Nom","Prenom","Adresse","Telephone"}; 
		Object [][] data = null;
		try{
			data = (new Add(ma_connection).selectAllPersonne());
		}catch(SQLException e){
			System.out.println("problem de sql");
		}
		this.main_table = new Table(data,title);
		this.rightJPanel.add(this.main_table);
		this.main_split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left_panel,rightJPanel);
		this.add(main_split);
		this.main_split.setResizeWeight(0.33);


		this.setVisible(true);
	}

}
