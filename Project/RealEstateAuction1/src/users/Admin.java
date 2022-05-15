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
    /**
     * This method does not decrease the balance of the user, it is because the user is admin and he has unlimited power and money.
     * Since this class extends the User class, the method is overridden with its own implementation which can be an example of polymorphism.
     */
    @Override
    public void buy(long price, Property property) {
        properties.add(property);
    }
}
