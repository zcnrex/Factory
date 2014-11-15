package czeng_CSCI201_assignment5a;

import java.util.HashMap;

//Actually it stores every materials and tools in the factory
public class Tasks {
	private HashMap<String, Material> mList;
	private HashMap<String, Tool> tList;
	private HashMap<String, Station> sList;
	private String status, name;
	
	public Tasks(){
		mList = new HashMap<String, Material>();
		tList = new HashMap<String, Tool>();
		sList = new HashMap<String, Station>();
		Material[] m = new Material[3];
		m[0] = new Material("Wood");
		mList.put("Wood", m[0]);
		m[1] = new Material("Metal");
		mList.put("Metal", m[1]);
		m[2] = new Material("Plastic");
		mList.put("Plastic", m[2]);
		
//		Tool t = new Tool("Screwdriver");
		Tool[] t = new Tool[5];
		t[0] = new Tool("Screwdriver");
		tList.put(t[0].getName(), t[0]);
		t[1] = new Tool("Hammer");
		tList.put(t[1].getName(), t[1]);
		t[2] = new Tool("Paintbrush");
		tList.put(t[2].getName(), t[2]);
		t[3] = new Tool("Pliers");
		tList.put(t[3].getName(), t[3]);
		t[4] = new Tool("Scissors");
		tList.put(t[4].getName(), t[4]);
		
//		Station s = new Station("Anvil");
		Station[] s = new Station[15];
		s[0] = new Station("Anvil");
		sList.put("Anvil1", s[0]);
		s[1] = new Station("Anvil");
		sList.put("Anvil2", s[1]);
		s[2] = new Station("Workbench");
		sList.put("Workbench1", s[2]);
		s[3] = new Station("Workbench");
		sList.put("Workbench2", s[3]);
		s[4] = new Station("Workbench");
		sList.put("Workbench3", s[4]);
		s[5] = new Station("Furnace");
		sList.put("Furnace1", s[5]);
		s[6] = new Station("Furnace");
		sList.put("Furnace2", s[6]);
		s[7] = new Station("Saw");
		sList.put("Saw1", s[7]);
		s[8] = new Station("Saw");
		sList.put("Saw2", s[8]);
		s[9] = new Station("Saw");
		sList.put("Saw3", s[9]);
		s[10] = new Station("Painting");
		sList.put("Painting1", s[10]);
		s[11] = new Station("Painting");
		sList.put("Painting2", s[11]);
		s[12] = new Station("Painting");
		sList.put("Painting3", s[12]);
		s[13] = new Station("Painting");
		sList.put("Painting4", s[13]);
		s[14] = new Station("Press");
		sList.put("Press1", s[14]);
	}
	
	public void setMaterial(String s, int n){
		Material m = new Material(s);
		
		mList.put(s, m);
	}
	
	public void setTool(String s, int n){
		tList.get(s).setNum(n);
	}
	
	public HashMap<String, Tool> getTools(){
		return tList;
	}
	public HashMap<String, Material> getMaterials(){
		return mList;
	}
	public HashMap<String, Station> getStations(){
		return sList;
	}
	
//	public static void main(String[] args){
//		Task t = new Task();
//		System.out.println(t.getMaterials().get("Wood").getName());
//	}
}