package users;

import properties.Property;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    private String username;
    private long balance;
    private ArrayList<Property> properties;
    private Contact contact; // aggregation
    private Address address; // aggregation

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

    public void setName(String name) {
        this.username = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void bid(long price) {
        System.out.println("***You*** " + this.username + " just bid $ " + price);
    }

    public void buy(long price, Property property) {
        properties.add(property);
        balance -= price;
    }
}
