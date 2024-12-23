package application;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class AttendanceLogger {
	//🤞🏼 <-- Save this program with this to change from cp1252 encoding to UTF-8 encoding. The encoding is basically for symbols. cp1252 which (to my knowledge)
	//is what java is on due to system locale for MS windows doesn't represent things like emojis. It is very limiting and has only 256 representations whilst
	//UTF-8 has like 1.1million. This is important because sometimes the chats will contain things like emojis. (I didn't want to be a dictator and ban emojis
	//from the chat so we must all compromise for the greater good and change to UTF-8.) Anyway, if it is on cp1252 and the scanner finds an unrepresented symbol
	//it will end without any indication, which is very dangerous because it might miss a students attendance which follows from the emojis. Since there is no 
	//indication you will never know it ended and thus skip a student(s) from being entered for attendance. 
	public static String [] ar = new String[200]; // Public array which is accessible from any method. Max size is 100, could've used a more dynamic structure like
	//array lists but forgot.
	public static String date = new String("");
	public static String list = new String("");
	public static String present = new String("");
	public static boolean chckException = false;
	public static void main(String[] args) throws FileNotFoundException {
		String address = SecondController.staticPath;		
		File chatlogs = new File(address);
		try {
			Scanner c = new Scanner(chatlogs);
			
			int m = 0;//array index counter
			date=(chatlogs.getName().substring(3,11));//Zoom recording chats are formatted in a certain way wherein they tell the date in the file's title.
			//this merely singles out the date in combination with the getName() method(gets the name of file).
			while(c.hasNextLine()) {
					ar[m] = c.nextLine().split("[0-9:]")[8].trim();//whats happening here is that we are basically assigning a certain value to index m in array ar
					//this certain value is the nextLine which is split under the regex of digits from 0-9 or :. This is done because zoom chats are
					//generally formatted in accordance to time which is represented by numbers and seperated by colons. This makes a couple of different segregated 
					//parts because remember the split method splits the string into multiple different parts, here I am specifically taking the 8th section and then
					//utilizing the trim method to get rid of the extra white space. There are rare exceptions to this when sometimes a line generates without a time. I have
					//handled such exceptions through catching ArrayIndexOutOfBoundsException, this will let you know to go into the file and just backspace the overflown line
					//to the previous line so that the file only starts with the time and nothing else. 
					m++;// updating the index for the array.		
			}
			sortVals(ar,2);//now that the array is filled we send it to the sortVals method.
			c.close();
		}

		catch(ArrayIndexOutOfBoundsException a) {
			chckException = true;
			SecondController.checkExp = true;
			SecondController.exceptionHandle(chckException);
		}
		catch(StringIndexOutOfBoundsException s) {
			chckException = true;
			SecondController.checkExp = true;
			SecondController.exceptionHandle(chckException);
		}


	}
	public static void sortVals(String[] c, int num) {
		for (int i = 0; i < c.length; i++) {
			String hld = ar[i];//takes one element from array one by one
			for (int j = i+1; j < c.length; j++) {
				if (ar[i]!= null &&  hld.equals(ar[j])) {//check to see if ar[i] is equal to null first because we can't use a method on a null value then and that answer with
					//if hld is equal to ar[j] remember j starts at a value i+1 so it goes through the remaining array to check and see if there are any values which match ar[i]
					//this is done to see if there are any people in the zoom chat that have additional chat messages. We want to remove those additional occurances.
					ar[j] = "";//If both of the above conditions are true then we set those additional peoples names to ""/ nothing
				}

			}
		}
		int m = 0;//another array index counter
		int pres = 0;//present counter
		while(ar[m]!= null) {			//check to see if the value at the index is null or not
			if (!(ar[m].equals(""))) {	//check to see if the value  at the index is not ""
				list+=(ar[m]) + ", ";
				pres++;	
			}
			m++;//updating the index for the array
		}
		present+=("Total: " + pres);
		
		
	}
	

}