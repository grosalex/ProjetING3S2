package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Resultat;

//on a un problème pour les listeners 
/*
 * deux solution je me fixe un attribut string qui contient le type et je creer l'objet qui va bien
 */
public class Table extends JTable {
	private String type=null;
	public Table(Object [][] data, String [] title,final String type) {
		super(data,title);
		this.type=type;
		this.setIntercellSpacing(new Dimension(5,5));

		this.addButton();
		
	}
	public void addButton(){
		Action modify = new AbstractAction()
		{
		    @Override
			public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("test");
		    	int modelRow = Integer.valueOf(e.getActionCommand());
		    	TableModel model = getModel();
		    	if(type.equals("docteur")){
		    		System.out.println("Docteur id = + "+ model.getValueAt(modelRow, 0));
		    	}
		    	else if(type.equals("infirmier")){
		    		
		    	}
		    	else if(type.equals("malade")){
		    		
		    	}
		    }
		};
		Action delete = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		ButtonColumn deleteButtonColumn = new ButtonColumn(this, modify, this.getColumnCount()-2);
		ButtonColumn modifyButtonColumn = new ButtonColumn(this, delete, this.getColumnCount()-1);
	}
	public void hide(String title){
		for(int i=0;i<getColumnCount();i++){
			if(this.getColumnName(i).equals(title)){
				this.getColumnModel().getColumn(i).setMinWidth(0);
				this.getColumnModel().getColumn(i).setMaxWidth(0);
			}
		}

		this.updateUI();
		
	}
	public void show(String title){
		for(int i=0;i<getColumnCount();i++){
			if(this.getColumnName(i).equals(title)){
				this.getColumnModel().getColumn(i-1).setMinWidth(this.getColumnModel().getColumn(i-1).getWidth()-50);
				this.getColumnModel().getColumn(i-1).setMaxWidth(this.getColumnModel().getColumn(i-1).getWidth()-50);
				this.getColumnModel().getColumn(i+1).setMinWidth(this.getColumnModel().getColumn(i+1).getWidth()-50);
				this.getColumnModel().getColumn(i+1).setMaxWidth(this.getColumnModel().getColumn(i+1).getWidth()-50);
				this.getColumnModel().getColumn(i).setMinWidth(100);
				this.getColumnModel().getColumn(i).setMaxWidth(100);
				this.getColumnModel().getColumn(i).setWidth(80);
			}
		}

		this.updateUI();
	}
	public void update(Resultat resultat, String type){
		this.type=type;
		this.setModel(new DefaultTableModel(resultat.getResult(), resultat.getTitles()));
		this.addButton();
		this.updateUI();

	}

}
