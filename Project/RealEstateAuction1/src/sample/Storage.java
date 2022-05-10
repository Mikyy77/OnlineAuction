package sample;

import properties.*;

import java.util.Random;

public class Storage {

    // arrays

    private Bridge bridgeArray[] = new Bridge[5];
    private BusinessCentre centreArray[] = new BusinessCentre[5];
    private Flat flatArray[] = new Flat[5];
    private Hotel hotelArray[] = new Hotel[5];
    private Residence residenceArray[] = new Residence[5];

    public Storage() {
        createProperties();
    }

    public Property generateRandomProperty() {
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
        if(firstRandom == 4) {
            return residenceArray[secondRandom];
        }
        return null;
    }

    public void createProperties(){

        // create bridges

        String names = "Golden Gate Bridge,Brooklyn Bridge,Tower Bridge,Ponte di Rialto,Ponte Vecchio";
        String[] namesArray = names.split(",");
        int prices[] = {50000000, 350000000, 90000000, 80000000, 40000000};
        String[] imageURLs = {"@../images/goldengate.jpeg",
                "@../images/brooklyn.jpeg",
                "@../images/towerbridge.jpeg",
                "@../images/rialto.jpeg",
                "@../images/pontevecchio.jpeg"};
        for(int i = 0; i < 5; i++) {
            Bridge bridge = new Bridge(namesArray[i], prices[i], imageURLs[i]);
            bridgeArray[i] = bridge;
        }

        // business centres

        names = "Burj Khalifa,Petronas Towers,Chrysler Building,Flatiron Building,Empire State Building";
        namesArray = names.split(",");
        prices[0] = 990000000; prices[1] =690000000; prices[2] =340000000; prices[3] =360000000; prices[4] =550000000;
        imageURLs[0] = "@../images/burjkhalifa.jpeg";
        imageURLs[1] = "@../images/petronas.jpeg";
        imageURLs[2] = "@../images/chrysler.jpeg";
        imageURLs[3] = "@../images/flatiron.jpeg";
        imageURLs[4] = "@../images/empirestate.jpeg";
        for(int i = 0; i<namesArray.length; i++) {
            BusinessCentre centre = new BusinessCentre(namesArray[i], prices[i], imageURLs[i]);
            centreArray[i] = centre;
        }

        // hotels

        names = "Burj Al Arab,The Plaza,Beverly Hills Hotel,Claridge's,The Ritz";
        namesArray = names.split(",");
        prices[0] = 450000000; prices[1] =420000000; prices[2] =380000000; prices[3] =310000000; prices[4] =390000000;
        imageURLs[0] = "@../images/burjalarab.jpeg";
        imageURLs[1] = "@../images/theplaza.jpg";
        imageURLs[2] = "@../images/beverly.jpg";
        imageURLs[3] = "@../images/claridges.jpg";
        imageURLs[4] = "@../images/ritz.jpg";
        for(int i = 0; i<namesArray.length; i++) {
            Hotel hotel = new Hotel(namesArray[i], prices[i], imageURLs[i]);
            hotelArray[i] = hotel;
        }

        // houses

        names = "Tiger Woods's house,Ellen DeGeneres' house,Tom Cruise's house,Beyonce's house,Graceview Estate";
        namesArray = names.split(",");
        prices[0] = 50000000; prices[1] =45000000; prices[2] =75000000; prices[3] =82000000; prices[4] =24000000;
        imageURLs[0] = "@../images/tiger.jpg";
        imageURLs[1] = "@../images/ellen.jpg";
        imageURLs[2] = "@../images/tomcruise.jpg";
        imageURLs[3] = "@../images/beyonce.jpg";
        imageURLs[4] = "@../images/mansion.jpg";
        for(int i = 0; i<namesArray.length; i++) {
            Residence residence = new Residence(namesArray[i], prices[i], imageURLs[i]);
            residenceArray[i] = residence;
        }

        // flats

        names = "Cozy Nest Apartment,Honest Home,Safe N Secure Living,Welcome Retreat,The Comfort Court";
        namesArray = names.split(",");
        prices[0] = 450000; prices[1] =650000; prices[2] =320000; prices[3] =560000; prices[4] =390000;
        imageURLs[0] = "@../images/flat1.jpg";
        imageURLs[1] = "@../images/flat2.jpg";
        imageURLs[2] = "@../images/flat3.jpg";
        imageURLs[3] = "@../images/flat4.jpg";
        imageURLs[4] = "@../images/flat5.jpg";
        for(int i = 0; i<namesArray.length; i++) {
            Flat flat = new Flat(namesArray[i], prices[i], imageURLs[i]);
            flatArray[i] = flat;
        }

    }

}
