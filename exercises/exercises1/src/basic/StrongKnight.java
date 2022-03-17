package basic;

public class StrongKnight extends Knight {
	void attack(Ogre ogre) {
		int randomDamage = (int) (Math.random() * (double)ogre.energy + 20); // deals more damage than a normal knight
		ogre.energy = randomDamage;
		ogre.revenge(this);
	}
}
