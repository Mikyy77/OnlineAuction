package interfaces;

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
    void setNameL(String name);
    void setBalanceL(String balance);
    void appendBalanceL(String balance);
    void setInterest(boolean isInterested);
}
