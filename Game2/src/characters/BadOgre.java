package characters;

public class BadOgre extends Ogre {
	boolean hungry; // beware of attribute hiding!
	
	public BadOgre(int energy) {
		super(energy);
	}
	void revenge(Knight knight) {
		if (hungry)
			eat(knight);
	}
	void eat(Knight knight) {
		knight.setEnergy(0);
	}
}
