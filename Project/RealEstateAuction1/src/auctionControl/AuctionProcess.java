package auctionControl;

import interfaces.AuctionController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import properties.Property;
import storage.Storage;
import users.Admin;
import users.Bot;
import users.User;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AuctionProcess implements Serializable { // singleton class
    public static final long serialVersionUID = 123456789;

    private User user;
    private Property property;
    private boolean isOwned = false;
    private long currentPrice;
    private AuctionController auctionController;
    private int counter = 5;
    private Timer timer = new Timer();
    private boolean paused = false;
    private ArrayList<Bot> bots = createBots();
    private Bot currentBot;

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

    public Bot getCurrentBot() {
        return currentBot;
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

        auctionController.setNameL(user.getName());
        auctionController.appendBalanceL(formatPrice(user.getBalance()));

        restartTimer();
    }

    public User getUser() {
        return user;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
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

                    if(counter > 0 && !paused) {
                        counter--;
                        boolean pressure = false;
                        if(counter < 3) {
                            pressure = true;
                        }
                        boolean interested = calculateInterested();

                        notifyAllBots(pressure, interested);
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    public void addBalance() {
        user.setBalance(user.getBalance() + 10000000);
        auctionController.addToTextArea("Added $ 10 M to balance");
        auctionController.setBalanceL("Balance: " + formatPrice(user.getBalance() + 10000000));
    }


    public boolean calculateInterested() {
        boolean interested = false;
        if(Math.random() > 0.8) {
            interested = true;
            auctionController.setInterest(true);
        } else {
            auctionController.setInterest(false);
        }
        return interested;
    }

    public void endAuction() {
        if(isOwned) {
            user.buy(currentPrice, property);
            auctionController.addToTextArea("\nCongratulations! You have just bought the " + property.getName() + "\n");
        } else {
            auctionController.addToTextArea("\n" + getCurrentBot().getName() + " has just bought the " + property.getName() + "\n");
        }
    }

    public void bid(Bot bot) {
        autoBid((long) ((double) currentPrice * 1.1), bot);
        botBid();
    }

    public void notifyAllBots(boolean pressure, boolean interested) { // TODO observer - notify all objects about a certain event

        double press = 0;
        if(pressure) {
            press = 0.05;
        }

        double interest = 0;
        if(interested) {
            interest = 0.1;
        }

        for(Bot bot : bots) {

            if(botBidding(press, interest)) {
                bot.bid((long) ((double) currentPrice * 1.1));
                bid(bot);
                break;
            }

        }
    }

    public boolean botBidding(double pressure, double interest) {
        if((currentPrice - property.getPrice()) < (property.getPrice() * 0.3)) {
            if(Math.random() > (0.8 - pressure - interest)) {
                return true;
            }
        }else if((currentPrice - property.getPrice()) < (property.getPrice() * 0.5)) {
            if(Math.random() > (0.85 - pressure - interest)) {
                return true;
            }
        }else if((currentPrice - property.getPrice()) < (property.getPrice() * 0.8)) {
            if(Math.random() > (0.9 - pressure - interest)) {
                return true;
            }
        }else if((currentPrice - property.getPrice()) < (property.getPrice() * 1.5)) {
            if(Math.random() > (0.97 - pressure - interest)) {
                return true;
            }
        }else if((currentPrice - property.getPrice()) < (property.getPrice() * 2)) {
            if(Math.random() > (0.99 - pressure - interest)) {
                return true;
            }
        }else if((currentPrice - property.getPrice()) >= (property.getPrice() * 2)) {
            if(Math.random() > (0.99 - interest)) {
                return true;
            }
        }
        return false;
    }


    public void botBid() {
        increaseAndRestart();
    }

    public void bidBtnClicked() {
        long biddingPrice = Long.parseLong(auctionController.getBidPrice());

        if(user.getBalance() < biddingPrice) {
            auctionController.addToTextArea("You don't have enough money.");
            return;
        }

        if(biddingPrice >= currentPrice) {
            increaseAndRestart();
            user.bid(biddingPrice);
            isOwned = true;
            auctionController.addToTextArea("*You* " + user.getName() + " just bid " + formatPrice(currentPrice));
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

    public void autoBid(long price, Bot bot) {
        currentBot = bot;
        auctionController.addToTextArea(bot.getName() + " just bid " + formatPrice(price));
        isOwned = false;
    }

    public ArrayList<Bot> createBots() {
        ArrayList<Bot> bots = new ArrayList<>();
        for(int i = 0; i<15; i++) {
            bots.add(new Bot(randomName()));
        }
       return bots;
    }


    public long getCurrentPrice() {
        return currentPrice;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public String randomName() {
        Random rand = new Random();
        String names = "Rachel Joey Ross Phoebe Chandler Monica Brad Alexa Jonas Tommy Arthur Mike Miley Brian Max George Jose Siri Chris";
        String[] namesArray = names.split(" ");
        return namesArray[rand.nextInt(namesArray.length)];
    }

    public void createUser(String username, long balance) {
        user = new User(username, balance);
    }

    public void createAdmin(String username, long balance) {
        user = new Admin(username, balance);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }


}
