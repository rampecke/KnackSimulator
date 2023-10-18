import java.util.ArrayList;

public class PointCalculator {

    public int pointsInCards(ArrayList<Card> cards) {
        int cardInHandKaro = cards.stream().filter(karte -> karte.getFarbe() == Farbe.KARO).map(karte -> karte.getWert()).reduce(0, Integer::sum);
        int cardInHandHerz = cards.stream().filter(karte -> karte.getFarbe() == Farbe.HERZ).map(karte -> karte.getWert()).reduce(0, Integer::sum);
        int cardInHandPick = cards.stream().filter(karte -> karte.getFarbe() == Farbe.PICK).map(karte -> karte.getWert()).reduce(0, Integer::sum);
        int cardInHandKreuz = cards.stream().filter(karte -> karte.getFarbe() == Farbe.KREUZ).map(karte -> karte.getWert()).reduce(0, Integer::sum);

        return Math.max(Math.max(cardInHandHerz, cardInHandKreuz), Math.max(cardInHandPick, cardInHandKaro));
    }

}
