package sample;

import javafx.application.Platform;
import javafx.scene.image.Image;
import properties.Property;
import users.Bot;
import users.User;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AuctionProcess { // singleton class

    private User user;
    private Property property;
    private boolean isOwned = false;
    private long currentPrice;
    private AuctionController auctionController;
    private RegisterController registerController;
    private int counter = 5;
    private Timer timer = new Timer();
    private String randomName;

    public static AuctionProcess auctionProcess;

    private AuctionProcess(){}

    public static AuctionProcess getInstance() {
        if(auctionProcess == null) {
            auctionProcess = new AuctionProcess();
        }
        return auctionProcess;
    }

    public void setLocalAuctionController(AuctionController auctionController) {
        this.auctionController = auctionController;
    }

    public void setLocalRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }

    public String getRandomName() {
        return randomName;
    }

    public void initialize() {
        Storage storage = new Storage();
        storage.createProperties();
        property = storage.generateRandomProperty();
        currentPrice = property.getPrice();

        auctionController.setTitle(property.getName()); // set title
        updatePrice(property.getPrice()); // set price

        String imageUrl = property.getImageURL().substring(1);
        Image img = new Image(getClass().getResource(imageUrl).toString()); // set image url
        auctionController.setImage(img);

        long futurePrice = (long) ((double)property.getPrice() * 1.1); // future price instead of current price
        futurePrice = (futurePrice + 5000) / 10000 * 10000;
        auctionController.setBidPrice(String.valueOf(futurePrice));

        restartTimer();
    }

    public void updatePrice(long initialPrice) {
        auctionController.setPrice(formatPrice(initialPrice));
    }

    public String formatPrice(long price) {
        if(price > 1000000000) {
            long currPrice = price / 100000000;
            if(currPrice < 100) {
                DecimalFormat df = new DecimalFormat("#.#");
                return "$" + String.valueOf(df.format((double)currPrice / 10)) + " B";
            }else {
                return "$" + String.valueOf(currPrice / 10) + " B";
            }
        }else if(price > 1000000) {
            long currPrice = price / 100000;
            if(currPrice < 100) {
                DecimalFormat df = new DecimalFormat("#.#");

                return "$" + String.valueOf(df.format((double)currPrice / 10)) + " M";
            }else {
                return "$" + String.valueOf(currPrice / 10) + " M";
            }
        }else if(price > 1000) {
            long currPrice = price / 1000;
            return "$ " + String.valueOf(currPrice) + " K";
        }else {
            return "$ " + String.valueOf(price);
        }
    }

    public void startTimer() {
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(() -> {
                    auctionController.setTimer(String.valueOf(counter));
                    if(counter == 0) {
                        endAuction();
                        auctionController.setBid();
                        timer.cancel();
                    }
                    if(counter > 0) {
                        counter--;
                        botBetting();
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    public void endAuction() {
        if(isOwned) {
            auctionController.addToTextArea("\nCongratulations! You have just bought the " + property.getName() + "\n");
        } else {
            auctionController.addToTextArea("\n" + randomName + " has just bought the " + property.getName() + "\n");
        }
    }

    public void botBetting() {
        // bot betting
        if((currentPrice - property.getPrice()) < (currentPrice * 0.3)) {
            if(Math.random() > 0.5) {
                autoBid((long) ((double) currentPrice * 1.1)); // 50% opportunity every second
                botBid();
            }
        }else if((currentPrice - property.getPrice()) < (currentPrice * 0.5)) {
            if(Math.random() > 0.7) {
                autoBid((long) ((double) currentPrice * 1.1)); // 30% opportunity every second
                botBid();
            }
        }else if((currentPrice - property.getPrice()) < (currentPrice * 0.8)) {
            if(Math.random() > 0.8) {
                autoBid((long) ((double) currentPrice * 1.1)); // 20% opportunity every second
                botBid();
            }
        }else if((currentPrice - property.getPrice()) < (currentPrice * 1.5)) {
            if(Math.random() > 0.9) {
                autoBid((long) ((double) currentPrice * 1.1)); // 10% opportunity every second
                botBid();
            }
        }else if((currentPrice - property.getPrice()) >= (currentPrice * 1.5)) {
            if(Math.random() > 0.95) {
                autoBid((long) ((double) currentPrice * 1.1)); // 5% opportunity every second
                botBid();
            }
        }
    }

    public void botBid() {
        increaseAndRestart();
    }

    public void bidBtnClicked() {
        long biddingPrice = Long.parseLong(auctionController.getBidPrice());

        if(user.getBalance() < biddingPrice) {
            auctionController.addToTextArea("You don't have enough money.\n");
            return;
        }

        if(biddingPrice >= currentPrice) {
            increaseAndRestart();
            user.bid(biddingPrice);
            isOwned = true;
            auctionController.addToTextArea("*You*" + user.getName() + " just bid " + formatPrice(currentPrice));
        } else {
            auctionController.setBidPrice(String.valueOf(biddingPrice));
        }
    }

    public void increaseAndRestart() {
        currentPrice = Long.parseLong(auctionController.getBidPrice());
        updatePrice(currentPrice);
        long futurePrice = (long) ((double)currentPrice * 1.1);
        futurePrice = (futurePrice + 500) / 1000 * 1000;
        auctionController.setBidPrice(String.valueOf(futurePrice));
        restartTimer();
    }

    public void restartTimer() {
        auctionController.setTimer(String.valueOf(5));
        counter = 5;
        timer.cancel();
        timer = new Timer();
        startTimer();
    }

    public void autoBid(long price) {
        randomName = randomName();
        Bot bot = new Bot(randomName);
        bot.bid(price);
        auctionController.addToTextArea(randomName + " just bid " + formatPrice(price));
        isOwned = false;
    }


    public long getCurrentPrice() {
        return currentPrice;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public String randomName() {
        Random rand = new Random();
        String names = "Rachel Joey Ross Phoebe Chandler Monica Toby Todd Brad Alex Alexa Jonas Tommy Arthur Mike Miley Alice Adele Brian Max George Jenny Tim Peter John Jose";
        String[] namesArray = names.split(" ");
        return namesArray[rand.nextInt(namesArray.length)];
    }

    public void createUser(String username, long balance) {
        user = new User(username, balance);
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }


}
