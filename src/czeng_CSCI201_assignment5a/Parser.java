package czeng_CSCI201_assignment5a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JLabel;

public class Parser {
	private File[] files = null;

	private StringBuffer sb;
	private BufferedReader br;
	private ArrayList<String> sList;
	
	private Factory factory;
	
	private TaskPool taskPool;

	private StringTokenizer sToken;
	private String taskName, token;
	private Vector<JLabel> jl = new Vector<JLabel>();
	
	
	public Parser(File[] files, Factory factory, TaskPool taskPool){
		this.files = files;
		this.factory = factory;
		this.taskPool = taskPool;
		
		sb = new StringBuffer();
		sList = new ArrayList<String>();
		
		//iterate through all files
		for (File file : files){

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
				sList.add(sb.toString());
			}
			sb = new StringBuffer();
		}
		

		System.out.println("num of files: " + sList.size());
		for(int i = 0; i < sList.size(); i++){
//			System.out.println("num of files: " + sList.size());
			
			sToken = new StringTokenizer(sList.get(i));
//			while (sToken.hasMoreTokens()){
				//Read the first  token in a string
//			System.out.println(i);
				token = sToken.nextToken();
//				sToken.
//				if (token.equals("[Widget]") || token.equals("[Cog]") || token.equals("[Gadget]")){
				if (token.length()<9){
//					System.out.println("before setup task: " + i);
					setupTask(sToken, token);

//					System.out.println("setup task: " + i);
//					System.out.println(taskName + "1 " + token);
				}
//				else if (!token.substring(1, 7).equals("Worker")){
//					taskName = token.substring(1, token.length()-1);
//					factory.setTaskToJSP(taskName, sToken.nextToken());
//					System.out.println(taskName + "2 " + token);
//				}
				else{
//					System.out.println("before setup factory: " + i);

					factory.setupFactory(sToken, token);

//					System.out.println("setup factory: " + i);
					continue;
				}
				
				if (sToken.hasMoreElements()){
				token = sToken.nextToken();
//				continue;
				}
				else continue;
//				while(token.contains(":")){
//					
//				}
		}
//		taskPool.getTask(7).setName("sdfa");
//		taskPool.getTask(3).getMaterial(0).setName("2434");
//		for (int i = 0; i < taskPool.getSize(); i++){
//			System.out.print(taskPool.getTask(i).getName() + "  ");
//			for (Material m : taskPool.getTask(i).getMeterials()){
//			System.out.print(m.getName() + "  ");
//			}
//			System.out.println();
//		}

//		System.out.println(taskPool.getTasks().size());

		System.out.println("number of tasks: " + taskPool.getTasks().size());
		factory.setTaskToJSP();
		
	}
	
	public void setupTask(StringTokenizer st, String tk){
		
		taskName = tk.substring(1, tk.length()-1);
//		factory.setTaskToJSP(taskPool);
		tk = sToken.nextToken();
//		Vector<Material> m = new Vector<Material>();
		Material m;
		TaskPiece tp = new TaskPiece();
		int len = 0;
		for (int j = 0; j < tk.length()-1; j++){
			len += (int) (Math.pow(10, j) * (Character.getNumericValue(tk.charAt(tk.length()-j-1))));
		}

//		System.out.println(len);
//		JLabel[] jl = new JLabel[len];
		Task task = new Task(taskName);
		int num = 0;
		tk = st.nextToken();
		while (tk.contains(":")){
			num = 0;
			m = new Material();
			m.setName(tk.substring(1,tk.indexOf(":")));
//			System.out.println("sfa");
			for (int i = 0; i < tk.length()-2-tk.indexOf(":"); i++){
				num += (int) (Math.pow(10, i) * (Character.getNumericValue(tk.charAt(tk.length()-i-2))));
			}
			m.setNum(num);
			task.addMaterial(m);
//			System.out.println(task.getMeterials().lastElement().getName() + " : " + task.getMeterials().lastElement().getNum());
//			System.out.println(task.getMeterials().get(0).getName() + " : " + task.getMeterials().get(0).getNum());
			
			tk = st.nextToken();
		}
//		Vector<TaskPiece> taskpiece = new Vector<TaskPiece>();
		TaskPiece taskpiece = new TaskPiece();
		int count = 0;
		while(st.hasMoreElements()){
			if (tk.contains("[Use")){
				taskpiece = new TaskPiece();
				task.addTaskPiece(taskpiece);
				tk = st.nextToken();
				if (tk.contains("x")){
					setTools(st, task.getLastTaskPiece(), tk);
					if(st.hasMoreElements()){
					tk = st.nextToken();
					}
					else break;
				}
				else{
					setStation(st, task.getLastTaskPiece(), tk);
					tk = st.nextToken();
				}
				continue;
			}
//			else if (tk.contains("x")){
//				num = 0;
//				for (int i = 0; i < tk.length()-2; i++){
//					num += (int) (Math.pow(10, i) * (Character.getNumericValue(tk.charAt(tk.length()-i-2))));
//				}
//				tk = st.nextToken();
//				task.getLastTaskPiece().setTool(tk, num);
//				
//			}
//		else if (tk.contains("and")){
//			
//		}
//		else if (tk.contains("at")){
//			tk = st.nextToken();
//			
//		}
//			else if (tk.contains("for")){
//			
//		}
//			
		}
//		System.out.println("Station name: " + task.getLastTaskPiece().getStation().getName());
//		System.out.println("Taskpiece number: " + task.getTaskPieces().size());
//		Task[] tasks = new Task[len];
		Task newTask;
//		Task newTask;
		for (int j = 0; j < len; j++){
//			System.out.println("number of tasks: " + len);
//			tasks[j] = new Task();
//			tasks[j].setName(task.getName());
			
//			newTask = new Task();
//			newTask.setName(task.getName());
//			newTask.se
//			newTask.addMaterials(task.getMeterials());
//			for (Material ma : task.getMeterials()){
//				newTask.addMaterial(ma);
//			}
//			newTask.addMaterials(task.getMeterials());
//			newTask.
//			newTask = task;
			
//			for (Material ma : task.getMeterials()){
//				tasks[j].addMaterial(ma);
//			}
			
			taskPool.addTask(Task.copy(task));
//			jl[j] = new JLabel();
//			jl[j].setText(name + "..." + task.getStatus());
//			jspPanel.add(jl[j]);
		}
//		System.out.println("number of taskpieces in last task: " + taskPool.getTask(0).getTaskPieces().size());
		
	}
	
	public void setStation(StringTokenizer st, TaskPiece taskpiece, String stk){
//		String stk;
//		stk = st.nextToken();
		String stationName = stk;
		if (stk.equals("Painting")){
		stk = st.nextToken();
		stk = st.nextToken();
		stk = st.nextToken();
	}
		else{
			stk = st.nextToken();
			stk = st.nextToken();
		}
		int num = 0;
//		System.out.println("stk" + stk);
		for (int i = 0; i < stk.length()-2; i++){
			num += (int) (Math.pow(10, i) * (Character.getNumericValue(stk.charAt(stk.length()-i-3))));
		}
//		System.out.println("set station " + stationName + " : " + num);
		taskpiece.setStation(stationName, num);
	}
	
	public void setTools(StringTokenizer st, TaskPiece taskpiece, String stk){
		int num = 0;
//		for (int i = 0; i < stk.length()-2; i++){
//			num += (int) (Math.pow(10, i) * (Character.getNumericValue(stk.charAt(stk.length()-i-2))));
//		}
		
		while(st.hasMoreElements()){
			if (stk.contains("x")){
				num = 0;
//				System.out.println(stk);
				for (int i = 0; i < stk.length()-1; i++){
					num += (int) (Math.pow(10, i) * (Character.getNumericValue(stk.charAt(stk.length()-i-2))));
				}
				stk = st.nextToken();
				if(stk.contains("Paintbrush")){ stk = "Paintbrush";}
				else if ((stk.charAt(stk.length()-1) == 's') && !stk.contains("Scissor") && !stk.contains("Plier") ){
					stk = stk.substring(0, stk.length()-1);
				}
//				System.out.println("set tools: " +  stk + " numbers: " + num);
				taskpiece.setTool(stk, num);
				stk = st.nextToken();
				if(stk.equals("and")){
					stk = st.nextToken();
					continue;
				}
				else if(stk.equals("at")){
					stk = st.nextToken();
					setStation(st, taskpiece, stk);
					break;
				}
			}
			
		}
	}
}
