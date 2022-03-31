package characters;
public class Ogre implements Energy {
	private boolean hungry;
	private int energy;
	
	public Ogre() {
		
	}
	
	public Ogre(int energy) {
		setEnergy(energy);
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public void increaseEnergy(int energy) {
		this.energy += energy;
	}
	public void decreaseEnergy(int energy) {
		this.energy -= energy;
	}
	public int getEnergy() {
		return energy;
	}
	
	void setValues(int energy) {
		this.energy = energy;
	}

	void setValues(boolean hungry) {
		this.hungry = hungry;
	}
	
	void setValues(int energy, boolean hungry) {
		this.energy = energy;
		this.hungry = hungry;
	}
	
	void revenge(Knight knight) {
		if (energy > knight.getEnergy())
//			knight.energy = (int) (0.9 * knight.energy);
			// the energy cannot be accessed directly anymore because it's private
			knight.setEnergy((int) (0.9 * knight.getEnergy()));
	}
}
