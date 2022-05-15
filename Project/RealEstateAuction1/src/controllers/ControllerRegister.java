package controllers;

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
import auctionControl.AuctionProcess;
import auctionControl.LoginControl;
import interfaces.RegisterController;
import serialization.Serialize;
import users.User;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerRegister implements Initializable, RegisterController, Serializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameF;
    @FXML
    private TextField balanceF;
    @FXML
    private TextField fullNameF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField phoneF;
    @FXML
    private TextField streetF;
    @FXML
    private TextField streetNoF;
    @FXML
    private TextField cityF;
    @FXML
    private TextField countryF;
    @FXML
    private Label errMsg;


    public String getUsernameF() {
        return usernameF.getText();
    }

    public String getBalanceF() {
        return balanceF.getText();
    }

    public String getFullNameF() {
        return fullNameF.getText();
    }

    public String getEmailF() {
        return emailF.getText();
    }

    public String getPhoneF() {
        return phoneF.getText();
    }

    public String getStreetF() {
        return streetF.getText();
    }

    public String getStreetNoF() {
        return streetNoF.getText();
    }

    public String getCityF() {
        return cityF.getText();
    }

    public String getCountryF() {
        return countryF.getText();
    }

    private LoginControl loginControl;
    private AuctionProcess process;
    private User user;
    private boolean alreadyRegistered = false;

    /**
     * This constructor checks all information and writes the registered user into the text file.
     */
    public ControllerRegister() {
        loginControl = LoginControl.getInstance();
        loginControl.setLocalRegisterController(this);

        createUser();
        Serialize serialize = new Serialize();
        try {
            ArrayList<User> users = (ArrayList<User>) serialize.getFromTxt("users");
            for(User user : users) {
                if(user.getName().equals(user.getName())) {
                    alreadyRegistered = true;
                }
            }
            if(!alreadyRegistered) {
                users.add(user);
                serialize.writeIntoTxt(users);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a user from the register information
     */
    public void createUser() {

        User user = new User(loginControl.getLoginForm(), 100000000);
        user.setPassword(loginControl.getPwForm());
        System.out.println(loginControl.getPwForm());
        System.out.println(user.getPassword());

        process = AuctionProcess.getInstance();
        process.setUser(user);
        this.user = user;

    }

    /**
     * Updates the information about the user.
     */
    public void updateUser() {
        if(balanceF.getText().length() > 11) {
            errMsg.setText("Balance too high!");
            user.setBalance(100000000);
        } else {
            user.setBalance(Integer.parseInt(balanceF.getText()));
        }
        if(!fullNameF.getText().isEmpty() && !emailF.getText().isEmpty() && !phoneF.getText().isEmpty()) {
            user.createContact(fullNameF.getText(), emailF.getText(), phoneF.getText());
        }
        if(!streetF.getText().isEmpty() && !streetNoF.getText().isEmpty() && !cityF.getText().isEmpty() && countryF.getText().isEmpty()) {
            user.createAddress(streetF.getText(), Integer.parseInt(streetNoF.getText()), cityF.getText(), countryF.getText());
        }
    }

    public void startAuction(ActionEvent event) throws IOException {
        updateUser();
        switchToScene(event, "/scenes/auction.fxml");
    }

    /**
     * Increases balance of a user by pressing the button +
     * @param event
     */
    public void increaseBalance(ActionEvent event) {
        if(Integer.parseInt(balanceF.getText()) < 900000000) {
            long balanceIncrease = Integer.parseInt(balanceF.getText()) + 100000000;
            balanceF.setText(String.valueOf(balanceIncrease));

        }
    }

    /**
     * Decreses balance of a user by pressing the button -
     * @param event
     */
    public void decreaseBalance(ActionEvent event) {
        if(Integer.parseInt(balanceF.getText()) > 100000000) {
            long balanceIncrease = Integer.parseInt(balanceF.getText()) - 100000000;
            balanceF.setText(String.valueOf(balanceIncrease));
        } else if(Integer.parseInt(balanceF.getText()) > 10000000) {
            long balanceIncrease = Integer.parseInt(balanceF.getText()) - 10000000;
            balanceF.setText(String.valueOf(balanceIncrease));
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameF.setText(loginControl.getLoginForm());
        balanceF.setText("100000000");
    }

    public void switchToScene(ActionEvent event, String file) throws IOException {
        root = FXMLLoader.load(getClass().getResource(file));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
