package auctionControl;

import interfaces.LoginController;
import interfaces.RegisterController;
import serialization.Serialize;
import users.User;

import java.io.*;
import java.util.ArrayList;

public class LoginControl implements Serializable {

    public static LoginControl loginControl;
    private LoginController localLoginController;
    private RegisterController registerController;

    private static final long serialVersionUID = 6529685098267757690L;

    private LoginControl() {
    };

    public static LoginControl getInstance() {
        if(loginControl == null) {
            loginControl = new LoginControl();
        }
        return loginControl;
    }


    public void setLocalLoginController(LoginController localLoginController) {
        this.localLoginController = localLoginController;
    }

    public void setLocalRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }


    public boolean isAdmin() {
        if(localLoginController.getLoginForm().equals("admin") && localLoginController.getPwForm().equals("admin")){
            return true;
        }
        return false;
    }

    public String getLoginForm() {
        return localLoginController.getLoginForm();
    }

    public String getPwForm() {
        return localLoginController.getPwForm();
    }

    public User checkLogin() {
        localLoginController.setUsernameValid("");
        localLoginController.setPwValid("");

        // username validation
        String username = localLoginController.getLoginForm();


        if(username.length() < 2) {
            localLoginController.setUsernameValid("Username too short");
            return null;
        }
        if(username.length() > 20) {
            localLoginController.setUsernameValid("Username too long");
            return null;
        }
        for (int i = 0; i < username.length(); i++) {
            if (Character.isWhitespace(username.charAt(i))) {
                localLoginController.setUsernameValid("Username must not contain spaces");
                return null;
            }
        }

        String password = localLoginController.getPwForm();


        Serialize serialize = new Serialize();
        try {
            ArrayList<User> users = (ArrayList<User>) serialize.getFromTxt("users");
            for(User user : users) {
                if(user.getName().equals(username)) {
                    System.out.println(user.getName());
                    System.out.println(user.getPassword());
                    if(!user.getPassword().isEmpty() && user.getPassword().equals(password)) {
                        System.out.println("match!");
                        return user;
                    } else {
                        localLoginController.setUsernameValid("Incorrect password for this username");
                        return null;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        localLoginController.setUsernameValid("User not found! Try to register instead");
        return null;

    }

    public boolean checkRegister() {
        localLoginController.setUsernameValid("");
        localLoginController.setPwValid("");

        String username = localLoginController.getLoginForm();
        String password = localLoginController.getPwForm();

        // username validation

        if (username.length() < 2) {
            localLoginController.setUsernameValid("Username too short");
            return false;
        }
        if (username.length() > 20) {
            localLoginController.setUsernameValid("Username too long");
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            if (Character.isWhitespace(username.charAt(i))) {
                localLoginController.setUsernameValid("Username must not contain spaces");
                return false;
            }
        }
        // password validation

        if (password.length() < 6) {
            localLoginController.setPwValid("Password too short");
            return false;
        }
        if (password.length() > 20) {
            localLoginController.setPwValid("Password too long");
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isWhitespace(password.charAt(i))) {
                localLoginController.setPwValid("Password must not contain spaces");
                return false;
            }
        }

        Serialize serialize = new Serialize();
        try {
            ArrayList<User> users = (ArrayList<User>) serialize.getFromTxt("users");
            for(User user : users) {
                if(user.getName().equals(username)) {
                    localLoginController.setUsernameValid("User already registered. Try to log in");
                    return false;
                }
            }
            return true;
//            users.add(new User(username, 10000000));
//            serialize.writeIntoTxt(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean checkRegisterValues() {
        if(!registerController.getUsernameF().isEmpty() && !registerController.getBalanceF().isEmpty()) {
            return true;
        }
        return false;
    }



    public void writeInfo() {
        String loginAndPw = localLoginController.getLoginForm() + " " + localLoginController.getPwForm();
    }
}
