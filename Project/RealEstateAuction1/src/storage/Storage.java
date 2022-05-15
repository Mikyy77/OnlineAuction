package storage;

import properties.*;


public class Storage {

    // arrays

    private final Bridge[] bridgeArray = new Bridge[5];
    private final BusinessCentre[] centreArray = new BusinessCentre[5];
    private final Flat[] flatArray = new Flat[5];
    private final Hotel[] hotelArray = new Hotel[5];
    private final Residence[] residenceArray = new Residence[5];

    public Storage() {
        createProperties();
    }

    /**
     * Method that generates a random property in a separate class because of using the factory method design pattern.
     * @return
     */
    public Property generateRandomProperty() {
        StorageFactory factory = new StorageFactory();
        return factory.generateRandomProperty(bridgeArray, centreArray, flatArray, hotelArray, residenceArray);
    }

    /**
     * Creates properties of various types by using factory method design pattern
     */
    public void createProperties(){

        StorageFactory factory = new StorageFactory();

        // create bridges

        String names = "Golden Gate Bridge,Brooklyn Bridge,Tower Bridge,Ponte di Rialto,Ponte Vecchio";
        String[] namesArray = names.split(",");
        long[] prices = {5000000, 3500000, 9000000, 8000000, 4000000};
        String[] imageURLs = {"@../images/goldengate.jpeg",
                "@../images/brooklyn.jpeg",
                "@../images/towerbridge.jpeg",
                "@../images/rialto.jpeg",
                "@../images/pontevecchio.jpeg"};


        for(int i = 0; i < 5; i++) {
            bridgeArray[i] = factory.createBridge(namesArray[i], prices[i], imageURLs[i]);
        }

        // business centres

        names = "Burj Khalifa,Petronas Towers,Chrysler Building,Flatiron Building,Empire State Building";
        namesArray = names.split(",");
        prices[0] = 99000000; prices[1] =69000000; prices[2] =34000000; prices[3] =36000000; prices[4] =55000000;
        imageURLs[0] = "@../images/burjkhalifa.jpeg";
        imageURLs[1] = "@../images/petronas.jpeg";
        imageURLs[2] = "@../images/chrysler.jpeg";
        imageURLs[3] = "@../images/flatiron.jpeg";
        imageURLs[4] = "@../images/empirestate.jpeg";
        for(int i = 0; i<namesArray.length; i++) {
            centreArray[i] = factory.createBusinessCentre(namesArray[i], prices[i], imageURLs[i]);
        }

        // hotels

        names = "Burj Al Arab,The Plaza,Beverly Hills Hotel,Claridge's,The Ritz";
        namesArray = names.split(",");
        prices[0] = 45000000; prices[1] =42000000; prices[2] =38000000; prices[3] =31000000; prices[4] =39000000;
        imageURLs[0] = "@../images/burjalarab.jpeg";
        imageURLs[1] = "@../images/theplaza.jpg";
        imageURLs[2] = "@../images/beverly.jpg";
        imageURLs[3] = "@../images/claridges.jpg";
        imageURLs[4] = "@../images/ritz.jpg";
        for(int i = 0; i<namesArray.length; i++) {
            hotelArray[i] = factory.createHotel(namesArray[i], prices[i], imageURLs[i]);
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
            residenceArray[i] = factory.createResidence(namesArray[i], prices[i], imageURLs[i]);
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
            flatArray[i] = factory.createFlat(namesArray[i], prices[i], imageURLs[i]);
        }

    }

}
