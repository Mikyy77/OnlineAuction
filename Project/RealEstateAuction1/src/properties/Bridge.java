package properties;

import java.io.Serializable;

public class Bridge extends Property implements Serializable {

    public Bridge(String name, long price, String image) {
        super(name, price, image);
    }
}
