/*
 * 
 * Main class
 * 
 */
public class Scheduler {

	public static void main(String[] args) {
		ProcessGenerator processGenerator = new ProcessGenerator();
		processGenerator.createProcess(40);
		Process[] procArr = processGenerator.getProcessArray();
		
		//Test: prints out contents of process array (NAME, ARRIVALTIME, TOTALRUNTIME)
		for(int i = 0; i < procArr.length; i++){
			System.out.println(procArr[i].getProcName() + " " + procArr[i].getArrivalTime() + " " + procArr[i].getExpTotRunTime());
		}
	
		//FCFS Algorithm (just 1 run as of right now)
		FCFS fcfs = new FCFS(procArr);
		
		
	}
	

}
