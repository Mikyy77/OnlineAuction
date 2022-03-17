package characters;

public class ElectroSword extends Sword {
	public ElectroSword(int productNumber) {
		super(productNumber);
	}

	public void hit(Knight knight, Ogre ogre) {
		if (knight.getEnergy() >= ogre.getEnergy())
			ogre.setEnergy((int) (0.62 * ogre.getEnergy()));
		
		System.out.println("Knight - electro sword");
	}
	
	public void hit(BraveKnight knight, Ogre ogre) {
		ogre.setEnergy((int) (0.35 * ogre.getEnergy()));
		
		System.out.println("Brave Knight - electro sword");		
	}
}
