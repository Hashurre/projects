package SmartTourism.Heatmap;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
import java.util.ArrayList;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HeatmapApplication {
	protected static Long widthOfM;
	protected static Long lengthOfM;
	protected static int widthOfHeatMap;
	protected static int heightOfHeatMap;
	protected static String settings;
	protected static ArrayList<Artifact> artifactList = new <Artifact>ArrayList();
	protected static ArrayList<Beacons> beaconList = new <Beacons>ArrayList();
	protected static ArrayList<Visitor> visitorList = new <Visitor>ArrayList();

	public static void main(String[] args) {
		SpringApplication.run(HeatmapApplication.class, args);
		try {
			settingFunctions();
			parseFunctionA();
			parseFunctionB();
			positionFunction();
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}
	//parses the museum json file
	public static void parseFunctionA()  throws IOException, ParseException {
		String artifactName;
		Long artifactXC;
		Long artifactYC;
		Long bleID;
		Long bleXC;
		Long bleYC;
		//A JSONParser object to use to parse JSON data
		JSONParser jp = new JSONParser();
		//A FileReader object named readF to read data from a JSON file
		FileReader readF = new FileReader(".\\jsonfiles\\MUInfoF1T.Json");
		//The JSON data read from the file is parsed using the parse method of the JSONParser object jp 
		//and the result is stored in an Object named obj.
		Object obj = jp.parse(readF);
		//The previous generic Object is cast to a JSONObject named job. 
		JSONObject job = (JSONObject)obj;
		//object within the JSON data named "mDimensions" is extracted and cast to a JSONObject named mDim.
		JSONObject mDim = (JSONObject)job.get("mDimensions");
		//Values for widthOfM and lengthOfM are extracted from the mDim object. They are cast to Long assuming they are integer values.
		widthOfM = ((Long)(Object)(mDim.get("widthOfM")));
		lengthOfM = (Long)(Object)(mDim.get("lengthOfM"));
		//An array within the JSON data named "mArtifacts" is extracted and cast to a JSONArray named ja. - contains museum artifacts
		JSONArray ja = (JSONArray)job.get("mArtifacts");
		//This loop iterates over each element of ja array containing artifacts; for each artifact its name, xcoord, and ycoord are extracted from the JSON object and used
		//to create an artifact object which is then added to the artifact list.
		for(int i = 0; i < ja.size(); i++) {
			artifactName = (String)((JSONObject)ja.get(i)).get("artifactName");
			artifactXC = (Long)((JSONObject)ja.get(i)).get("artifactXC");
			artifactYC = (Long)((JSONObject)ja.get(i)).get("artifactYC");
			Artifact aobject = new Artifact(artifactName,artifactXC,artifactYC);
			artifactList.add(aobject);
		}
		//"mBLEBeacons" is extracted and cast to a JSONArray named jb
		//iterates over each element of the jb array, which represents BLE beacons. For each beacon, its ID, X-coordinate, and Y-coordinate are extracted from the JSON object 
		//and used to create a Beacons object, which is then added to beaconList.
		JSONArray jb = (JSONArray)job.get("mBLEBeacons");
		for(int j = 0; j < jb.size(); j++) {
			bleID = (Long)((JSONObject)jb.get(j)).get("bleID");
			bleXC = (Long)((JSONObject)jb.get(j)).get("bleXC");
			bleYC = (Long)((JSONObject)jb.get(j)).get("bleYC");
			Beacons bobject = new Beacons(bleID, bleXC, bleYC);
			beaconList.add(bobject);
			
		}
		
	}
	//parses the visitor json file
	public static void parseFunctionB() throws IOException, ParseException {
		Long visitorID;
		Long bleID;
		Long RSSI;
		BLEDetected bandrDetected;
		Visitor visitor;
	    // new instance of JSONParser to parse JSON Files
		JSONParser jp = new JSONParser();
	    // Read the JSON file containing visitor information
		FileReader readF = new FileReader(".\\jsonfiles\\USInfoF1T.Json");
	    // Parse the JSON file and store it as an object
		Object obj = jp.parse(readF);
	    // Cast the parsed object to JSONObject
		JSONObject job = (JSONObject)obj;	
	    // Extract the array of visitors' information from the JSON object
		JSONArray ja = (JSONArray)job.get("visitorsInformation");
	    // Iterate through each visitor's information
		for(int n = 0; n < ja.size(); n++) {
	        // Create a list to store BLE devices detected by the visitor
			ArrayList<BLEDetected> bledetectedList = new ArrayList<BLEDetected>();
	        // Extract visitor's ID
			visitorID = (Long)((JSONObject)ja.get(n)).get("visitorID");
	        // Extract array of BLE devices detected by the visitor
			JSONArray jaBLE = (JSONArray)((JSONObject)ja.get(n)).get("bleDetected");
	        // Iterate through each BLE device detected by the visitor
			for(int c = 0; c < jaBLE.size(); c++) {
	            // Extract BLE device ID and RSSI value
				bleID = (Long)((JSONObject)jaBLE.get(c)).get("bleID");
				RSSI = (Long)((JSONObject)jaBLE.get(c)).get("RSSI");
	            // Create a BLEDetected object with extracted BLE ID and RSSI
				bandrDetected = new BLEDetected(bleID,RSSI);
	            // Add the BLEDetected object to the list
				bledetectedList.add(bandrDetected);
			}
	        // Create a Visitor object with extracted visitor ID and list of BLE devices detected
			visitor = new Visitor(visitorID,bledetectedList);
	        // Add the Visitor object to a list of visitors
			visitorList.add(visitor);
		}
		
		
	}
	//finds the position of each person in our visitor list
	public static void positionFunction() {
		for (Visitor vis : visitorList) {
			//make sure each visitor detects atleast 3 BLES
			if(vis.getBLENumber() < 3) {
				System.out.println("Can't locate visitor: " + vis.getVisitorID());
			}
			//determine the coordinates of user
			else {
				//for every BLE in a visitor's bleDetected List
				long lowest = Integer.MAX_VALUE;
				long secondLowest = Integer.MAX_VALUE;
				long thirdLowest = Integer.MAX_VALUE;
				long lowestID =-1;
				long secondLowestID =-1;
				long thirdLowestID =-1;
				for (int j = 0; j < vis.getBLEDetectedList().size();j++) {
					//find the BLES with 3 lowest RSSI signals, and their ID
						if (lowest>vis.getBLEDetectedList().get(j).getRSSI()) {
						thirdLowest = secondLowest;
						thirdLowestID = secondLowestID;
						secondLowest = lowest;
						secondLowestID = lowestID;
						lowest = vis.getBLEDetectedList().get(j).getRSSI();
						lowestID = vis.getBLEDetectedList().get(j).getBLEID();
						
					}
						else if(secondLowest>vis.getBLEDetectedList().get(j).getRSSI()) {
						thirdLowest = secondLowest;
						thirdLowestID = secondLowestID;
						secondLowest = vis.getBLEDetectedList().get(j).getRSSI();
						secondLowestID = vis.getBLEDetectedList().get(j).getBLEID();
	
					}
						else if(thirdLowest>vis.getBLEDetectedList().get(j).getRSSI()) {
						thirdLowest = vis.getBLEDetectedList().get(j).getRSSI();
						thirdLowestID = vis.getBLEDetectedList().get(j).getBLEID();
					}
					
				}
				long [] lowestPair = {lowestID,lowest};
				long [] secondLowestPair = {secondLowestID,secondLowest};
				long [] thirdLowestPair = {thirdLowestID, thirdLowest};
				long[] lowestCo = new long[2];
				long[] secondLowestCo = new long[2];
				long[] thirdLowestCo = new long[2];
				//Find the coordinates for our lowest BLES store it in their respective arrays 0 = x 1 = y
				for (int c = 0; c < beaconList.size(); c++) {
					if(lowestPair[0] == beaconList.get(c).getBLEID()) {
						lowestCo[0] = beaconList.get(c).getBLEXC();
						lowestCo[1] = beaconList.get(c).getBLEYC();
					}
					else if (secondLowestPair[0] == beaconList.get(c).getBLEID()) {
						secondLowestCo[0] = beaconList.get(c).getBLEXC();
						secondLowestCo[1] = beaconList.get(c).getBLEYC();
					}
					else if (thirdLowestPair[0] == beaconList.get(c).getBLEID()) {
						thirdLowestCo[0] = beaconList.get(c).getBLEXC();
						thirdLowestCo[1] = beaconList.get(c).getBLEYC();
					}
					
				}
				//Calculate the coordinates for the person
				double x1 = (double)lowestCo[0];
				double y1 = (double)lowestCo[1];
				double r1= (double)lowestPair[1];
				
				double x2 = (double)secondLowestCo[0];
				double y2 = (double)secondLowestCo[1];
				double r2 = (double)secondLowestPair[1];
				
				double x3 = (double)thirdLowestCo[0];
				double y3 = (double)thirdLowestCo[1];
				double r3 = (double)thirdLowestPair[1];
				
				float A = (float)((2*x2)-(2*x1));
				float B = (float)((2*y2)-(2*y1));
				float C = (float)((r1*r1)-(r2*r2)-(x1*x1)+(x2*x2)-(y1*y1)+(y2*y2));
				float D = (float)((2*x3)-(2*x2));
				float E = (float)((2*y3)-(2*y2));
				float F = (float)((r2*r2)-(r3*r3)-(x2*x2)+(x3*x3)-(y2*y2)+(y3*y3));
				//The coordinates
				float xCVisitor = ((C*E-F*B)/(E*A-B*D));
				float yCVisitor = ((C*D-A*F)/(B*D-A*E));
				
				//Assign the coordinates to the visitor.
				vis.setVisitorCoordinates(xCVisitor, yCVisitor);
			}
		}
		
		
	}
	public static void settingFunctions() {
		Scanner input = new Scanner(System.in);
		int heightHeatMap;
		int widthHeatMap;
		int max = -1;
		int min = -1;
		String color = null;
		settings = "";
		
		System.out.println("What is the height of the Heat Map?");
		heightHeatMap = input.nextInt();
		System.out.println("What is the width of the Heat Map?");
		widthHeatMap = input.nextInt();
		settings+=heightHeatMap+","+widthHeatMap+",";
		
		System.out.println("What is the minimum population for the High Sector?");
		min = input.nextInt();
		System.out.println("What is the Color for the High sector?");
		color = input.next();
		settings+=min+","+color;
		
		System.out.println("What is the Color for the Medium sector?");
		color = input.next();
		settings+=","+color+",";

		System.out.println("What is the population Max for Low sector?");
		max = input.nextInt();
		System.out.println("What is the Color for the Low sector?");
		color = input.next();
		settings+=max+","+color;

		
	}


}
