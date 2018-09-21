/*
 * 
 * Generates processes and adds them to priority queue
 * 
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
	public void createProcesses(int numOfProc){
		
		Random r = new Random();
		
		for (int i = 0; i < numOfProc; i++){
			Process process;
			
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
			
			//Converts int to char for process name
			int decVal = i + 65;
			char ch = (char) decVal;
			
			//Assign generated values to process
			process = new Process(randArrivalTime, randRunTime, randPriority,Character.toString(ch));			
			
			processPriorityQueue.add(process);
		}
	}
	
	//Returns the process queue as an array 
	public ArrayList<Process> getNewProcessArrayList(){
		createProcesses(26);
		ArrayList<Process> list = new ArrayList<Process>();
		
		while (!processPriorityQueue.isEmpty()){
			list.add(processPriorityQueue.remove());
		}
		
		processPriorityQueue.clear();
		return list;
	}
	
	public Process peek() {
		return processPriorityQueue.peek();
	}
	
	public Process remove() {
		return processPriorityQueue.remove();
	}
	
	/*
	//Test: prints queue to test results
	public void printPriorityQueue(){
		for(Process p : getProcessArray()) {
			System.out.println(p.getDataString());
		}
	}
	*/
	
}
