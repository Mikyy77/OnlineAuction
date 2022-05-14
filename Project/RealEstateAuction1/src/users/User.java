package users;

import properties.Property;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class User implements Serializable {

    private String username;
    private String password;
    private long balance;
    private ArrayList<Property> properties;
    private Address address;
    private Contact contact; // TODO aggregation
    public static final long serialVersionUID = 123456789;

    public User(String username, long balance) {
        this.username = username;
        this.balance = balance;
        this.properties = new ArrayList<>();
    }

    public void createContact(String fullName, String email, String phoneNumber){
        contact = new Contact(fullName, email, phoneNumber);
    }

    public void createAddress(String street, int number, String city, String country){
        address = new Address(street, number, city, country);
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String txt) {
        password = txt;
    }

    public void setName(String name) {
        this.username = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public void bid(long price) {
        System.out.println("***You*** " + this.username + " just bid $ " + price);
    }

    public void buy(long price, Property property) {
        properties.add(property);
        balance -= price;
    }
}
