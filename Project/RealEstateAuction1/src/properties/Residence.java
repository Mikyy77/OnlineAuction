package properties;

import java.io.Serializable;

public class Residence extends Property implements Serializable {
    public Residence(String name, long price, String image) {
        super(name, price, image);
    }
}
