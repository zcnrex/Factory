package czeng_CSCI201_assignment5a;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainPanel extends JPanel implements Runnable{
	public MainPanel(){
		this.setLayout(null);
	}
	
	public void run(){
		revalidate();
	}

}
