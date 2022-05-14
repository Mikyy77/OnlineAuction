package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import auctionControl.AuctionProcess;
import interfaces.WinnerController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerWinner implements Initializable, WinnerController {

    @FXML
    private Label message;
    @FXML
    private Label message1;
    @FXML
    private ImageView view;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    private AuctionProcess process;

    public ControllerWinner() {
        process = AuctionProcess.getInstance();
    }

    public void setMessage(String text) {
        message.setText(text);
    }

    public void setMessage1(String text) {
        message1.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(process.isOwned()) {
            message.setText("Congratulations!");
            message1.setText("You now own " + process.getProperty().getName() + ". You bought it for " + process.formatPrice(process.getCurrentPrice()));
        }else {
            message.setText("Better luck next time!");
            message1.setText("You have done well, but unfortunately, " + process.getCurrentBot().getName() + " wanted " + process.getProperty().getName() + " even more.");
        }
        String imageUrl = process.getProperty().getImageURL().substring(1);
        Image img = new Image(getClass().getResource(imageUrl).toString()); // set image url
        view.setImage(img);
    }

    public void start(ActionEvent event) throws IOException {
        if(process.getUser().getName().equals("admin")) {
            switchToScene(event, "/scenes/adminAuction.fxml");
        } else {
            switchToScene(event, "/scenes/auction.fxml");
        }
    }


    public void switchToScene(ActionEvent event, String file) throws IOException {
        root = FXMLLoader.load(getClass().getResource(file));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goBack(ActionEvent event) throws IOException {
        switchToScene(event, "/scenes/home.fxml");
    }

}
