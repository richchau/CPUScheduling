import java.util.LinkedList;

public class Simulator {

	public static void main(String[] args) {

		ProcessGenerator pg = new ProcessGenerator();
		LinkedList<Scheduler> schedulers = new LinkedList<Scheduler>();

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
				s.getNext();
				
				//If a process is running right now, reduce it's remaining time by 1
				if(current != null) {
					current.reduceRemainingTime(1);
				}
			}
		}
	}
}
