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
	private ArrayList<Process> tempList;
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
	
	public SJF(ArrayList<Process> procList){
		processList = new ArrayList<>(procList);
		tempList = new ArrayList<Process>();
		queueList = new ArrayList<String>();
		
		processArr = new Process[processList.size()];
		for(int i = 0; i < processList.size(); i++){
			processArr[i] = processList.get(i);
		}
		
		simulate();
		printTimeChart();
		computeStatistics();
	}


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

	public void simulate() {
		int quanta = 0;
		Process shortestBurst;
		
	    while (quanta < 100) {
	        //adds the data to the queue
	        for (Process p : processList) {
	            if (p.getArrivalTime() < quanta) {
	                tempList.add(p);
	            }
	        }
	        
	        processList.removeAll(tempList);
	        
	        // checks if the queue is empty
	        if (tempList.isEmpty()) {
	            queueList.add(" ");
	            quanta++;
	        } else {
	            shortestBurst = tempList.get(0);
	            throughput++;
	            for (Process q : tempList) {
	                if (q.getExpTotRunTime() < shortestBurst.getExpTotRunTime()) {
	                    shortestBurst = q;
	                }
	            }
	       
	            
	            while (shortestBurst.getRemainingTime() > 0) {
	                queueList.add(shortestBurst.getProcName());
	                shortestBurst.reduceRemainingTime(1);
	                quanta++;
	            }
	          
	            tempList.remove(shortestBurst);
	        }
	    }
	    
	}

	public void printTimeChart(){
		
		//Prints out the quanta labels for time chart
		System.out.println("SJF Time Chart:");
		String quantas = "";
	    for (int i = 0; i < 100; i++) {
	        quantas = quantas + String.format("%3d", i) + " |";
	    }
	    quantas = quantas.substring(0, quantas.length() - 1);
	    System.out.println(quantas);
		
	    //Prints the result of FCFS queue to the time chart 
	    String previous = queueList.get(0);
	    String output = "";
	    for (String string : queueList) {
	        if (string.equals(previous)) {
	            output = output + String.format("%4s", string) + "|";
	        } else {
	            output = output.substring(0, output.length());
	            output = output + String.format("%4s", string) + "|";
	            previous = string;
	        }
	    }
	    output = output.substring(0, output.length() - 1);

	    System.out.println(output);
	    
	}
	
	public Process getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProcess(Process p) {
		// TODO Auto-generated method stub
		/*
		if (processArr.size()==0) {
			p.setNext(p);
			now = p;
			processArr.add(p);
		}else if (processArr.size() == 1){
			p.setNext(processArr.getFirst());
			processArr.add(p);
			processArr.getFirst().setNext(p);
		}else {
			p.setNext(processArr.getFirst());
			processArr.add(p);
			processArr.getLast().setNext(p);
		}
		*/
	}
	

}
