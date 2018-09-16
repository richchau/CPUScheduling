/*
 * 
 * Main class
 * 
 */
public abstract class Scheduler {

	public abstract Process getNext();
	public abstract void addProcess(Process p);

}
