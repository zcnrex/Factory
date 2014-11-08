package czeng_CSCI201_assignment5a;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
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
	
	private JPanel mainPanel, workPanel, jspPanel;
	private JPanel boardPanel;
	private JLabel boardLabel;
	private ArrayList<JLabel> taskList;
	
	private StringTokenizer sToken;
	private String taskName;
		
	public Factory(){
		super("Factory");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
		
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

//		jsp = new JScrollPane(new JLabel("s1241df"));
		workPanel = new JPanel();
		workPanel.setPreferredSize(new Dimension(600, 600));
		
		mainPanel.add(workPanel);
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

					jsp.setVisible(false);
					getFile();
					jsp.setVisible(true);	
			
				}
			}
		});
		setVisible(true);
	}
	
	
	//Read and parse files 
	public void getFile(){
		
		sb = new StringBuffer();
		sList = new ArrayList<String>();
		
		//iterate through all files
		for (File file : f){
			
			//Dont't read hidden files
			if (!file.isHidden() ){
				try {
					br = new BufferedReader(new FileReader(file));
				
					String s = null;
					while ((s = br.readLine()) != null){
						sb.append(s);
						sb.append(" ");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			sList.add(sb.toString());
			sb = new StringBuffer();
		}
		
		for(int i = 0; i < sList.size(); i++){
			
			sToken = new StringTokenizer(sList.get(i));
//			while (sToken.hasMoreTokens()){
				//Read the first  token in a string
				String token = sToken.nextToken();
//				if (token.equals("[Widget]") || token.equals("[Cog]") || token.equals("[Gadget]")){
				if (token.length()<9){
					taskName = token.substring(1, token.length()-1);
					setTaskToJSP(taskName, sToken.nextToken());
				}
				else if (!token.substring(1, 8).equals("Workers")){
					taskName = token.substring(1, token.length()-1);
					setTaskToJSP(taskName, sToken.nextToken());
					System.out.println(taskName + " " + token);
				}
				
		
		}

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
	
	class NewPanel extends JPanel implements Runnable{
		NewPanel(){
			super();
		}
		public void run(){
			while(true){
				repaint();

			}
		}
	}
	
	public static void main(String[] args){
		Factory f = new Factory();
	}
}
