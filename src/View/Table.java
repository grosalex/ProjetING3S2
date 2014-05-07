package View;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Table extends JTable {
	public Table(Object [][] data, String [] title) {
		super(data,title);

		
	}
	public void hide(String title){
		for(int i=0;i<getColumnCount();i++){
			if(this.getColumnName(i).equals(title)){
				this.getColumnModel().getColumn(i).setMinWidth(0);
				this.getColumnModel().getColumn(i).setWidth(0);
			}
		}

		this.updateUI();
		
	}
	public void show(String title){
		for(int i=0;i<getColumnCount();i++){
			if(this.getColumnName(i).equals(title)){
				this.getColumnModel().getColumn(i).setWidth(30);
			}
		}

		this.updateUI();
	}

}
