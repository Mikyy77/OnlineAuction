package properties;

import javafx.scene.image.Image;

public abstract class Property {
    String name;
    double price;
    Image image;
    boolean sold = false;

    public Property(String name, double price, Image image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSold() {
        return sold;
    }

    public void sell() {
        sold = true;
    }

}

