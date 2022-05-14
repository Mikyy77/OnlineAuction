package properties;

// chained inheritance --> Flat extends Residence extends Property

import java.io.Serializable;

public class Flat extends Residence implements Serializable {
    public Flat(String name, long price, String image) {
        super(name, price, image);
    }
}
