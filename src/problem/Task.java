package problem;

public class Task {

	public int num;
	public int time;
	public boolean isUnc;
	
	public Task(int n, int t){
		num = n;
		time = t;
		isUnc = false;
	}
	
	public void setup(int n, int time){
		num = n;
		this.time = time;
	}
}
