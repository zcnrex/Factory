package czeng_CSCI201_assignment5a;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Factory extends JFrame {
	private JMenuBar jmb;
	private JMenuItem openFolder;
	private JScrollPane jsp;
	
	private File[] f;
	private StringBuffer sb;
	private BufferedReader br;
	private ArrayList<String> sList;
	
	private Tasks tasks = new Tasks();
	
	private JPanel jspPanel;
	private MainPanel mainPanel = new MainPanel();
	private WorkPanel workPanel = new WorkPanel(tasks);
	private JPanel boardPanel;
	private JLabel boardLabel;
	private ArrayList<JLabel> taskList;
	
	private StringTokenizer sToken;
	private String taskName;
	
		
	public Factory(){
		super("Factory");
		setSize(800, 630);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		mainPanel = new JPanel();
//		mainPanel.setLayout(null);
		Thread t = new Thread(mainPanel);
		t.start();
		
		jmb = new JMenuBar();
		openFolder = new JMenuItem("Open Folder...");
		jmb.add(openFolder);
		setJMenuBar(jmb);
		
		boardPanel = new JPanel();
		boardPanel.setLayout(new BorderLayout());
		jspPanel = new JPanel();
		jspPanel.setLayout(new BoxLayout(jspPanel, BoxLayout.Y_AXIS));
		jsp = new JScrollPane(jspPanel);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setMaximumSize(new Dimension(200, 10000));
		boardLabel = new JLabel("Task Board");

		boardPanel.add(boardLabel, BorderLayout.NORTH);
		boardPanel.add(jsp, BorderLayout.CENTER);
		boardPanel.setBounds(600, 0, 200, 600);

//		workPanel = new JPanel();
//		workPanel.setPreferredSize(new Dimension(600, 600));
		
//		mainPanel.add(workPanel);
		mainPanel.add(boardPanel);

		add(mainPanel);		

		
		
		f = null;
		openFolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JFileChooser fc = new JFileChooser("/Users/rexzeng/Documents/CS201/czeng_CSCI201_assignment5a");
				fc.setDialogTitle("Choose a file");
//				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
				int choice = fc.showOpenDialog(openFolder);
				if(choice == JFileChooser.APPROVE_OPTION){
					fc.setFileHidingEnabled(true);
					f = fc.getCurrentDirectory().listFiles();
					
//					Parser parser = new Parser(f, this);

					getFile();
			
				}
			}
		});
		setVisible(true);
	}
	
	
	//Read and parse files 
	public void getFile(){
		
		Parser parser = new Parser(f, this);
		
		//Set up containers
//		WorkPanel wp = new WorkPanel();
		workPanel.setBounds(0, 0, 600, 600);
		Thread twp = new Thread(workPanel);
		twp.start();
		mainPanel.add(workPanel);

	}
	
	
	public void setTaskToJSP(String name, String token){
		int len = 0;
		
		//get number of tools needs to be made
		for (int j = 0; j < token.length()-1; j++){
			len = (int) (Math.pow(10, j) * (Character.getNumericValue(token.charAt(token.length()-j-1))));
		}
		
		JLabel[] jl = new JLabel[len];
		for (int j = 0; j < len; j++){
//			System.out.println("len " + len);
			jl[j] = new JLabel();
			jl[j].setText(name + "...Not Built");
			jspPanel.add(jl[j]);
		}
	}
	
	public void setupFactory(StringTokenizer st){
		String s = "";
		int i = 0;
		String substring = "";
//		Tasks t = new Tasks();
		while(st.hasMoreTokens()){
			s = st.nextToken();
			int len = 0;
			
			//get number of tools needs to be made
			for (int j = 0; j < s.length()-2-s.indexOf(":"); j++){
				System.out.println(Character.getNumericValue(s.charAt(s.length()-j-2)));
				len = (int) (Math.pow(10, j) * (Character.getNumericValue(s.charAt(s.length()-j-2))));
			}
			System.out.println(s.substring(1, s.indexOf(":")-1) + " : " + len);
			substring = s.substring(1, s.indexOf(":")-1);
			if (substring.equals("Plier") || substring.equals("Scissor")){
				tasks.setTool(substring+"s", len);
			}
			else if (substring.equals("Paintbrushe")) tasks.setTool("Paintbrush", len);
			else tasks.setTool(substring, len);
		}
	}
	
//	class NewPanel extends JPanel implements Runnable{
//		NewPanel(){
//			super();
//		}
//		public void run(){
//			while(true){
//				repaint();
//				revalidate();
//			}
//		}
//	}
	

	
	public static void main(String[] args){
		Factory f = new Factory();
		ArrayList<Integer> s = new ArrayList<Integer>();
		
	}
}
