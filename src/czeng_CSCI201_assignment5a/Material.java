package czeng_CSCI201_assignment5a;

public class Material extends Infrastructure{
	private String name;
	private int num;
	
	public Material(){
		this.name = "";
		this.num = 999;
	}
	public Material(String name){
		this.name = name;
		this.num = 999;
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public void setNum(int i){}
	
	public void use(int n){
		this.num -= n;
	}
	
	public String getNum(){
		return (num + "");
	}
	
	public String getName(){
		return name;
	}
}
