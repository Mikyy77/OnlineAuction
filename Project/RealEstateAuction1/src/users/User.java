package users;

public class User {

    private String username;
    private long balance;

    public User(String username, long balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void bid(long price) {
        System.out.println("***You*** " + this.username + " just bid $ " + price);
    }

    public void buy(long price, long initialPrice) {

    }
}
