package basic;
public class Knight {
	int energy;
	
	void attack(Ogre ogre) {
		int randomDamage = (int) (Math.random() * (double)ogre.energy); // randomized attack damage
		ogre.energy = randomDamage; // (int) just casts a float result into an int
		ogre.revenge(this); // this represents a reference to the current knight object 
		//System.out.println(this); // each reference is unique
	}
}
