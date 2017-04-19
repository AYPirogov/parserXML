package problem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Instance {

	public int nT;		//tasks
	public int nM;		//machines
	public int nB;		//blocks
	public float time;	//direct time
	
	public int rMax; 	//capacity
	public int bMax;	//blocks per machine
	
	public ArrayList<Task> taskList;
	
	public Instance(String name){
		File file = new File(name);
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader( new FileReader(file.getAbsoluteFile()));
			String s;
			s = br.readLine();
			nT = Integer.parseInt(s);
			taskList = new ArrayList<Task>();
			
			for(int i = 0; i < nT; i++){
				s = br.readLine();
				taskList.add(new Task(i+1, Integer.parseInt(s)));
				s = br.readLine();
				if (Integer.parseInt(s) == 1) taskList.get(i).isUnc = true;
			}
			s = br.readLine();
			nM = Integer.parseInt(s);
			s = br.readLine();
			nB = Integer.parseInt(s);
			s = br.readLine();
			rMax = Integer.parseInt(s);
			s = br.readLine();
			bMax = Integer.parseInt(s);
			s = br.readLine();
			time = Float.parseFloat(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print(){
		System.out.println("Instance");
		System.out.println("TMB: " + nT + " " + nM + " " + nB);
		System.out.println("rMax: " + rMax);
		System.out.println("bMax: " + bMax);
		System.out.println("Index \t Time");
		for(int i = 0; i< nT; i++){
			System.out.println(taskList.get(i).num + "\t" + taskList.get(i).time + "\t" + taskList.get(i).isUnc);
		}
		System.out.println("End of Instance");
	}
	
}
