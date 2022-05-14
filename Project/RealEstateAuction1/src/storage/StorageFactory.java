package storage;

import properties.*;

import java.util.Random;

public class StorageFactory {

    // TODO Factory design pattern - creates all properties

    public Property generateRandomProperty(Bridge bridgeArray[], BusinessCentre centreArray[], Flat flatArray[], Hotel hotelArray[], Residence residenceArray[]) {
        Random rand = new Random();
        int firstRandom = rand.nextInt(5);
        int secondRandom = rand.nextInt(5);
        if(firstRandom == 0) {
            return bridgeArray[secondRandom];
        }
        if(firstRandom == 1) {
            return centreArray[secondRandom];
        }
        if(firstRandom == 2) {
            return flatArray[secondRandom];
        }
        if(firstRandom == 3) {
            return hotelArray[secondRandom];
        }
        return residenceArray[secondRandom];
    }

    public Bridge createBridge(String name, long price, String imageURL) {
        return new Bridge(name, price, imageURL);
    }
    public BusinessCentre createBusinessCentre(String name, long price, String imageURL) {
        return new BusinessCentre(name, price, imageURL);
    }
    public Flat createFlat(String name, long price, String imageURL) {
        return new Flat(name, price, imageURL);
    }
    public Hotel createHotel(String name, long price, String imageURL) {
        return new Hotel(name, price, imageURL);
    }
    public Residence createResidence(String name, long price, String imageURL) {
        return new Residence(name, price, imageURL);
    }





}
