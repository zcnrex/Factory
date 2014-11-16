package czeng_CSCI201_assignment5a;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
	private int workerNum;
	
	
	private Lock lock = new ReentrantLock();
	private Condition stationAvailable = lock.newCondition();
	
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
	
	public void setWorkerNum(int n){
		this.workerNum = n;
	}
	
	public void run(){
		try {
//			System.out.println(factory);
//			int[] p = new int[2];
//			for (int i = 0; i < posList.size(); i++){
//				p = posList.get(i);
//				p = position.getPosition(task.getMaterial(i).getName());
			sleep(workerNum*500+1000);
			while(pos[1] < 80){
				sleep(1);				
				pos[1]++;
			}
			while(pos[0] < 550){
				sleep(1);				
				pos[0]++;
			}
			sleep(200);
			int[] p = new int[2];
			for (int i = 0; i < factory.getTaskPool().getTasks().size(); i++){
	//			int i= 0;
//				System.out.println("i: " + i);
	//			sleep(100);
				this.setTask(factory.getTaskPool().getTask(i));
	//			task = factory.getTaskPool().getTask(0);
				if (!task.getStatus().equals("Not Built")) continue;
				factory.getTaskPool().getTask(i).setStatus("In Progress");
				System.out.println("Task name: "+ task.getName() + " " + task.getStatus());
				factory.setJl(i);
				for (Material m : task.getMeterials()){
	//				p = posList.get(i);
					p = position.getPosition(m.getName());
					move(p, 50, 0);
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
							move(p, 50, 0);
							while(factory.getTasks().getTools().get(t.getName()).getNumber() < t.getNumber()){
								sleep(200);
							}
							factory.getTasks().getTools().get(t.getName()).takeTool(t.getNumber());
							sleep(200);
							
						}
					}
					Station s = tp.getStation();
					System.out.println(s.getName());
						
					p = position.getPosition(s.getName()+"1");
					move(p, 0, -80);
//					stationAvailable.
					//while(lock.acquired)
					//{sleep}
//					lock.
//					Semaphore numTablesSemaphore = new Semaphore(1);
//					numTablesSemaphore.
//					this.wait();
					
//					lock.lock();
//					while(!factory.getTasks().getStations().get(s.getName()+"1").getStatus().equals("Open")){
//						sleep(1000);
//					}
					factory.getTasks().getStations().get(s.getName()+"1").setTime(s.getTime());
					factory.getTasks().getStations().get(s.getName()+"1").inUse(p, pos);
//					lock.
//					while(factory.getTasks().getStations().get(s.getName()+"1").getLock().tryLock()){
//						sleep(100);
//					}
//					move(p, 0, 0);
//					for(int k = 0; k < s.getTime(); k++){
//	//					factory.getTaskPool().getTask(0).getTaskPieces().get(0).getStation().setStatus((s.getTime()-k) + "s");
//						factory.getTasks().getStations().get(s.getName()+"1").setStatus((s.getTime()-k) + "s");
//	//					factory.se
//						sleep(1000);
//					}

//					factory.getTasks().getStations().get(s.getName()+"1").finished();
//					factory.getTasks().getStations().get(s.getName()+"1").setStatus("Open");
//					lock.unlock();
//					factory.getTasks().getStations().get(s.getName()+"1").getLock().unlock();
					if (tp.hasTools()){
						for(Tool t : tp.getTools()){
							System.out.println(t.getName());
							
							p = position.getPosition(t.getName());
							move(p, 50, 0);
							factory.getTasks().getTools().get(t.getName()).returnTool(t.getNumber());
							sleep(200);
							
						}
					}
				}
				while(pos[0] < 550){
					sleep(1);				
					pos[0]++;
				}
				while(pos[1] > 80){
					sleep(1);				
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
	
	public void move(int[] p, int x, int y){
		try {
			while(pos[1] < p[1] + y){
				sleep(1);				
				pos[1]++;
			}
			while(pos[1] > p[1] + y){
				sleep(1);				
				pos[1]--;
			}
			while(pos[0] < (p[0] + x)){
				sleep(1);				
				pos[0]++;
			}
			while(pos[0] > (p[0] + x)){
				sleep(1);				
				pos[0]--;
			}
//			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
