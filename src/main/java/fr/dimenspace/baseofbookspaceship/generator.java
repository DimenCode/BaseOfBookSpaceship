package fr.dimenspace.baseofbookspaceship;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private boolean isFull;



    public ship(int shipId, String shipName, String companyName, int places, int placesBooked,
                LocalDateTime boardingDate, LocalDateTime departureDate, LocalDateTime arrivalDate, int price, int flightDuration, boolean cryostase, boolean isFull) {
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
        this.isFull = isFull;
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


        // je crée une liste de tous mes vaisseaux que je vais générer ..
        List<ship> shipList = new ArrayList<>();

        // .. juste ici avec cette boucle, je met pas 20 au cas où je veuille ajouter d'autres noms plus tard
        for (int i = 0; i < shipNames.size(); i++) {

            // trouve un id aléatoire à 6 chiffres allant de 000000 à 999999
            Random rand = new Random();
            int randomInt = rand.nextInt(1000000);
            String shipId = String.format("%06d", randomInt); // fait en sorte que les Ids fassent 6 chiffres

            String shipName = shipNames.get(i); // prends un nom dans la liste

            // prend un nom d'entreprise au hasard dans la liste (pareil, c'est relatif à la taille)
            int placeInTheList = rand.nextInt(companyNames.size());
            String companyName = companyNames.get(placeInTheList);

            // le prix va dépendre de `placesBooked`, `cryostase` et de `flightDuration`
            // + de `places` , - chère
            // + de `placesBooked` / rapport à `places` , + chère
            // `cryostase` = true , plus chère
            // + de `flightDuration` , - chère

            int places = rand.nextInt(281) + 20; // nombre de places aléatoire entre 20 et 300

            double doublePrice = (1.0/places) * 1000000; // 300 = 3 333 ; 20 = 50 000

            // flightDuration est entre 120 et 365 jours et ne dépend pas du prix
            int flightDuration = rand.nextInt(246) + 120;
            doublePrice = doublePrice * (1.0/flightDuration * 100); // +27% pour 365 ; +83% pour 120

            // placesBooked est un nombre aléatoire entre 20% des places et +20% des place
            // si placesBooked >= places, alors le vol sera complet
            // c'est pour ne pas avoir 1% ou moins de chances pour que le vol soit complet

            double calcul_1 = places - (places * 0.8);
            int minBooked = (int) calcul_1;

            double calcul_2 = places * 1.2;
            int maxBooked = (int) calcul_2;

            double placesBooked = rand.nextInt(maxBooked - minBooked + 1) + minBooked;
            boolean isFull = placesBooked >= places; // bon euh ça se voit je crois

            // 1 chance sur 10 qu'il y ait une cryostase
            boolean cryostase = rand.nextInt(10) == 0;
            if (cryostase) {
                doublePrice = doublePrice - (doublePrice * 0.15); // -15% du prix si il y a cryostase
            }

            // le prix final sera influencé par le nombre de places restantes, calculé au-dessus
            // c'est assez simple : 70-79% réservées = +15% ; 80-89% réservées = +25% ; >=90% réservées = +35%
            float ratio = (float) placesBooked / places;
            if (ratio <= 0.79 && ratio >= 0.7) {
                doublePrice = doublePrice * 1.15; // +15%
            }
            else if (ratio <= 0.89 && ratio >= 0.8) {
                doublePrice = doublePrice * 1.25; // +25%
            }
            else if (ratio >= 0.9) {
                doublePrice = doublePrice * 1.35; // +35%
            }

            // et pour en finir avec le prix, j'en fais un int et je *100
            int price = (int) doublePrice * 100; // le prix est + élevé et c'est + réaliste d'avoir au moins 2 zéros
                                                // à la fin d'un prix aussi grand

            // maintenant je m'occupe des dates
        }

    }
}
