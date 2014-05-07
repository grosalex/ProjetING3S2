package View;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Resultat;

public class Table extends JTable {
	public Table(Object [][] data, String [] title) {
		super(data,title);
		
	/*	
		Action modify = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {

		    }
		};
		ButtonColumn buttonColumn = new ButtonColumn(this, modify, title.length-1);
*/
		
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


}
