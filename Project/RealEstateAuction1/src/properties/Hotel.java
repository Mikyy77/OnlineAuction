package properties;

// chained inheritance --> Hotel extends Residence extends Property

import java.io.Serializable;

public class Hotel extends Residence implements Serializable {
    public Hotel(String name, long price, String image) {
        super(name, price, image);
    }
}
