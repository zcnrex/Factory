package czeng_CSCI201_assignment5a;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
	
	private JPanel mainPanel, workPanel, jspPanel;
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
		
		mainPanel = new JPanel();
//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
		mainPanel.setLayout(null);
		
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

//		jsp = new JScrollPane(new JLabel("s1241df"));
		workPanel = new JPanel();
		workPanel.setPreferredSize(new Dimension(600, 600));
		
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

					setVisible(false);
					getFile();
					setVisible(true);					
			
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
//				sToken.
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
				else{
					setupFactory(sToken);
					continue;
				}
				
				token = sToken.nextToken();
//				while(token.contains(":")){
//					
//				}
		
		}
		
		//Set up containers
		WorkPanel wp = new WorkPanel();
		wp.setBounds(0, 0, 600, 600);
		mainPanel.remove(boardPanel);
		mainPanel.add(wp);
		mainPanel.add(boardPanel);

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
	
	class WorkPanel extends JPanel{
		private JLabel[] jlWood, jlMetal, jlPlastic, woodName, metalName, plasticName;
		private ImageIcon[] woodIcon, metalIcon, plasticIcon;
		WorkPanel(){
			this.setLayout(null);
			
			
			Position p = new Position();
			HashMap<String, int[]> h = p.getAllPositions();
			Iterator it = h.entrySet().iterator();
			Task t = new Task();
			int i = 0;
			woodIcon = new ImageIcon[300];
			jlWood = new JLabel[300];
			woodName = new JLabel[30];
			
			HashMap<String, Material> material = new HashMap<String, Material>();
			material = t.getMaterials();
			HashMap<String, Tool> tool = new HashMap<String, Tool>();
			tool = t.getTools();
			HashMap<String, Station> station = new HashMap<String, Station>();
			station = t.getStations();
			
			while(it.hasNext()){
//			for (int i = 0; i < 3; i++){
				Map.Entry pairs = (Map.Entry)it.next();
				String name = (String)pairs.getKey();
				if ((name.charAt(name.length()-1)-'1') >= 0 && (name.charAt(name.length()-1)-'1') <= 4)
					name = name.substring(0, name.length()-1);
				if(name.equals("Painting"))
					woodIcon[i] = new ImageIcon("images/Paintingstation.png");
				else
					woodIcon[i] = new ImageIcon("images/"+name+".png");
				if (((int[])pairs.getValue())[1] == 30){
//					System.out.println((String)pairs.getKey());
					jlWood[i] = new JLabel((material.get((String)pairs.getKey())).getNum(), woodIcon[i], SwingConstants.CENTER);
					woodName[i] = new JLabel((material.get((String)pairs.getKey())).getName());
				}
				else if(((int[])pairs.getValue())[0] == 0){
//					System.out.println((String)pairs.getKey());
					jlWood[i] = new JLabel((tool.get((String)pairs.getKey())).getNum(), woodIcon[i], SwingConstants.CENTER);
					woodName[i] = new JLabel((tool.get((String)pairs.getKey())).getName());
				}
				else {
//					System.out.println((String)pairs.getKey());
//					System.out.println((String)pairs.getKey());
//					jlWood[i] = new JLabel((station.get((String)pairs.getKey())).getNum(), woodIcon[i], SwingConstants.CENTER);
					jlWood[i] = new JLabel("", woodIcon[i], SwingConstants.CENTER);
//					jlWood[i] = new JLabel(woodIcon[i], SwingConstants.CENTER);
//					jlWood[i].setText((station.get((String)pairs.getKey())).getNum());
					woodName[i] = new JLabel((station.get((String)pairs.getKey())).getName());
					woodName[i].setForeground((station.get((String)pairs.getKey())).getColor());
				}
				jlWood[i].setHorizontalTextPosition(SwingConstants.CENTER);
				jlWood[i].setVerticalTextPosition(SwingConstants.CENTER);
				jlWood[i].setBounds(((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1], 50, 50);
				
//				if(((String)pairs.getKey()).equals("Painting"))
//					woodName[i] = new JLabel("Painting Station");
//				else
					
				woodName[i].setBounds(((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1] - 20, 80, 20);
				this.add(woodName[i]);
				this.add(jlWood[i]);
				i++;
//				System.out.println(i + " " + ((int[])pairs.getValue())[0] + " " + ((int[])pairs.getValue())[1]);
			}
			
						
			
//			woodIcon = new ImageIcon("images/wood.png");
//			jlWood = new JLabel("100", woodIcon, SwingConstants.CENTER);
//			jlWood.setHorizontalTextPosition(SwingConstants.CENTER);
//			jlWood.setVerticalTextPosition(SwingConstants.CENTER);
//			jlWood.setBounds(140, 20, 50, 50);
//			woodName = new JLabel("Wood");
//			woodName.setBounds(180, 0, 50, 20);
//			this.add(woodName);
//			this.add(jlWood);
//			
//			String s = "metal";
//			metalIcon = new ImageIcon("images/"+s+".png");
//			jlMetal = new JLabel("100", metalIcon, SwingConstants.CENTER);
//			jlMetal.setHorizontalTextPosition(SwingConstants.CENTER);
//			jlMetal.setVerticalTextPosition(SwingConstants.CENTER);
//			jlMetal.setBounds(280, 20, 50, 50);
//			metalName = new JLabel("Metal");
//			metalName.setBounds(305, 0, 50, 20);
//			this.add(metalName);
//			this.add(jlMetal);
//			
//			plasticIcon = new ImageIcon("images/plastic.png");
//			jlPlastic = new JLabel("100", plasticIcon, SwingConstants.CENTER);
//			jlPlastic.setHorizontalTextPosition(SwingConstants.CENTER);
//			jlPlastic.setVerticalTextPosition(SwingConstants.CENTER);
//			jlPlastic.setBounds(420, 20, 50, 50);
//			plasticName = new JLabel("Plastic");
//			plasticName.setBounds(430, 0, 50, 20);
//			this.add(plasticName);
//			this.add(jlPlastic);
		}
	}
	
	public static void main(String[] args){
		Factory f = new Factory();
		ArrayList<Integer> s = new ArrayList<Integer>();
		
//		s.indexOf(s)
//		s.get
//		s.cl
	}
}
