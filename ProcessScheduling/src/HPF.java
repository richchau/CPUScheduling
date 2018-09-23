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
