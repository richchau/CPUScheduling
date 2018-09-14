/*
 * 
 * First Come First Serve Algorithm
 * 
 */
public class FCFS {
	
	private Process[] processArr;
	
	public FCFS(Process[] procArr){
		processArr = procArr;
		computeAvgWaitingTime();
	}
	
	public void computeAvgWaitingTime(){
		float serviceTime;
		float totWaitingTime = 0;
		
		serviceTime = processArr[0].getArrivalTime();
		for (int i = 0; i < processArr.length - 1; i++){
			if ((processArr[i].getExpTotRunTime() + serviceTime) < processArr[i+1].getArrivalTime()){
				totWaitingTime += processArr[i + 1].getArrivalTime() - (processArr[i].getExpTotRunTime() + serviceTime);
				serviceTime = processArr[i + 1].getArrivalTime();
			}else{
				totWaitingTime += ((processArr[i].getExpTotRunTime() + serviceTime) - processArr[i+1].getArrivalTime());
				serviceTime = processArr[i].getExpTotRunTime() + serviceTime;
			}
		}
		
		float avgWaitingTime = totWaitingTime / (processArr.length - 1);
		System.out.println("FCFS Average Waiting Time: " + avgWaitingTime);
		
	}
	
	public void computeAvgTurnaroundTime(){
		
	}
	
	public void computeAvgResponseTime(){
		
	}
	
}
