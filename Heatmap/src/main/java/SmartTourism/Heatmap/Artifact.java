package SmartTourism.Heatmap;

public class Artifact {
	private String aName;
	private long x;
	private long y;
	public Artifact(String aName, long x, long y) {
		this.aName = aName;
		this.x = x;
		this.y = y;
	}
	public long getX() {
		return this.x;
	}
	public long getY() {
		return this.y;
	}
	public String getName() {
		return this.aName;
	}
	@Override
	public String toString() {
		String s = new String();
		s += this.aName + "\n" + this.x + "\n" + this.y;
		return s;
	}
}
