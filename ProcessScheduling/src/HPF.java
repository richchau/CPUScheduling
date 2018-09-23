import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HPF  extends Scheduler{

	private PriorityQueue<Process> queue;
	private ArrayList<Process> processList;
	private ArrayList<Process> tempList;
	private ArrayList<String> queueList;
	private int throughput;
	
	public HPF(ArrayList<Process> procList){
		Comparator<Process> c = new Comparator<Process>(){

			@Override
			public int compare(Process p1, Process p2) {
				return (int)Math.signum(p1.getPriority() - p2.getPriority());
			}
			
		};
		queue = new PriorityQueue<Process>(c);
		
		processList = new ArrayList<>();
		
		for(int i = 0; i < procList.size(); i++){
			processList.add(new Process(procList.get(i).getArrivalTime(), procList.get(i).getExpTotRunTime(), procList.get(i).getPriority(), procList.get(i).getProcName()));
		}
		queueList = new ArrayList<>();
		tempList = new ArrayList<>();
		
		simulate();
		printTimeChart();
	}
	
	public void simulate() {
		int quanta = 0;
		Process highestPriority;
		
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
	            highestPriority = tempList.get(0);
	            throughput++;
	            for (Process q : tempList) {
	                if (q.getPriority() > highestPriority.getPriority()) {
	                    highestPriority = q;
	                }
	            }
	       
	            
	            while (highestPriority.getRemainingTime() > 0) {
	                queueList.add(highestPriority.getProcName());
	                highestPriority.reduceRemainingTime(1);
	                quanta++;
	            }
	          
	            tempList.remove(highestPriority);
	        }
	    }
	    
	}

	public void printTimeChart(){
		
		//Prints out the quanta labels for time chart
		System.out.println("HPF Time Chart:");
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

	public int getThroughput() {
		return throughput;
	}
	
	@Override
	public Process getNext() {
		if(queue.isEmpty())
			return null;
		if(!queue.peek().isCompleted()){
			return queue.peek();
		}else{
			queue.remove();
			return queue.peek();
		}
	}

	@Override
	public void addProcess(Process p) {
		queue.add(p);
	}

}
