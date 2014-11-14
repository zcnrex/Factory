package czeng_CSCI201_assignment5a;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Worker extends Thread{
	private int[] pos;
	private Task t;
	private ImageIcon img;
	private Vector<int[]> posList = new Vector<int[]>();
	
	Worker(){
		pos = new int[2];
		pos[0] = 0;
		pos[1] = 30;
		img = new ImageIcon("images/worker.png");
		
	}
	
	public int[] getPosition(){
		return pos;
	}
	
	public void setTask(String s){
		
	}
	
	public void drawWorker(Graphics g){
		g.drawImage(img.getImage(), pos[0], pos[1], null);
	}
	
	public void setPosition(int x, int y){
		int[] p = new int[2];
		p[0] = x;
		p[2] = y;
		posList.add(p);
 	}
	
	public void run(){
		try {
			int[] p = new int[2];
			for (int i = 0; i < posList.size(); i++){
				p = posList.get(i);
			while(pos[1] < p[1]){
				sleep(20);				
				pos[1]++;
			}
			while(pos[0] < p[0]){
				sleep(20);				
				pos[0]++;
			}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
