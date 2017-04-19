package problem;

import java.util.ArrayList;

public class Block {

	//================
	//===ATTRIBUTES===
	//================
	private int index;
	public ArrayList<Integer> taskList;
	public int time;
	boolean isEmpty;
	
	//constructor
	public Block(int ind){
		index = ind;
		isEmpty = true;
		taskList = new ArrayList<Integer>();
	}
	
	//================
	//=====METHODS====
	//================
	
	public void print(){
		System.out.println("Block " + index);
		System.out.println("Task list:");
		for(int i = 0; i < taskList.size(); i++)
			System.out.print(taskList.get(i) + " ");
		System.out.println();
		System.out.println("Time: " + time);
		System.out.println("Is empty? " + isEmpty);
		System.out.println("---End of block---");
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public void addTask(int taskIndex){
		//System.out.println("Inserted index: " + taskIndex);
		taskList.add(taskIndex);
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public void setNonEmpty(){
		isEmpty = false;
	}
}
