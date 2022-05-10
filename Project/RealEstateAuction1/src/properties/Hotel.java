package properties;

// chained inheritance --> Hotel extends Residence extends Property

public class Hotel extends Residence {
    public Hotel(String name, int price, String image) {
        super(name, price, image);
    }
}
