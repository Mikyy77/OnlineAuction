package users;

import properties.Property;

import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends User implements Serializable {

    private ArrayList<Property> properties;

    public Admin(String username, long balance) {
        super(username, 999999999999L);
        properties = new ArrayList<>();
    }

    // TODO polymorphism
    @Override
    public void buy(long price, Property property) {
        properties.add(property);
    }
}
