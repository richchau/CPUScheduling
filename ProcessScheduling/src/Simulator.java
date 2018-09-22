import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Simulator {

    public static String printOutput(List<Process> processList) {
        String result = "";
        String string = String.format("%3s", "") + "Name" + String.format("%3s", "")
                + String.format("%3s", "") + "Priority" + String.format("%3s", "")
                + String.format("%3s", "") + "Arrival Time" + String.format("%3s", "")
                + String.format("%3s", "") + "Expected Total Run Time" + String.format("%3s", "")
                + "\n";

        for (Process process : processList) {
            result = result + String.format("%3s", "") + String.format("%3s", process.getProcName()) + String.format("%4s", "")
                    + String.format("%7s", "") + String.format("%1s", process.getPriority()) + String.format("%6s", "")
                    + String.format("%4s", "") + String.format("%10f", process.getArrivalTime()) + String.format("%4s", "")
                    + String.format("%10s", "") + String.format("%10f", process.getExpTotRunTime()) + String.format("%9s", "")
                    + "\n";
        }
        result = string + result;
        return result;
    }
	
	public static void main(String[] args) {

		ProcessGenerator pg = new ProcessGenerator();
		ArrayList<Process> list1 = pg.getNewProcessArrayList();
		ArrayList<Process> list2 = pg.getNewProcessArrayList();
		ArrayList<Process> list3 = pg.getNewProcessArrayList();
		ArrayList<Process> list4 = pg.getNewProcessArrayList();
		ArrayList<Process> list5 = pg.getNewProcessArrayList();
		
		
		System.out.println("Generated Sets of Simulated Processes" +  "\n");
		
		System.out.println("Set 1:");
		System.out.println(printOutput(list1) + "\n");
		System.out.println("Set 2:");
		System.out.println(printOutput(list2) + "\n");
		System.out.println("Set 3:");
		System.out.println(printOutput(list3) + "\n");
		System.out.println("Set 4:");
		System.out.println(printOutput(list4) + "\n");
		System.out.println("Set 5:");
		System.out.println(printOutput(list5) + "\n");

		/*
		 * FCFS COMPUTATIONS
		 */
		System.out.println("*************************************" + "\n FCFS Statistics \n" + "************************************* \n");
		System.out.println("SET 1:");
		FCFS fcfs1 = new FCFS(list1);
		
		System.out.println("\n\nSET 2:");
		FCFS fcfs2 = new FCFS(list2);
		
		System.out.println("\n\nSET 3:");
		FCFS fcfs3 = new FCFS(list3);
		
		System.out.println("\n\nSET 4:");
		FCFS fcfs4 = new FCFS(list4);
		
		System.out.println("\n\nSET 5:");
		FCFS fcfs5 = new FCFS(list5);
		
		double avgThroughput = (fcfs1.getThroughput() + fcfs2.getThroughput() + fcfs3.getThroughput() + fcfs4.getThroughput() + fcfs5.getThroughput()) / 5;
		System.out.println("\n\n***FCFS Average Throughput: " + avgThroughput);
		
		
		
		
		//LinkedList<Scheduler> schedulers = new LinkedList<Scheduler>();
		
		/*
		for (Scheduler s : schedulers) {
			//Create all the processes for this run
			pg.createProcesses(100);
			Process current = null;
			
			// Simulates running 100 time slices
			for (int time = 0; time < 100; time++) {
				
				//Simulates processes arriving to the queue
				while(pg.peek().getArrivalTime() <= time) {
					s.addProcess(pg.remove());
				}
				
				//Get the process that is going to run in the current time slice
				current = s.getNext();
				
				//If a process is running right now, reduce it's remaining time by 1
				if(current != null) {
					current.reduceRemainingTime(1);
				}
			}
		}
		*/
	}
}
