package users;

import java.io.Serializable;

public class Contact implements Serializable {
    private String fullName;
    private String email;
    private String phoneNumber;

    public Contact(String fullName, String email, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
