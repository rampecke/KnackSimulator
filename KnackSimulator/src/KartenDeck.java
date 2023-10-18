import java.util.ArrayList;
import java.util.Random;

public class KartenDeck {
    private ArrayList<Card> kartenDeck;

    public KartenDeck() {
        this.kartenDeck = new ArrayList<Card>();

        for (CardValue cardValue : CardValue.values()) {
            for (CardColor cardColor : CardColor.values()) {
                kartenDeck.add(new Card(cardValue, cardColor));
            }
        }
    }

    public int size() {
        return this.kartenDeck.size();
    }

    public ArrayList<Card> getKartenDeck() {
        return kartenDeck;
    }

    public void setKartenDeck(ArrayList<Card> kartenDeck) {
        this.kartenDeck = kartenDeck;
    }

    public ArrayList<Card> getThreeCardsFromCardDeck() {
        ArrayList<Card> returnCards = new ArrayList<Card>();
        Random rand = new Random();

        if (this.kartenDeck.size() >= 3) {
            for (int i = 0; i<3; i++) {
                int randomIndex = rand.nextInt(this.kartenDeck.size());
                returnCards.add(kartenDeck.get(randomIndex));
                kartenDeck.remove(randomIndex);
            }
        }

        return returnCards;
    }
}
