import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 
 * Shortest Job First non-preemptive algorithm
 * 
 */
public class SJF extends Scheduler {
	private ArrayList<Process> processList;
	private Process[] processArr;
	private ArrayList<String> queueList;
	private int throughput;
	private PriorityQueue<Process> priorityQueue = new PriorityQueue<Process>(10, SJF.burst);

	public static Comparator<Process> burst = new Comparator<Process>() {

		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getExpTotRunTime() > p2.getExpTotRunTime()) {
				return 1;
			} else if (p1.getExpTotRunTime() < p2.getExpTotRunTime()) {
				return -1;
			}
			return 0;
		}

	};
	



	public void computeStatistics() {

		float serviceTime;
		float totWaitingTime = 0;
		float waitingTime;
		float totTurnAroundTime = 0;
		
		priorityQueue.addAll(Arrays.asList(processArr));
		serviceTime = processArr[0].getArrivalTime();
		totTurnAroundTime += processArr[0].getExpTotRunTime();
		processArr[0] = priorityQueue.poll();
		processArr[1] = priorityQueue.poll();
		processArr[2] = priorityQueue.poll();
		processArr[3] = priorityQueue.poll();
		processArr[4] = priorityQueue.poll();
		for (int i = 0; i < processArr.length - 1; i++) {
			if ((processArr[i].getExpTotRunTime() + serviceTime) < processArr[i+1].getArrivalTime()){

				waitingTime = processArr[i + 1].getArrivalTime() - (processArr[i].getExpTotRunTime() + serviceTime);
				totTurnAroundTime += (waitingTime + processArr[i + 1].getExpTotRunTime());
				totWaitingTime += waitingTime;
				serviceTime = processArr[i + 1].getArrivalTime();
			// Stops scheduling if process tries to start after 99 quantas
			if (serviceTime > 99) {
				break;

			}
		}
		}			

		float avgWaitingTime = totWaitingTime / (processArr.length - 1);
		float avgTotTurnAroundTime = totTurnAroundTime / (processArr.length);
		System.out.println("SJF Average Waiting Time: " + avgWaitingTime);
		System.out.println("SJF Average Turnaround Time: " + avgTotTurnAroundTime);
		// Wait time and response time are the same in FCFS
		System.out.println("SJF Average Response Time: " + avgWaitingTime);
		System.out.println("SJF Throughput: " + throughput);

}
	


	public int getThroughput() {
		return throughput;
	}

	

}
