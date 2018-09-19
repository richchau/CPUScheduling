import java.util.Comparator;
import java.util.PriorityQueue;

public class HPF  extends Scheduler{

	private PriorityQueue<Process> queue;
	
	public HPF(){
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
