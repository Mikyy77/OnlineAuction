package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class LoginControl {

    public static LoginControl loginControl;
    private LoginController localLoginController;

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

    public boolean checkLogin() {
        localLoginController.setUsernameValid("");
        localLoginController.setPwValid("");
        // username validation
        String username = localLoginController.getLoginForm();
        if(username.length() < 2) {
            localLoginController.setUsernameValid("Username too short");
            return false;
        }
        if(username.length() > 20) {
            localLoginController.setUsernameValid("Username too long");
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            if (Character.isWhitespace(username.charAt(i))) {
                localLoginController.setUsernameValid("Username must not contain spaces");
                return false;
            }
        }
        boolean alreadyLogged = false;
        String foundPassword = "";
        try {
            FileReader file = new FileReader("C:\\Users\\micha\\IdeaProjects\\RealEstateAuction\\src\\files\\users.txt");

            BufferedReader buffer = new BufferedReader(file);

            String line;

            while ((line = buffer.readLine()) != null) {
                String foundUsername = line.split(" ")[0];
                if(foundUsername.equals(username)) {
                    alreadyLogged = true;
                    foundPassword = line.split(" ")[1];
                }
            }
            buffer.close();
        }catch(Exception e) {
            e.getStackTrace();
        }


        // password validation
        String password = localLoginController.getPwForm();
        if(alreadyLogged) {
            if(!foundPassword.equals(password)) {
                localLoginController.setPwValid("Incorrect password for given username");
                return false;
            }
        }
        if(password.length() < 6) {
            localLoginController.setPwValid("Password too short");
            return false;
        }
        if(password.length() > 20) {
            localLoginController.setPwValid("Password too long");
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isWhitespace(password.charAt(i))) {
                localLoginController.setPwValid("Password must not contain spaces");
                return false;
            }
        }
        return true;
    }

    public void writeInfo() {
        String loginAndPw = localLoginController.getLoginForm() + " " + localLoginController.getPwForm();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\micha\\IdeaProjects\\RealEstateAuction\\src\\files\\users.txt"));
            String input;
            String allInfo = "";
            while((input = br.readLine()) != null) {
                allInfo += input;
            }
            FileWriter file = new FileWriter("C:\\Users\\micha\\IdeaProjects\\RealEstateAuction\\src\\files\\users.txt");
            BufferedWriter output = new BufferedWriter(file);
            output.append(allInfo);
            if(allInfo.length() > 2) {
                output.append("\n");
            }
            output.append(loginAndPw);
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
