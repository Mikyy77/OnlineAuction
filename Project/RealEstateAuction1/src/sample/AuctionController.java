package sample;

import javafx.scene.image.Image;

public interface AuctionController {
    void setPrice(String text);
    void setBidPrice(String text);
    String getBidPrice();
    void setTimer(String value);
    void setBid();
    void setTitle(String text);
    void setImage(Image img);
    void addToTextArea(String text);
}
