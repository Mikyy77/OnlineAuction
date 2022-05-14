package controllers;

import auctionControl.AuctionProcess;
import auctionControl.LoginControl;
import exceptions.WrongLoginException;
import interfaces.LoginController;
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
import serialization.Serialize;
import users.User;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerLogin implements Initializable, LoginController, Serializable {
    private static final long serialVersionUID = 6529685098267757690L;


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

    private AuctionProcess process;
    private LoginControl loginControl;

    public ControllerLogin() {

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
        loginControl = LoginControl.getInstance();
        loginControl.setLocalLoginController(this);
        if(loginForm.getText().equals("admin")) {
            if(loginControl.isAdmin()) {
                process = AuctionProcess.getInstance();
                process.createAdmin("admin", 999999999);
                switchToScene(event, "/scenes/adminAuction.fxml");
            } else {
                pwValid.setText("Incorrect user password");
            }
            return;
        }
        User user = loginControl.checkLogin();
        if(user != null) {
            AuctionProcess process = AuctionProcess.getInstance();
            process.setUser(user);
            switchToScene(event, "/scenes/auction.fxml");
       } else {
            try {
                if(pwForm.getText().length() < 6 || pwForm.getText().length() > 20)
                    throw new WrongLoginException(pwValid.idProperty()); // TODO my custom exception
                if(loginForm.getText().length() < 3 || loginForm.getText().length() > 20)
                    throw new WrongLoginException(usernameValid.idProperty());
            } catch (WrongLoginException e) {
            }
        }
    }

    public void register(ActionEvent event) throws IOException {
        loginControl = LoginControl.getInstance();
        loginControl.setLocalLoginController(this);
        if(loginControl.checkRegister() && !loginForm.getText().equals("admin")) {
            process = AuctionProcess.getInstance();
            switchToScene(event, "/scenes/register.fxml");
        }
    }

    // getters and setters

    public String getLoginForm() {
        return loginForm.getText();
    }

    public String getPwForm() {
        return pwForm.getText();
    }

    public void setUsernameValid(String text) {
        usernameValid.setText(text);
    }

    public void setPwValid(String text) {
        pwValid.setText(text);
    }
}
