package users;

import java.util.Random;

public class Bot extends User { //  example of inheritance

    private String name;

    public Bot(String name) {
        super(name, 999999999);
        this.name = name;
    }

    @Override
    public void bid(long price) {
        System.out.println(name + " just bid $ " + price);
    }

    public String getName() {
        return this.name;
    }
}
