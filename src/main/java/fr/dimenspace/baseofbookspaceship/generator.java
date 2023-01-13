package fr.dimenspace.baseofbookspaceship;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

class ship {

    private int shipId;
    private String shipName;
    private String companyName;
    private int places;
    private int placesBooked;
    private LocalDateTime boardingDate;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private int price;
    private int flightDuration; // in days
    private boolean cryostase;



    public ship(int shipId, String shipName, String companyName, int places, int placesBooked,
                LocalDateTime boardingDate, LocalDateTime departureDate, LocalDateTime arrivalDate, int price, int flightDuration, boolean cryostase) {
        this.shipId = shipId;
        this.shipName = shipName;
        this.companyName = companyName;
        this.places = places;
        this.placesBooked = placesBooked;
        this.boardingDate = boardingDate;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
        this.flightDuration = flightDuration;
        this.cryostase = cryostase;
    }
}
public class generator {

    public static void main(String[] args) {

        // en premier il faut générer les noms et les ids
        List<String> shipNames = List.of("The Star Explorer","The Star Queen","The Galaxy Voyager",
                "The Cosmic Explorer","The Nebula Cruiser","The Deep Space Diver","The Starbird",
                "The Star Serpent","The Star Breeze","The Star Spray","The Star Dragon","The Star Horse",
                "The Star Rover","The Star Pearl","The Star Adventurer","The Star Hunter","The Star Explorer II",
                "The Star Monarch","The Galaxy Voyager II","The Star Dragon II");

        List<String> companyNames = List.of("SpaceX", "Boeing", "Blue Origin", "Arianespace", "Virgin Galactic",
                                            "Lockheed Martin", "Northrop Grumman", "SNC");
        // merci ChatGPT pour les noms
        Random rand = new Random();
        int randomInt = rand.nextInt(1000000);

        // fait en sorte que les Ids fassent 6 chiffres
        String shipId = String.format("%06d", randomInt);




    }
}
