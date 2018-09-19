import java.util.Comparator;
import java.util.PriorityQueue;

public class HPFNonPreemptive extends Scheduler{

	private PriorityQueue<Process> queue;
	private Process current;
	
	public HPFNonPreemptive(){
		Comparator<Process> c = new Comparator<Process>(){

			@Override
			public int compare(Process p1, Process p2) {
				return (int)Math.signum(p1.getPriority() - p2.getPriority());
			}
			
		};
		queue = new PriorityQueue<Process>(c);
	}
	
	@Override
	public Process getNext() {
		if(current == null || current.isCompleted()){
			current = queue.remove();
		}
		return current;
	}

	@Override
	public void addProcess(Process p) {
		queue.add(p);
	}

}
