package czeng_CSCI201_assignment5a;

import java.awt.Color;

public class Station extends Infrastructure {
	private String name, status;
	private int time;
	
	public Station(){
		this.name = "";
		this.time = 0;
		this.status = "Open";
	}

	public Station(String name){
		this.name = name;
		this.time = 0;
		this.status = "Open";
	}
	
	public Station(String name, int time){
		this.name = name;
		this.time = time;
		this.status = "Open";
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public void setStatus(String s){
		this.status = s;
	}
	
	public void setTime(int n){
		this.time = n;
	}
	
	public int getTime(){
		return this.time;
	}
	
	public String getName(){
		return status;
	}
	
	public Color getColor(){
		if (this.status.equals("Open"))
			return Color.GREEN;
		else 
			return Color.RED;
//		return Color.GREEN;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setNum(int n){}
	public String getNum(){return "";}
}
