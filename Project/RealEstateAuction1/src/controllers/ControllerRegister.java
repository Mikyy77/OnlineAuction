package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.AuctionController;
import sample.AuctionProcess;
import sample.RegisterController;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegister implements Initializable, RegisterController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label usernameF;
    @FXML
    private Label balanceF;
    @FXML
    private Label fullNameF;
    @FXML
    private Label emailF;
    @FXML
    private Label phoneF;
    @FXML
    private Label streetF;
    @FXML
    private Label streetNoF;
    @FXML
    private Label cityF;
    @FXML
    private Label countryF;

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

    private AuctionProcess process;

    public ControllerRegister() {
        process = AuctionProcess.getInstance();
        process.setLocalRegisterController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
