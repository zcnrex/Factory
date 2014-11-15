package czeng_CSCI201_assignment5a;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Worker extends Thread{
	private int[] pos;
	private Task task;
	private ImageIcon img;
	private Vector<int[]> posList = new Vector<int[]>();
	private Position position;
	private Factory factory;
	
	Worker(){
		pos = new int[2];
		pos[0] = 0;
		pos[1] = 30;
		img = new ImageIcon("images/worker.png");
		
	}
	
	Worker(Position position, Factory factory){
		pos = new int[2];
		pos[0] = 0;
		pos[1] = 30;
		img = new ImageIcon("images/worker.png");
		this.position = position;
		this.task = new Task();
		this.factory = factory;
	}
	
	public int[] getPosition(){
		return pos;
	}
	
	public void setTask(Task t){
		this.task = t;
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
//			System.out.println(factory);
//			int[] p = new int[2];
//			for (int i = 0; i < posList.size(); i++){
//				p = posList.get(i);
//				p = position.getPosition(task.getMaterial(i).getName());
			while(pos[1] < 110){
				sleep(10);				
				pos[1]++;
			}
			while(pos[0] < 550){
				sleep(10);				
				pos[0]++;
			}
			sleep(200);
			int[] p = new int[2];
			for (int i = 0; i < factory.getTaskPool().getTasks().size(); i++){
//			int i= 0;
			
//			sleep(100);
			this.setTask(factory.getTaskPool().getTask(i));
//			task = factory.getTaskPool().getTask(0);
			factory.getTaskPool().getTask(i).setStatus("In Progress");
			factory.setJl(i);
			for (Material m : task.getMeterials()){
//				p = posList.get(i);
				p = position.getPosition(m.getName());
				while(pos[0] < (p[0] + 50)){
					sleep(10);				
					pos[0]++;
				}
				while(pos[0] > (p[0] + 50)){
					sleep(10);				
					pos[0]--;
				}
				while(pos[1] < p[1]){
					sleep(10);				
					pos[1]++;
				}
				while(pos[1] > p[1]){
					sleep(10);				
					pos[1]--;
				}
//				System.out.println(task.getMeterials().size());
//				System.out.println(m.getNum());
//				System.out.println(m.getName());
				factory.getTasks().getMaterials().get(m.getName()).use(Integer.parseInt(m.getNum()));
//				factory.setTaskToJSP(factory.getTaskPool());
				sleep(200);
			}

//			System.out.println(task.getTaskPieces().size());
			for (TaskPiece tp : task.getTaskPieces()){
				if (tp.hasTools()){
					for(Tool t : tp.getTools()){
						System.out.println(t.getName());
						
						p = position.getPosition(t.getName());
						while(pos[1] < p[1]){
							sleep(10);				
							pos[1]++;
						}
						while(pos[1] > p[1]){
							sleep(10);				
							pos[1]--;
						}
						while(pos[0] < (p[0] + 50)){
							sleep(10);				
							pos[0]++;
						}
						while(pos[0] > (p[0] + 50)){
							sleep(10);				
							pos[0]--;
						}
						
						sleep(200);
						
					}
				}
				Station s = tp.getStation();
				System.out.println(s.getName());
					
				p = position.getPosition(s.getName()+"1");
				while(pos[1] < p[1]){
					sleep(10);				
					pos[1]++;
				}
				while(pos[1] > p[1]){
					sleep(10);				
					pos[1]--;
				}
				while(pos[0] < (p[0])){
					sleep(10);				
					pos[0]++;
				}
				while(pos[0] > (p[0])){
					sleep(10);				
					pos[0]--;
				}
				for(int k = 0; k < s.getTime(); k++){
//					factory.getTaskPool().getTask(0).getTaskPieces().get(0).getStation().setStatus((s.getTime()-k) + "s");
					factory.getTasks().getStations().get(s.getName()+"1").setStatus((s.getTime()-k) + "s");
//					factory.se
					sleep(1000);
				}
				factory.getTasks().getStations().get(s.getName()+"1").setStatus("Open");
			}
			while(pos[0] < 550){
				sleep(10);				
				pos[0]++;
			}
			while(pos[1] > 110){
				sleep(10);				
				pos[1]--;
			}
			
			factory.getTaskPool().getTask(i).setStatus("Complete!");
			factory.setJl(i);
			sleep(200);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
