import java.awt.geom.FlatteningPathIterator;
import java.nio.file.spi.FileSystemProvider;
import java.sql.Time;
import java.util.ArrayList;

/*
 * 
 * First Come First Serve Algorithm
 * 
 */
public class FCFS extends Scheduler{
	
	private ArrayList<Process> processList;
	private Process[] processArr;
	private ArrayList<String> queueList;
	private int throughput;
	
	
	public FCFS(ArrayList<Process> procList){
		processList = procList;
		processArr = new Process[processList.size()];
		for(int i = 0; i < processList.size(); i++){
			processArr[i] = procList.get(i);
		}
		queueList = new ArrayList<>();
		
		simulate();
		computeStatistics();
		printTimeChart();
	
	}
	
	public void computeStatistics(){
		float serviceTime;
		float totWaitingTime = 0;
		float waitingTime;
		float totTurnAroundTime = 0;
		
		serviceTime = processArr[0].getArrivalTime();
		totTurnAroundTime += processArr[0].getExpTotRunTime();
		
		for (int i = 0; i < processArr.length - 1; i++){
			if ((processArr[i].getExpTotRunTime() + serviceTime) < processArr[i+1].getArrivalTime()){
				waitingTime = processArr[i + 1].getArrivalTime() - (processArr[i].getExpTotRunTime() + serviceTime);
				totTurnAroundTime += (waitingTime + processArr[i + 1].getExpTotRunTime());
				totWaitingTime += waitingTime;
				serviceTime = processArr[i + 1].getArrivalTime();
				
				//Stops scheduling if process tries to start after 99 quantas
				if (serviceTime > 99){
					break;
				}
				
			}else{
				waitingTime = ((processArr[i].getExpTotRunTime() + serviceTime) - processArr[i+1].getArrivalTime());
				totTurnAroundTime += (waitingTime + processArr[i + 1].getExpTotRunTime());
				totWaitingTime += waitingTime;
				serviceTime = processArr[i].getExpTotRunTime() + serviceTime;
				
				//Stops scheduling if process tries to start after 99 quantas
				if (serviceTime > 99){
					break;
				}
				
			}
		}
		
		float avgWaitingTime = totWaitingTime / (processArr.length - 1);
		float avgTotTurnAroundTime = totTurnAroundTime / (processArr.length);
		System.out.println("FCFS Average Waiting Time: " + avgWaitingTime);
		System.out.println("FCFS Average Turnaround Time: " + avgTotTurnAroundTime);
		//Wait time and response time are the same in FCFS
		System.out.println("FCFS Average Response Time: " + avgWaitingTime);
		System.out.println("FCFS Throughput: " + throughput);
		
	}
	
	
	public void simulate(){
		 int timeCount = 0;  
	        while (!processList.isEmpty() && timeCount <= 100)  
	        {
	            Process process = processList.remove(0);
	            throughput++;

	            while (process.getArrivalTime() > timeCount) {
	                queueList.add(" ");
	                timeCount++;
	            }

	            while (process.getRemainingTime() > 0) {
	                queueList.add(process.getProcName());
	                process.reduceRemainingTime(1);
	                timeCount++;
	            }
	        }
	        
	}
	

	
	public void printTimeChart(){
		
		//Prints out the quanta labels for time chart
		System.out.println("FCFS Time Chart:");
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
	
	public int getThroughput() {
		return throughput;
	}
	
	@Override
	public Process getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProcess(Process p) {
		// TODO Auto-generated method stub
		
	}
	
}
