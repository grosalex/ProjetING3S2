package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Window extends JFrame{
	private JPanel left_panel=new JPanel();
	private JPanel rightJPanel=new JPanel();
	private JSplitPane main_split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left_panel,rightJPanel);
	private Menu menue = new Menu();
	public Window(){
		this.setTitle("Projet ING3 Semestre 2");
		this.setSize(800,600);		
		this.setJMenuBar(menue);
		this.add(main_split);
		this.main_split.setResizeWeight(0.33);
		this.setVisible(true);
	}

}
