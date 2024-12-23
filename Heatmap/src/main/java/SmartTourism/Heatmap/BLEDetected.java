package SmartTourism.Heatmap;

public class BLEDetected {
	private Long bleID;
	private Long RSSI;
	
	public BLEDetected(Long bleID, Long RSSI) {
		this.bleID = bleID;
		this.RSSI = RSSI;
	}
	public Long getBLEID() {
		return this.bleID;
	}	
	public Long getRSSI() {
		return this.RSSI;
	}
	@Override
	public String toString() {
		String s = new String();
		s += this.bleID + " " + this.RSSI;
		return s;
	}
}
