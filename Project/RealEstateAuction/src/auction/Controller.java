package auction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField loginForm;
    @FXML
    private TextField pwForm;
    @FXML
    private Label usernameValid;
    @FXML
    private Label pwValid;

    AuctionProcess process;
    String filepath;
    
    
    
    
    public Controller() {
    	filepath = "C:\\Users\\micha\\mySpaceShip\\RealEstateAuction\\src\\files\\users.txt"; // update users file location here
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void switchToScene(ActionEvent event, String file) throws IOException {
        root = FXMLLoader.load(getClass().getResource(file));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent event) throws IOException {
        if(checkLogin()) {
            writeInfo();
            AuctionProcess process = AuctionProcess.getInstance();
            process.createUser(loginForm.getText(), 1000000000);
            switchToScene(event, "login.fxml");
       }
    }

    public boolean checkLogin() {
        usernameValid.setText("");
        pwValid.setText("");
        // username validation
        String username = loginForm.getText();
        if(username.length() < 2) {
            usernameValid.setText("Username too short");
            return false;
        }
        if(username.length() > 20) {
            usernameValid.setText("Username too long");
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            if (Character.isWhitespace(username.charAt(i))) {
                usernameValid.setText("Username must not contain spaces");
                return false;
            }
        }
        boolean alreadyLogged = false;
        String foundPassword = "";
        try {
            FileReader file = new FileReader(filepath);

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
        String password = pwForm.getText();
        if(alreadyLogged) {
            if(!foundPassword.equals(password)) {
                pwValid.setText("Incorrect password for given username");
                return false;
            }
        }
        if(password.length() < 6) {
            pwValid.setText("Password too short");
            return false;
        }
        if(password.length() > 20) {
            pwValid.setText("Password too long");
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isWhitespace(password.charAt(i))) {
                pwValid.setText("Password must not contain spaces");
                return false;
            }
        }
        return true;
    }

    public void writeInfo() {
        String loginAndPw = loginForm.getText() + " " + pwForm.getText();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String input;
            String allInfo = "";
            while((input = br.readLine()) != null) {
                allInfo += input;
            }
            FileWriter file = new FileWriter(filepath);
            BufferedWriter output = new BufferedWriter(file);
            output.append(allInfo);
            if(allInfo.length() > 2) {
                output.append("\n");
            }
            output.append(loginAndPw);
            output.close();
            br.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }



}
