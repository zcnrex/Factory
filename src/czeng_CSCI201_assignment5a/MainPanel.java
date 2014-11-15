package czeng_CSCI201_assignment5a;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainPanel extends JPanel implements Runnable{
	private JPanel boardPanel = new JPanel(), jspPanel = new JPanel();
	private JScrollPane jsp;
	private JLabel boardLabel = new JLabel("Task Board");
	public MainPanel(){
		this.setLayout(null);
//
//		boardPanel.setLayout(new BorderLayout());
//		jspPanel.setLayout(new BoxLayout(jspPanel, BoxLayout.Y_AXIS));
//		jsp = new JScrollPane(jspPanel);
//		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		jsp.setMaximumSize(new Dimension(200, 10000));
//		
//		boardPanel.add(boardLabel, BorderLayout.NORTH);
//		boardPanel.add(jsp, BorderLayout.CENTER);
//		boardPanel.setBounds(600, 0, 200, 600);
	}
	
	public void run(){
		while(true){
//			repaint();
			revalidate();
		}
	}

}
