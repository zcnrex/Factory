package czeng_CSCI201_assignment5a;

import java.util.HashMap;
import java.util.Vector;

public class Task {
//	private HashMap<String, Material> mList;
//	private HashMap<String, Tool> tList;
//	private HashMap<String, Station> sList;
	private String status, name;
	private int numTask;
	
	private Vector<TaskPiece> taskPieces = new Vector<TaskPiece>();
	private Vector<Material> materials = new Vector<Material>();
	
	public Task(){
		status = "Not Built";
	}
	
	public Task(String name){
		status = "Not Built";
		this.name = name;
//		this.numTask = num;
	}
	
	public void addTaskPiece(TaskPiece tp){
		taskPieces.add(tp);
	}
	
	public TaskPiece getLastTaskPiece(){
		return taskPieces.lastElement();
	}
	public Vector<TaskPiece> getTaskPieces(){
		return taskPieces;
	}
	
	public void addMaterial(Material m){
		materials.add(m);
	}
	
	public void addMaterials(Vector<Material> a){
		materials = a;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String s){
		status = s;
	}
	
	public String getName(){
		return name;
	}
	
	public Vector<Material> getMeterials(){
		return materials;
	}
	
	public Material getMaterial(int i){
		return materials.get(i);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
//	public static void main(String[] args){
//		Task t = new Task();
//		System.out.println(t.getMaterials().get("Wood").getName());
//	}
}
