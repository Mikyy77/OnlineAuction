package properties;

import java.io.Serializable;

public class BusinessCentre extends Property implements Serializable {
    public BusinessCentre(String name, long price, String image) {
        super(name, price, image);
    }
}
