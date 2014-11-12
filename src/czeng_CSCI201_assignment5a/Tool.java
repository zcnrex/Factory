package czeng_CSCI201_assignment5a;

public class Tool extends Infrastructure{
	private String name;
	private int sum, num;
	
	public Tool(){
		this.name = "";
		this.num = 5;
		this.sum = 5;
	}
	
	public Tool(String name){
		this.name = name;
		this.num = 5;
		this.sum = 5;
	}
	
	public Tool(String name, int sum){
		this.name = name;
		this.sum = sum;
		this.num = sum;
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public void setNum(int n){
		this.num = n;
		this.sum = n;
	}
	
	public void takeTool(int n){
		this.num -= n;
	}
	
	public void returnTool(int n){
		this.num += n;
	}
	
	public String getNum(){
		return (num + "/" + sum);
	}
	
	public String getName(){
		return name;
	}
}
