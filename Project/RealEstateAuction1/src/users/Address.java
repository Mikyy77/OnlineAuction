package users;

public class Address {
    private String street;
    private String city;
    private String country;
    private int number;

    public Address(String street, int number, String city, String country) {
        this.street = street;
        this.city = city;
        this.number = number;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getNumber() {
        return number;
    }

    public String getAddress() {
        return this.street + " " + this.number + " " + this.city +", " + this.country;
    }
}
