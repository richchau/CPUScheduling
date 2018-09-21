import java.awt.geom.FlatteningPathIterator;
import java.nio.file.spi.FileSystemProvider;
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
	
	
	public FCFS(ArrayList<Process> procList){
		processList = procList;
		processArr = processList.toArray(processArr);
		queueList = new ArrayList<>();
	
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
		
	}
	
	public void simulate(){
		 int timeCount = 0;  
	        while (!processList.isEmpty() && timeCount <= 100)  
	        {
	            Process process = processList.remove(0);

	            while (process.getArrivalTime() > timeCount) {
	                queueList.add("");
	                timeCount++;
	            }

	            while (process.getExpTotRunTime() > 0) {
	                queueList.add(process.getProcName());
	                process.reduceRemainingTime(1);
	                timeCount++;
	            }
	        }
	        
	     printTimeChart();
	}
	
	public void printTimeChart(){
		for (int i = 0; i < queueList.size(); i++){
			System.out.print(queueList.get(i));
		}
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
