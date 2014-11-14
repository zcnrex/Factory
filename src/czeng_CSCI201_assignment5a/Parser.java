package czeng_CSCI201_assignment5a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Parser {
	private File[] files = null;

	private StringBuffer sb;
	private BufferedReader br;
	private ArrayList<String> sList;
	
	private Factory factory;
	

	private StringTokenizer sToken;
	private String taskName;
	
	
	public Parser(File[] files, Factory factory){
		this.files = files;
		this.factory = factory;
		
		sb = new StringBuffer();
		sList = new ArrayList<String>();
		
		//iterate through all files
		for (File file : files){

//			System.out.println("asdfa");
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
		

//		System.out.println(sList.size());
		for(int i = 0; i < sList.size(); i++){
			
			sToken = new StringTokenizer(sList.get(i));
//			while (sToken.hasMoreTokens()){
				//Read the first  token in a string
			System.out.println(i);
				String token = sToken.nextToken();
//				sToken.
//				if (token.equals("[Widget]") || token.equals("[Cog]") || token.equals("[Gadget]")){
				if (token.length()<9){
					taskName = token.substring(1, token.length()-1);
					factory.setTaskToJSP(taskName, sToken.nextToken());
				}
				else if (!token.substring(1, 7).equals("Worker")){
					taskName = token.substring(1, token.length()-1);
					factory.setTaskToJSP(taskName, sToken.nextToken());
					System.out.println(taskName + " " + token);
				}
				else{
					factory.setupFactory(sToken);
					continue;
				}
				
				token = sToken.nextToken();
//				while(token.contains(":")){
//					
//				}
		
		}
	}
}
