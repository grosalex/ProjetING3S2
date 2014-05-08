package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Selection extends JPanel{
	private Window current_window = null;
	private ArrayList<JButton> button_list=new ArrayList<>();
	private JPanel top_panel = new JPanel();
	private JPanel bottom_panel = new JPanel(new BorderLayout());
	
	public Selection(String [] titles, Window current) {
		this.current_window=current;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.top_panel.setLayout(new BoxLayout(top_panel, BoxLayout.PAGE_AXIS));
		this.bottom_panel.setLayout(new BoxLayout(bottom_panel, BoxLayout.PAGE_AXIS));
		this.top_panel.add(new JLabel("Visible"));
		this.bottom_panel.add(new JLabel("hide"));
		for(int i=0;i<titles.length-2;i++){
			this.button_list.add(new JButton(titles[i]));
			this.button_list.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//e.getSource().get
					if(((JButton)e.getSource()).getParent().equals(top_panel)){
						top_panel.remove((JButton)e.getSource());
						bottom_panel.add((JButton)e.getSource());
						top_panel.updateUI();
						bottom_panel.updateUI();
						current_window.updateTable(((JButton)e.getSource()).getText(), false);
					}
					else if(((JButton)e.getSource()).getParent().equals(bottom_panel)){
						top_panel.add((JButton)e.getSource());
						bottom_panel.remove((JButton)e.getSource());
						top_panel.updateUI();
						bottom_panel.updateUI();
						current_window.updateTable(((JButton)e.getSource()).getText(), true);

					}
				}
			});
			this.top_panel.add(this.button_list.get(i));
		}
		this.add(top_panel);
		this.add(bottom_panel);
		
	}

	public void update(String [] titles){
		this.button_list.clear();
		this.top_panel.removeAll();
		this.bottom_panel.removeAll();
		this.top_panel.add(new JLabel("Visible"));
		this.bottom_panel.add(new JLabel("hide"));
		for(int i=0;i<titles.length-2;i++){
			this.button_list.add(new JButton(titles[i]));
			this.button_list.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//e.getSource().get
					if(((JButton)e.getSource()).getParent().equals(top_panel)){
						top_panel.remove((JButton)e.getSource());
						bottom_panel.add((JButton)e.getSource());
						top_panel.updateUI();
						bottom_panel.updateUI();
						current_window.updateTable(((JButton)e.getSource()).getText(), false);
					}
					else if(((JButton)e.getSource()).getParent().equals(bottom_panel)){
						top_panel.add((JButton)e.getSource());
						bottom_panel.remove((JButton)e.getSource());
						top_panel.updateUI();
						bottom_panel.updateUI();
						current_window.updateTable(((JButton)e.getSource()).getText(), true);

					}
				}
			});
			this.top_panel.add(this.button_list.get(i));
		}
		this.top_panel.updateUI();
		this.bottom_panel.updateUI();
		this.updateUI();

	}
}
