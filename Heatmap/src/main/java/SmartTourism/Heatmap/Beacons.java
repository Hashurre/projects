package SmartTourism.Heatmap;

public class Beacons {
	private long bleID;
	private long bleXC;
	private long bleYC;
	
	public Beacons(long bleID, long bleXC, long bleYC) {
		this.bleID = bleID;
		this.bleXC = bleXC;
		this.bleYC = bleYC;
	}
	public long getBLEID() {
		return this.bleID;
	}
	public long getBLEXC() {
		return this.bleXC;
	}
	public long getBLEYC() {
		return this.bleYC;
	}
	@Override
	public String toString() {
		String s = new String();
		s += this.bleID + "\n" + this.bleXC + "\n" + this.bleYC;
		return s;
	}
}
