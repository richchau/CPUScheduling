
public class RR  extends Scheduler{
	private Process[] processArr;
	private float[] rem_bt;
	private int quantum;
	public RR(Process[] aProcessArr, int aQuantum) {
		processArr = aProcessArr;
		rem_bt = new float[processArr.length];
		quantum = aQuantum;
	}
	//
	public void computeAvgWaitingTime(){
		//initialize burst time & remaining burst time array
		float bt[] = new float[processArr.length];
		for (int i = 0;i<processArr.length;i++) {
			rem_bt[i] = processArr[i].getRemainingTime();
			bt[i] = rem_bt[i];
		}
		//initialize wait time array
		float wt[] = new float[processArr.length];
		//initialize time
		float t = 0;
		//check if all processes finished
		while(!processesDone()) {
			// give time slice to each process in numerical order
			for (int i = 0;i<processArr.length;i++) {
				//if remaining burst time for i'th process > quantum
				if (rem_bt[i]>quantum) {
					// add quantum to total time & subtract burst time by quantum
					t = t+quantum;
					rem_bt[i] -=quantum;
				}
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
		float tat[] = new float[processArr.length];
		for (int i = 0; i<processArr.length;i++) {
			//turn around time = burst time + wait time of i'th process
			tat[i] = bt[i]+wt[i];
		}
		//initialize total wait time & total turn around time
		float total_Wt = 0;
		float total_Tat = 0;
		System.out.println("Processes " + " Burst time " +
                " Waiting time " + " Turn around time");
		// calculate total waiting time and total turn around time
		for (int i = 0; i<processArr.length;i++) {
			total_Wt = wt[i]+total_Wt;
			total_Tat = tat[i]+total_Tat;
			System.out.println(" " + (i+1) + "\t\t" + bt[i] +"\t " +
                    wt[i] +"\t\t " + tat[i]);
		}
		// compute and print avg waiting time & avg turn around time
		System.out.println("Average waiting time = " + total_Wt / (float)processArr.length);
		System.out.println("Average turn around time = " + total_Tat / (float)processArr.length);
	}
	public boolean processesDone() {
		boolean truth = true;
		for (Float f: rem_bt) {
			if (f != 0) {
				truth = false;
			}
		}
		return truth;
	}
	public Process getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProcess(Process p) {
		// TODO Auto-generated method stub
		
	}
}
