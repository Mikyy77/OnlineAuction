package properties;

public abstract class Property {
    String name;
    long price;
    String imageURL;
    boolean sold = false;

    public Property(String name, int price, String imageURL) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public boolean isSold() {
        return sold;
    }

    public void sell() {
        sold = true;
    }

}

