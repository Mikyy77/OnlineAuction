package characters;
public class TerrifyingOgre extends BadOgre {
	
	public TerrifyingOgre(int energy) {
		super(energy);
	}
	@Override
	void eat(Knight knight) {
		super.eat(knight);
		roar();
	}
	void roar() {
		System.out.println("Roar!");
	}
}
