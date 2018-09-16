
public class Process {
	private float arrivalTime;
	private float expTotRunTime; 
	private int priority; 
	private String name;
	
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
	
	public String toString() {
		return getProcName();
	}
	
	public String getDataString() {
		return getProcName() + " " + getArrivalTime() + " " + getExpTotRunTime();
	}
}
