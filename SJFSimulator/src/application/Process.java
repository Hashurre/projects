package application;
public class Process {
	private int PID;
	private int arrivalTime = 0;
	private int executionTime = 0;
	private static int count;
	private int executedAtTime = -1;
	public Process() {
		Process.count++;
		this.PID = count;
		this.arrivalTime = 0;
		this.executionTime = 0;
		
	}
	public Process(int PID, int arrivalTime, int executionTime) {
		this.PID = PID;
		this.arrivalTime = arrivalTime;
		this.executionTime = executionTime;
	}
	public void setPid(int PID) {
		this.PID = PID;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}
	public void setExecutedAtTime(int c) {
		this.executedAtTime = c;
	}
	
	public int getPid() {
		return this.PID;
	}
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	public int getExecutionTime() {
		return this.executionTime;
	}
	public int getExecutedAtTime() {
		return this.executedAtTime;
	}
	@Override
	public String toString() {
		String f = new String();
		f = "PID" + this.PID + "\nArrival time" + this.arrivalTime + "\nExecution time" + this.executionTime;
		return f;
	}
	
	

}