package main;

import problem.Instance;
import problem.Solution;

public class ParserXML {

	public static void main(String[] args) {
	    
		Instance inst = new Instance("inst/" + args[0].substring(0, args[0].length()-4)+".dat");
		FileBuilder fb = new FileBuilder("tex/" + args[0].substring(0, args[0].length()-4) + ".tex");
		inst.print();
		Solution sol = new Solution(inst);
		sol.parse("sol/" + args[0]);
		sol.print();
		fb.createTEXFile(inst, sol);
	} 
}