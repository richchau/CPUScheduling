/*
 * 
 * Generates processes and adds them to priority queue
 * 
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class ProcessGenerator {
	
	private PriorityQueue<Process> processPriorityQueue;
	
	public ProcessGenerator(){
		
		//Custom comparator to compare process arrival times
		Comparator<Process> arrivalTimeComparator = new Comparator<Process>(){
			@Override
			public int compare(Process p1, Process p2) {
				if (p1.getArrivalTime() > p2.getArrivalTime()){
					return 1;
				}
				if (p1.getArrivalTime() < p2.getArrivalTime()){
					return -1;
				}
				return 0;
			}
			
		};
		
		processPriorityQueue = new PriorityQueue<>(arrivalTimeComparator);
	}
	
	//Method to generate processes
	public void createProcess(int numOfProc){
		
		Random r = new Random();
		
		for (int i = 0; i < numOfProc; i++){
			Process process = new Process();
			
			//Randomly generates process arrival time
			float minArrTime = 0;
			float maxArrTime = 99;
			float randArrivalTime = minArrTime + r.nextFloat() * (maxArrTime - minArrTime);
			
			//Randomly generates process runtime
			float minRunTime = 0.1f;
			float maxRunTime = 10;
			float randRunTime = minRunTime + r.nextFloat() * (maxRunTime - minRunTime);
			
			//Randomly generates process priority 
			int minPriority = 1;
			int maxPriority = 4;
			int randPriority = minPriority + r.nextInt((maxPriority - minPriority) + 1);
			
			
			//Assign generated values to process
			process.setArrivalTime(randArrivalTime);
			process.setExpTotRunTime(randRunTime);
			process.setPriority(randPriority);
			process.setProcName(Integer.toString(i));
			
			processPriorityQueue.add(process);
		}
	}
	
	//Returns the process queue as an array 
	public Process[] getProcessArray(){
		Process[] procArray = new Process[processPriorityQueue.size()];
		for(int i = 0; i < procArray.length; i++){
			procArray[i] = processPriorityQueue.remove();
		}
		return procArray;
	}
	
	//Test: prints queue to test results
	public void printPriorityQueue(){
		while(!processPriorityQueue.isEmpty()){
			Process p = processPriorityQueue.remove();
			System.out.println(p.getProcName() + " " + p.getArrivalTime() + " " + p.getExpTotRunTime());
		}
	}
	
}
