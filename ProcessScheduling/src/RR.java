
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class RR  extends Scheduler{
	private Process[] processArr;
	private PriorityQueue<Process> processQue;
	private ArrayList<Process> processList;
	private ArrayList<String> queueList;
	private float[] rem_bt;
	private int quantum =1;
	private int throughput = 0;
	public RR(ArrayList<Process> procList) {
		processList = new ArrayList<>();
		for(int i = 0; i < procList.size(); i++){
			processList.add(new Process(procList.get(i).getArrivalTime(), procList.get(i).getExpTotRunTime(), procList.get(i).getPriority(), procList.get(i).getProcName()));
		}
		Comparator<Process> c = new Comparator<Process>(){

			@Override
			public int compare(Process p1, Process p2) {
				return (int)Math.signum(p1.getArrivalTime() - p2.getArrivalTime());
			}
			
		};
		processQue = new PriorityQueue<Process>(c);
		rem_bt = new float[processList.size()];
		queueList = new ArrayList<>();
		simulate();
		printTimeChart();
	//	computeAvgWaitingTime();
	}
	
	public void simulate(){
		 int timeCount = 0;
		 
	        while (!processList.isEmpty() && timeCount <= 100)  
	        {
	        	
	            Process process = processList.remove(0);
	            
	            throughput++;
	            processQue.add(process);
	            while (processQue.peek().getArrivalTime() > timeCount) {
	                queueList.add(" ");
	                timeCount++;
	            }
	            for (Process p: processQue) {
	            		if (p.getRemainingTime()>0 && p.getArrivalTime()<timeCount) {
	            			p.reduceRemainingTime(1);
	            			queueList.add(p.getProcName());
	            			timeCount++;
				}
	            }
	            
	        }
	        
	}
	public void printTimeChart(){
		
		//Prints out the quanta labels for time chart
		System.out.println("RR Time Chart:");
		String quantas = "";
        for (int i = 0; i < 100; i++) {
            quantas = quantas + String.format("%3d", i) + " |";
        }
        quantas = quantas.substring(0, quantas.length() - 1);
        System.out.println(quantas);
		
        //Prints the result of FCFS queue to the time chart 
        String previous = queueList.get(0);
        String printOut = "";
        for (String string : queueList) {
            if (string.equals(previous)) {
                printOut = printOut + String.format("%4s", string) + "|";
            } else {
                printOut = printOut.substring(0, printOut.length());
                printOut = printOut + String.format("%4s", string) + "|";
                previous = string;
            }
        }
        printOut = printOut.substring(0, printOut.length() - 1);

        System.out.println(printOut);
        
	}
	
		
	/*
	public void computeAvgWaitingTime(){
		//initialize burst time & remaining burst time array
		float bt[] = new float[processList.size()];
		int j = 0;
		
		//initialize wait time array
		float wt[] = new float[processList.size()];
		
		float rt[] = new float[processList.size()];
		float t = 0;
		//check if all processes finished
		while(t<100) {
			for (Process p: processList) {
				if (p.getArrivalTime()<t)
					rem_bt[j] = p.getExpTotRunTime();
					bt[j] = rem_bt[j];
					j++;
			}
			/*
			// give time slice to each process in numerical order
			for (int i = 0;i<processList.size();i++) {
				//if remaining burst time for i'th process > quantum
				if (rem_bt[i]>quantum) {
					// add quantum to total time & subtract burst time by quantum
					t = t+quantum;
					rem_bt[i] -=quantum;
				}// skip if burst time is depleted to 0
				else if(rem_bt[i]==0) {
					rem_bt[i]=rem_bt[i];	
				}
				//else remaining burst time for i'th process < quantum
				else { 
					// finish process by adding remaining burst time to time
					// wait time will equal total time minus burst time of process
					t = t + rem_bt[i];
					wt[i] = t-bt[i];
					rem_bt[i] = 0;
				}
			}
		}
		//initialize turn around time array
		float tat[] = new float[processList.size()];
		for (int i = 0; i<processList.size();i++) {
			//turn around time = burst time + wait time of i'th process
			tat[i] = bt[i]+wt[i];
		}
		//initialize total wait time & total turn around time
		float total_Wt = 0;
		float total_Tat = 0;
		System.out.println("Processes " + " Burst time " +
                " Waiting time " + " Turn around time");
		// calculate total waiting time and total turn around time
		for (int i = 0; i<processList.size();i++) {
			total_Wt = wt[i]+total_Wt;
			total_Tat = tat[i]+total_Tat;
			System.out.println(" " + (i+1) + "\t\t" + bt[i] +"\t " +
                    wt[i] +"\t\t " + tat[i]);
		}
		// compute and print avg waiting time & avg turn around time
		System.out.println("Average waiting time = " + total_Wt / (float)processList.size());
		System.out.println("Average turn around time = " + total_Tat / (float)processList.size());
		
	}
	
	public boolean processesDone() {
		boolean truth = true;
		for (Process p: processList) {
			if (p.getRemainingTime() != 0) {
				truth = false;
			}
		}
		return truth;
	}
	*/
    
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
