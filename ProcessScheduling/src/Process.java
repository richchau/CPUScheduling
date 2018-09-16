
public class Process {
	private float arrivalTime;
	private float expTotRunTime; 
	private int priority; 
	private float remainingTime;
	private boolean completed = false;
	private String name;
	
	public Process(float arrivalTime, float expectedTotalRunTime, int priority, String name) {
		this.arrivalTime = arrivalTime;
		expTotRunTime = expectedTotalRunTime;
		this.priority = priority;
		this.name = name;
	}
	
	public float getArrivalTime(){
		return arrivalTime;
	}
	
	public float getExpTotRunTime(){
		return expTotRunTime;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public String getProcName() {
		return name;
	}
	
	public void setArrivalTime(float x){
		arrivalTime = x;
	}
	
	public void setExpTotRunTime(float x) {
		expTotRunTime = x;
	}
	
	public void setPriority(int x) {
		priority = x;
	}
	
	public void setProcName(String name){
		this.name = name;
	}
	
	public float getRemainingTime() {
		return remainingTime;
	}

	public void reduceRemainingTime(float amount) {
		if(remainingTime - amount <= 0) {
			remainingTime = 0;
			completed = true;
		}else {
			remainingTime = remainingTime - amount;
		}
	}

	public boolean isCompleted() {
		return completed;
	}
	
	public String toString() {
		return getProcName();
	}
	
	public String getDataString() {
		return getProcName() + " " + getArrivalTime() + " " + getExpTotRunTime();
	}
}
