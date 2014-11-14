package czeng_CSCI201_assignment5a;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class WorkPanel extends JPanel implements Runnable{
//	private ImageIcon[] imgIcon = new ImageIcon[30];
	private Vector<ImageIcon> imgIcon = new Vector<ImageIcon>();
	private Vector<Worker> wokers = new Vector<Worker>();
	private Position position = new Position();
	
	private HashMap<String, int[]> positionMap = position.getAllPositions();
	private HashMap<String, Material> material = new HashMap<String, Material>();
	private HashMap<String, Tool> tool = new HashMap<String, Tool>();
	private HashMap<String, Station> station = new HashMap<String, Station>();
	

	private Tasks tasks = new Tasks();
	
	Worker w = new Worker();
	
	WorkPanel(Tasks tasks){
		this.setLayout(null);	
		this.tasks = tasks;
		w.start();	
	}
	
	public void paintComponent(Graphics g){
		
		Iterator it = positionMap.entrySet().iterator();
		int i = 0;
		
		material = tasks.getMaterials();
		tool = tasks.getTools();
		station = tasks.getStations();
		
		g.setFont(new Font("Courier", Font.PLAIN, 15));
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			String name = (String)pairs.getKey();
			if (name.equals("Anvils") || name.equals("Work benches") || name.equals("Furnaces") ||
					name.equals("Table Saws") || name.equals("Painting Stations") || name.equals("Press")){
				g.setColor(Color.BLACK);
				g.drawString(name, ((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1] + 20);
			}
			else{
				if ((name.charAt(name.length()-1)-'1') >= 0 && (name.charAt(name.length()-1)-'1') <= 4)
					name = name.substring(0, name.length()-1);
				if(name.equals("Painting")){
					imgIcon.add(new ImageIcon("images/Paintingstation.png"));
				}
				else{
					imgIcon.add(new ImageIcon("images/"+name+".png"));
				}
				
				g.drawImage(imgIcon.get(i).getImage(), ((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1], null);
				i++;
				if (((int[])pairs.getValue())[1] == 30){
					g.setColor(Color.BLACK);
					g.drawString((material.get((String)pairs.getKey())).getNum(), ((int[])pairs.getValue())[0] + 12, ((int[])pairs.getValue())[1] + 25);
					g.drawString((material.get((String)pairs.getKey())).getName(), ((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1] - 5);
					
				}
				else if(((int[])pairs.getValue())[0] == 0){
					g.setColor(Color.BLACK);
					g.drawString((tool.get((String)pairs.getKey())).getName(), ((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1] - 5);
					g.drawString((tool.get((String)pairs.getKey())).getNum(), ((int[])pairs.getValue())[0] + 12, ((int[])pairs.getValue())[1] + 25);
				}
				else {
					g.setColor((station.get((String)pairs.getKey())).getColor());
					g.drawString((station.get((String)pairs.getKey())).getName(), ((int[])pairs.getValue())[0] + 3, ((int[])pairs.getValue())[1] - 5);
				}
			}
			
		w.drawWorker(g);
		}
		
	}
	
	public void run(){
		while(true){
			repaint();
			
		}
	}
	
//	public static void main(String[] args){
//		
//	}
}