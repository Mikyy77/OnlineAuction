package basic;

public class Ogre {
	boolean hungry;
	int energy;
	
	void revenge(Knight knight) {
		if (energy > knight.energy) {
			// knight.energy = (int) (0.9 * knight.energy); // this is the default style
			// random style
			int randomDamage = (int) (Math.random() * (double)knight.energy);
			knight.energy = randomDamage; // random amount of damage
		}
	}
}
