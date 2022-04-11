package auction;

import properties.Property;
import users.Bot;
import users.User;

import java.util.Random;

public class AuctionProcess { // singleton class

    public static AuctionProcess auctionProcess;

    private User user;
    private Property property;
    private boolean isOwned = false;
    private long currentPrice;

    private AuctionProcess(){}

    public static AuctionProcess getInstance() {
        if(auctionProcess == null) {
            auctionProcess = new AuctionProcess();
        }
        return auctionProcess;
    }

    public void autoBid(long price) {
        Bot bot = new Bot(randomName());
        bot.bid(price);
        currentPrice = price;
        isOwned = false;
    }

    public void bid(long price) {
        isOwned = true;
        currentPrice = price;
        user.bid(price);
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
