package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import interfaces.AuctionController;
import auctionControl.AuctionProcess;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerAuction implements Initializable, AuctionController, Serializable {
    public static final long serialVersionUID = 123456789;
    @FXML
    private Label title;
    @FXML
    private Label price;
    @FXML
    private Label balance;
    @FXML
    private Image img;
    @FXML
    private ImageView imageView;
    @FXML
    private Label timerLabel;
    @FXML
    private TextField bidPrice;
    @FXML
    private Button bidButton;
    @FXML
    private TextArea textArea;
    @FXML
    private Label nameL;
    @FXML
    private Label balanceL;
    @FXML
    private Label interest;



    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    private AuctionProcess process;

    public ControllerAuction() {
        process = AuctionProcess.getInstance();
        process.setLocalAuctionController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process.initialize();
    }

    // auction process methods

    public void setPrice(String text) {
        price.setText(text);
    }

    public void setBidPrice(String text) {
        bidPrice.setText(text);
    }

    public String getBidPrice() {
        return bidPrice.getText();
    }

    public void setTimer(String value) {
        timerLabel.setText(value);
    }

    public void setBid() {
        setBidButton();
    }

    public void setTitle(String text) {
        title.setText(text);
    }

    public void setImage(Image img) {
        imageView.setImage(img);
    }

    public void addToTextArea(String text) {
        textArea.appendText(text + "\n");
    }

    public void setNameL(String name) {
        nameL.setText(nameL.getText() + " " + name);
    }


    public void appendBalanceL(String balance) {
        balanceL.setText(balanceL.getText() + " " + balance);
    }
    public void setBalanceL(String balance) {
        balanceL.setText(balance);
    }

    public void setInterest(boolean isInterested) {
        if(isInterested) {
            interest.setText("High Interest!");
        } else {
            interest.setText("");
        }
    }


    public void bid() {
        process.bidBtnClicked();
    }
    
    /**
     * Set bid button when the auction ends so you can see the results of the auction.
     */
    public void setBidButton() {
        bidButton.setPrefWidth(150);
        bidButton.setLayoutX(bidButton.getLayoutX() - 30);
        bidButton.setText("See Results");
        bidButton.setOnAction(event -> {  //TODO my own action event
            try {
                switchToScene(event, "/scenes/winner.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Add balance to the user.
     */
    public void addBalance() {
        process.addBalance();
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
