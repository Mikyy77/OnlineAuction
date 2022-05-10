package properties;

// chained inheritance --> Flat extends Residence extends Property

public class Flat extends Residence {
    public Flat(String name, int price, String image) {
        super(name, price, image);
    }
}
