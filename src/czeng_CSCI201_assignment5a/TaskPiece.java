package czeng_CSCI201_assignment5a;

import java.io.Serializable;
import java.util.Vector;

public class TaskPiece implements Serializable{
	private Vector<Tool> tools = new Vector<Tool>();
	private Vector<Station> stations = new Vector<Station>();
	
	public TaskPiece(){
		super();
//		tools = new Vector<Tool>();
//		stations = new Vector<Station>();
	}
	
	public void setTool(String st, int num){
		Tool t = new Tool(st, num);
		tools.add(t);
	}
	
	public void setStation(String s, int num){
		Station st = new Station(s, num);
		stations.add(st);
	}
	
	public Station getStation(){
		return stations.lastElement();
	}
	
	public boolean hasTools(){
		return !(tools == null);
	}

	public Vector<Tool> getTools(){
		return tools;
	}
}
