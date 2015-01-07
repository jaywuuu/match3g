package wu.jay.match3g;

import java.util.Random;

public class Orb {
	public enum OrbType {
		NONE(0),
		RED(1),
		GREEN(2),
		BLUE(3),
		YELLOW(4),
		BLACK(5);
		
		private final int id;
		
		OrbType(int i) {
			this.id = i;
		}
		
		public static OrbType getRandomOrb() {
			Random rand = new Random();
			return values()[1 + rand.nextInt(values().length - 1)];
		}
		
		public int getId() { return id; }
	}
	
	public OrbType type;
	public int cx;
	public int cy;
	public int r;
	
	public Orb(OrbType type) {
		this.type = type;
	}
}
