/*
 * 
 * Main class
 * 
 */
public class Scheduler {

	public static void main(String[] args) {
		ProcessGenerator processGenerator = new ProcessGenerator();
		
		/*Test: prints out contents of process array (NAME, ARRIVALTIME, TOTALRUNTIME)
		for(int i = 0; i < procArr.length; i++){
			System.out.println(procArr[i].getProcName() + " " + procArr[i].getArrivalTime() + " " + procArr[i].getExpTotRunTime());
		}
		*/
	
		//FCFS Algorithm
		System.out.println("FCFS Algorithm: " + "\n");
		for (int i = 0; i < 5; i++){
			System.out.println("Run #: " + (i+1));
			processGenerator.createProcess(40);
			Process[] procArr = processGenerator.getProcessArray();
			
			for(int j = 0; j < procArr.length; j++){
				System.out.println(procArr[j].getProcName() + " " + procArr[j].getArrivalTime() + " " + procArr[j].getExpTotRunTime());
			}
			System.out.println();
			FCFS fcfs = new FCFS(procArr);
			System.out.println("\n" + "\n");
		}
	
		
		
	}
	

}
