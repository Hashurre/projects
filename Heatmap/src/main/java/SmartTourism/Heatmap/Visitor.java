package SmartTourism.Heatmap;
import java.util.ArrayList;
public class Visitor {
	private int numBleDetected;
	private Long visitorID;
	private ArrayList<BLEDetected> VisitorBLED;
	private float visitorX = -1;
	private float visitorY = -1;
	
	public Visitor(Long visitorID, ArrayList<BLEDetected> BLEDetected) {
		this.visitorID = visitorID;
		this.VisitorBLED = BLEDetected;
		numBleDetected = BLEDetected.size();
	}
	public int getBLENumber() {
		return this.numBleDetected;
	}
	public Long getVisitorID() {
		return this.visitorID;
	}
	public ArrayList<BLEDetected> getBLEDetectedList() {
		return this.VisitorBLED;
	}
	public void setVisitorCoordinates(float x, float y) {
		this.visitorX = x;
		this.visitorY = y;
	}
	public float getVisitorX() {
		return this.visitorX;
	}
	public float getVisitorY() {
		return this.visitorY;
	}
	@Override
	public String toString() {
		String s = new String();
		s+= "	Visitor ID: " + this.visitorID + " " + "\nBLES Detected & RSSI Signal: \n" + VisitorBLED.toString();
		return s;
	}
}
