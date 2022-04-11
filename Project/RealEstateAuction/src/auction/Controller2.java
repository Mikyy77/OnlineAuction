package auction;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import properties.Property;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class Controller2 implements Initializable {
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
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Property property;
    private Timer timer;
    private int counter;
    private AuctionProcess process;
    private long currentPrice;


    public Controller2() {
        Storage storage = new Storage();
        storage.createProperties();
        property = storage.generateRandomProperty();
        timer = new Timer();
        counter = 5;
        startTimer();
        process = AuctionProcess.getInstance();
        currentPrice = property.getPrice();
        process.setProperty(property);
    }

    public Property getProperty() {
        return property;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setText(property.getName()); // set title
        updatePrice(property.getPrice()); // set price

        String imageUrl = property.getImageURL().substring(1);
        img = new Image(getClass().getResource(imageUrl).toString()); // set image url
        imageView.setImage(img);

        currentPrice = (long) ((double)property.getPrice() * 1.1);
        currentPrice = (currentPrice + 5000) / 10000 * 10000;
        bidPrice.setText(String.valueOf(currentPrice));
    }

    public void updatePrice(long initialPrice) {
        if(initialPrice > 1000000) { // set price
            long currPrice = initialPrice / 1000000;
            price.setText("$ " + String.valueOf(currPrice) + " M");
        }else if(initialPrice > 1000) {
            long currPrice = initialPrice / 1000;
            price.setText("$ " + String.valueOf(currPrice) + " K");
        }else {
            price.setText("$ " + String.valueOf(initialPrice));
        }
    }

    public int getCounter(){
        return counter;
    }

    public void startTimer() {
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(() -> {
                    timerLabel.setText(String.valueOf(counter));
                    if(counter == 0) {
                        setBidButton();
                        timer.cancel();
                        //callWinner();
//                        try {
//                            Thread.sleep(1000);
//                            callWinner();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                    if(counter > 0) {
                        counter--;
                    }
                    if(counter > 0) {
                        botBetting();
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    public void setBidButton() {
        bidButton.setPrefWidth(100);
        bidButton.setLayoutX(bidButton.getLayoutX() - 30);
        bidButton.setText("See Results");
        bidButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    switchToScene(event, "winner.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void botBetting() {
        // bot betting
        if((currentPrice - property.getPrice()) < (currentPrice * 0.3)) {
            if(Math.random() > 0.5) {
                process.autoBid((long) ((double) currentPrice * 1.1)); // 50% opportunity every second
                botBid();
            }
        }else if((currentPrice - property.getPrice()) < (currentPrice * 0.5)) {
            if(Math.random() > 0.7) {
                process.autoBid((long) ((double) currentPrice * 1.1)); // 30% opportunity every second
                botBid();
            }
        }else if((currentPrice - property.getPrice()) < (currentPrice * 0.8)) {
            if(Math.random() > 0.8) {
                process.autoBid((long) ((double) currentPrice * 1.1)); // 20% opportunity every second
                botBid();
            }
        }else if((currentPrice - property.getPrice()) < (currentPrice * 1.5)) {
            if(Math.random() > 0.9) {
                process.autoBid((long) ((double) currentPrice * 1.1)); // 10% opportunity every second
                botBid();
            }
        }else if((currentPrice - property.getPrice()) >= (currentPrice * 1.5)) {
            if(Math.random() > 0.95) {
                process.autoBid((long) ((double) currentPrice * 1.1)); // 5% opportunity every second
                botBid();
            }
        }
    }

    public void botBid() {
        increaseAndRestart();
    }

    public void bid() {
        increaseAndRestart();
        process.bid((long) ((double)currentPrice * 1.1));
    }

    public void increaseAndRestart() {
    	long newPrice = Long.parseLong(bidPrice.getText());
		currentPrice = newPrice; // issue - new price can be lower
        updatePrice(currentPrice);
        long futurePrice = (long) ((double)currentPrice * 1.1);
        futurePrice = (futurePrice + 5000) / 10000 * 10000;
        bidPrice.setText(String.valueOf(futurePrice));
        restartTimer();
    }

    public void restartTimer() {
        timerLabel.setText(String.valueOf(5));
        counter = 5;
        timer.cancel();
        timer = new Timer();
        startTimer();
    }

    public void switchToScene(ActionEvent event, String file) throws IOException {
        root = FXMLLoader.load(getClass().getResource(file));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goBack(ActionEvent event) throws IOException {
        switchToScene(event, "home.fxml");
    }
}
