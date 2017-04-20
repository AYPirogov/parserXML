package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import problem.Instance;
import problem.Solution;

public class FileBuilder {

	public String fileName;
	
	public FileBuilder(String name){
		fileName = name;
	}
	
	public void createTEXFile(Instance inst, Solution sol){
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			//bw.append("\\n"); use to append new line in the end of file
			
			bw.append("\\documentclass[a4paper]{article}\n");
			bw.append("\\usepackage{pst-tree}\n");
			bw.append("\\usepackage{pstricks,pstcol,pst-node}\n");
			bw.append("\\usepackage[french]{babel}\n");
			bw.append("\\usepackage{amsfonts,amsmath}\n");
			bw.append("\\usepackage[dvips]{graphicx}\n");
			bw.append("\\usepackage{lscape}\n");
			bw.append("\\pagestyle{empty}\n");
			bw.append("\\begin{document}\n");
			bw.append("\\begin{landscape}\n");
			bw.append("\\begin{figure}[ht!]\n");
			
			float buf = inst.time + 1f;
			int y = inst.nM*inst.rMax + 1;
			
			bw.append("\\psscalebox{" + 22/buf + "}{\n");
			bw.append("\\begin{pspicture*}(-3,-1)(" + buf + "," + y + ")\n");
			bw.append("\\rput[t](0,-0.2){0}\n");
			buf = inst.time + 0.5f;
			bw.append("\\psline{->}(0,0)(" + buf + ",0)\n");
			
			for (int i = 0; i < inst.time; i++)
				bw.append("\\psline(" + i + ",0)(" + i + ",-0.1)\\rput[t](" + i + ",-0.2){" + i + "}\n");
			
			y = y - 1;
			bw.append("\\psline(0,0)(0," + y + ")\n");
			bw.append("\\psline[linestyle=dashed](" + inst.time + ",0)(" + inst.time + "," + y + ")\n");
			
			for	(int j = 1; j <= inst.nM; j++){
				float pos = inst.rMax * 0.5f + (j-1)*inst.rMax;
				bw.append("\\rput[r](-0.2,"+ pos + "){Machine " + j + "}\n");
			}
			
			for (int j = 0; j < inst.nM ; j++){
				for (int k = 0; k < inst.bMax; k++){
					int realNum = j * inst.bMax + k;
					float xBlock = 0f;
					for (int q = 0; q < k ; q++){
						int realNum2 = j * inst.bMax + q;
						xBlock += sol.blockList.get(realNum2).time;
					}
					float yBlock = j * inst.rMax;
					for (int i = 0; i < sol.blockList.get(realNum).taskList.size(); i++){
						int taskTime = inst.taskList.get(sol.blockList.get(realNum).taskList.get(i)-1).time;
						int taskNum = inst.taskList.get(sol.blockList.get(realNum).taskList.get(i)-1).num;
						float x1,x2,y1,y2;
						x1 = xBlock;
						y1 = yBlock + i;
						x2 = x1 + taskTime;
						y2 = y1 + 1;
						float xText, yText;
						xText = x1 + taskTime * 0.5f;
						yText = y1 + 0.5f;
						
						if (inst.taskList.get(sol.blockList.get(realNum).taskList.get(i)-1).isUnc){
							bw.append("\\psframe[framearc=.5, linecolor=black, fillstyle=solid,fillcolor=lightgray](" + x1 + "," + y1 + ")(" + x2  + "," + y2 + ")\n");
							bw.append("\\rput[c](" + xText + "," + yText + "){\\texttt{" + taskNum + "}}\n");
						}
						else {
							bw.append("\\psframe[framearc=.5, linecolor=black](" + x1 + "," + y1 + ")(" + x2  + "," + y2 + ")\n");
							bw.append("\\rput[c](" + xText + "," + yText + "){\\texttt{" + taskNum + "}}\n");
						}
					}
				}
			}
			
			bw.append("\\end{pspicture*}\n");
			bw.append("}\n");
			bw.append("\\end{figure}\n");
			bw.append("\\end{landscape}\n");
			bw.append("\\end{document}\n");

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
}
