import java.util.Comparator;
import java.util.PriorityQueue;

public class SRT  extends Scheduler{

	private PriorityQueue<Process> queue;
	
	public SRT(){
		Comparator<Process> c = new Comparator<Process>(){

			@Override
			public int compare(Process p1, Process p2) {
				if(p1.getRemainingTime() == p2.getRemainingTime()){
					return (int)Math.signum(p1.getPriority() - p2.getPriority());
				}else{
					return (int)Math.signum(p2.getRemainingTime() - p1.getRemainingTime());
				}
			}
			
		};
		queue = new PriorityQueue<Process>(c);
	}
	
	@Override
	public Process getNext() {
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
