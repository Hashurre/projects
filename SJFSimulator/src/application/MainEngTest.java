package application;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class MainEngTest {
public static Process[] array;
public static ArrayList<Process> queue;
public static ArrayList<Process>Runningqueue =  new ArrayList<Process>();
public static int time = 0;
public static ArrayList<Process> hld = new ArrayList<Process>();
public static String print = new String();
//public static ArrayList<Integer> atTime = new ArrayList<Integer>();
	public static void main(String[] args) {		
		Initializer();
		SJF_Scheduler(array);
		PrintController.getResults(print);
		
		


	}
	public static void SJF_Scheduler(Process[] ar) {
		int smaller =-1;
		boolean allArriveSame = true;
		queue =  new ArrayList<Process>(array.length);

		//check to see if all arrived at once case1: all did case 2: all didn't
		for (int i = 0; i < ar.length-1; i++) {
			int firstArrival = ar[0].getArrivalTime();
			if (firstArrival != ar[i].getArrivalTime()) {
				allArriveSame = false;
			}
			
		}
		for (int i = 0; i < ar.length; i++) {
			queue.add(ar[i]);
		}


		//case 1
		if (allArriveSame) {
			sortRP(queue);
			for (int i = 0; i < queue.size();) {
				currentQueue(queue);
				CPUExecution(queue.get(i),i,queue,false);
			}
			return;
		}

		//case 2
		//Sort according to time, then shortest job first, then PID
		//first sort()
		if(allArriveSame == false) {
			for (int i = 0; i < queue.size(); i++) {
				smaller = queue.get(0).getArrivalTime();
				if (smaller>queue.get(i).getArrivalTime()) {
					smaller = queue.get(i).getArrivalTime();
				}
			}

			int c = 0;
			while(true) {
				if(c < 1) {
					start(smaller);
				}
				if(c>= 1) {
					continueQ();
					
				}
				if(Runningqueue.size() == 0) {
					return;
				}
			    currentQueue(Runningqueue);
				for (int i = 0; i < Runningqueue.size();) {
					CPUExecution(Runningqueue.get(i),i,Runningqueue,true);
				}
				c++;
				
			}

			
			
		}
		
		
	}

	public static void Initializer() {
		int numOfProcess = Main.numberOfProcesses; 
		array = new Process[numOfProcess];
		int count = 0;
		while (count < numOfProcess) {
			Process p1 = Main.processesArray[count];
			array[count] = p1;
			count++;
		}

	}
	public static void currentQueue(ArrayList<Process> arr) {
		print+=("Current Queue is ");
		
		for (int i =0; i < arr.size(); i++) {
			print+=(arr.get(i).getPid() + ",");
			
			
		}
		print+="\n";

		
		
		
	}
	public static void CPUExecution(Process c, int index, ArrayList<Process>queue, boolean chck) {
		
		print+=(" 		CPU is Executing " + c.getPid() + "\n");
		c.setExecutedAtTime(time);
		hld.add(c);
		time+=c.getExecutionTime();
		queue.remove(index);
		
		
	}
	public static void sortRP(ArrayList<Process> RPar) {
		Process temp;
		for (int i = 0; i < RPar.size()-1; i++) {
			for (int j = 0; j < RPar.size()-i-1; j++) {
				if (RPar.get(j).getExecutionTime()>RPar.get(j+1).getExecutionTime()) {
					temp = RPar.get(j);
					RPar.set(j, RPar.get(j+1));
					RPar.set(j+1, temp);
				}
				else if (RPar.get(j).getExecutionTime() == RPar.get(j+1).getExecutionTime()) {
					if (RPar.get(j).getPid() > RPar.get(j+1).getPid()) {
						temp = RPar.get(j);
						RPar.set(j, RPar.get(j+1));
						RPar.set(j+1, temp);
					}
				}
				
				
			}
		}
		
	}
	public static void start(int smaller) {
		int i = 0; 
		int count = 0;
		int insize = queue.size();

		while(queue.size()>0) {
			if (count == insize) {
				return;
			}
			
			
			if (queue.get(i).getArrivalTime() <= smaller) {
				print+=(queue.get(i).getPid());
				Runningqueue.add(queue.get(i)); //initial queue made now we sort //queue size decreases but insize== queue size
				queue.remove(i); 
				insize--;

			}
			sortRP(Runningqueue);

			
				
			//queue gets sorted
			i++;
			count++;

			
		}
		
	}
	public static void continueQ() {
		int i =0;
		while(queue.size()>0) {
			if (time>= queue.get(i).getArrivalTime()) {
				Runningqueue.add(queue.get(i));
				queue.remove(i);
				sortRP(Runningqueue);

			}
			else if (time < queue.get(i).getArrivalTime()) {
				return;
			}
		}
	}
	

}
