package problem;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Solution {
	
	public ArrayList<Block> blockList;
	public double objectiveValue;
	
	public Solution(Instance inst){
		blockList = new ArrayList<Block>();
		for(int i = 0; i< inst.nB ; i++)
			blockList.add(new Block(i+1));
	}
	
	public void parse(String file){
		try {

			File fXmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("variable");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					//System.out.println("Name : " + eElement.getAttribute("name"));
					/*System.out.println("Index : " + eElement.getAttribute("index"));
					System.out.println("Value : " + eElement.getAttribute("value"));*/
					
					if(eElement.getAttribute("name").charAt(0) == 'r'){
						objectiveValue = Double.parseDouble(eElement.getAttribute("value"));
					}
					
					if(eElement.getAttribute("name").charAt(0) == 'x' && eElement.getAttribute("name").charAt(1) != 'i'){
						if(Integer.parseInt(eElement.getAttribute("value")) == 1){
							String buf = eElement.getAttribute("name").substring(1);
							String[] arr = buf.split(",");
							blockList.get(Integer.parseInt(arr[1])-1).addTask(Integer.parseInt(arr[0]));
							//blockList.get(Integer.parseInt(arr[1])-1).taskList.add(Integer.parseInt(arr[0]));
						}
					}
					
					if(eElement.getAttribute("name").charAt(0) == 'y'){
						if(Integer.parseInt(eElement.getAttribute("value")) == 1){
							blockList.get(Integer.parseInt(eElement.getAttribute("name").substring(1))-1).setNonEmpty();
						}
					}
					
					if(eElement.getAttribute("name").charAt(0) == 't'){
						blockList.get(Integer.parseInt(eElement.getAttribute("name").substring(3))-1).time = Math.round(Float.parseFloat(eElement.getAttribute("value")));
					}
					
					if(eElement.getAttribute("name").charAt(0) == 'D'){
						doNothing();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doNothing(){
		System.out.println("Do Nothing is active");
	}
	
	public void print(){
		System.out.println("This is the solution");
		System.out.println("Objective value: " + objectiveValue);
		for(int i = 0; i < blockList.size(); i++){
			blockList.get(i).print();
		}
	}
}
