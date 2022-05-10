package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.AuctionProcess;
import sample.LoginControl;
import sample.LoginController;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerLogin implements Initializable, LoginController {

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


    LoginControl loginControl;

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
        if(loginControl.checkLogin()) {
            loginControl.writeInfo();
            AuctionProcess process = AuctionProcess.getInstance();
            process.createUser(loginForm.getText(), 1000000000);
            switchToScene(event, "/scenes/auction.fxml");
       }
    }

    // getters and setters

    public String getLoginForm() {
        try {
            return loginForm.getText();
        }catch(Exception e){}
        return "";
    }

    public String getPwForm() {
        try {
            return pwForm.getText();
        }catch(Exception e){}
        return "";
    }

    public void setUsernameValid(String text) {
        //try{
            usernameValid.setText(text);
       // }catch(Exception e){}

    }

    public void setPwValid(String text) {
        try{
            pwValid.setText(text);
        }catch(Exception e){}
    }
}
