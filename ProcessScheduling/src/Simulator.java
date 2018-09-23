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
		
		/*
		 * RR COMPUTATIONS
		 */
		System.out.println("\n*************************************" + "\n RR Statistics \n" + "************************************* \n");
		System.out.println("SET 1:");
		
		RR rr1 = new RR(list1);
		System.out.println("\n\nSET 2:");
		
		RR rr2 = new RR(list2);
		System.out.println("\n\nSET 3:");
		
		RR rr3 = new RR(list3);
		System.out.println("\n\nSET 4:");
		
		RR rr4 = new RR(list4);
		System.out.println("\n\nSET 5:");
		
		RR rr5 = new RR(list5);
		avgThroughput = (rr1.getThroughput() + rr2.getThroughput() + rr3.getThroughput() + rr4.getThroughput() + rr5.getThroughput()) / 5;
		System.out.println("\n\n***RR Average Throughput: " + avgThroughput);
		
		
		/*
		 * SJF COMPUTATIONS
		 */
		System.out.println("\n*************************************" + "\n SJF Statistics \n" + "************************************* \n");
		System.out.println("SET 1:");
		SJF sjf1 = new SJF(list1);
		
		System.out.println("\n\nSET 2:");
		SJF sjf2 = new SJF(list2);
		
		System.out.println("\n\nSET 3:");
		SJF sjf3 = new SJF(list3);
		
		System.out.println("\n\nSET 4:");
		SJF sjf4 = new SJF(list4);
		
		System.out.println("\n\nSET 5:");
		SJF sjf5 = new SJF(list5);
		
		avgThroughput = (sjf1.getThroughput() + sjf2.getThroughput() + sjf3.getThroughput() + sjf4.getThroughput() + sjf5.getThroughput()) / 5;
		System.out.println("\n\n***SJF Average Throughput: " + avgThroughput);
		
		
		/*
		 * SRT COMPUTATIONS
		 */
		
		
		/*
		 * HPF COMPUTATIONS
		 * 
		 */
		
		System.out.println("\n*************************************" + "\n HPF Statistics \n" + "************************************* \n");
		System.out.println("SET 1:");
		HPF hpf1 = new HPF(list1);
		
		System.out.println("\n\nSET 2:");
		HPF hpf2 = new HPF(list2);
		
		System.out.println("\n\nSET 3:");
		HPF hpf3 = new HPF(list3);
		
		System.out.println("\n\nSET 4:");
		HPF hpf4 = new HPF(list4);
		
		System.out.println("\n\nSET 5:");
		HPF hpf5 = new HPF(list5);
		
		avgThroughput = (hpf1.getThroughput() + hpf2.getThroughput() + hpf3.getThroughput() + hpf4.getThroughput() + hpf5.getThroughput()) / 5;
		System.out.println("\n\n***HPF Average Throughput: " + avgThroughput);
		
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
