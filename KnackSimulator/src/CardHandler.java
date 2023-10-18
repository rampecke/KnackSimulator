import java.util.ArrayList;
import java.util.stream.Collectors;

public class CardHandler {

    public int pointsInCards(ArrayList<Card> cards) {
        int cardInHandDimonds = cards.stream().filter(karte -> karte.getCardColor() == CardColor.DIMONDS).map(karte -> karte.getCardValue()).reduce(0, Integer::sum);
        int cardInHandClubs = cards.stream().filter(karte -> karte.getCardColor() == CardColor.CLUBS).map(karte -> karte.getCardValue()).reduce(0, Integer::sum);
        int cardInHandHearts = cards.stream().filter(karte -> karte.getCardColor() == CardColor.HEARTS).map(karte -> karte.getCardValue()).reduce(0, Integer::sum);
        int cardInHandSpades = cards.stream().filter(karte -> karte.getCardColor() == CardColor.SPADES).map(karte -> karte.getCardValue()).reduce(0, Integer::sum);

        return Math.max(Math.max(cardInHandClubs, cardInHandSpades), Math.max(cardInHandHearts, cardInHandDimonds));
    }

    public SwapTuple findBestSwap(ArrayList<Card> cardsHand, ArrayList<Card> cardsTable) {
        int indexHandBestSwap = 0;
        int indexTabelBestSwap = 0;
        int pointsOnHandAfterBestSwapp = 0;

        for (int i = 0; i < cardsHand.size(); i++) {
            for(int j = 0; j < cardsTable.size(); j++) {
                ArrayList<Card> newArrayHand = cardsHand;
                ArrayList<Card> newArrayTable = cardsTable;

                Card hand = newArrayHand.get(i);
                Card table = newArrayTable.get(i);

                cardsTable.remove(j);
                cardsTable.add(j, hand);

                cardsHand.remove(i);
                cardsHand.add(i, table);

                if (pointsInCards(cardsHand) > pointsOnHandAfterBestSwapp) {
                    indexHandBestSwap = i;
                    indexTabelBestSwap = j;
                    pointsOnHandAfterBestSwapp = pointsInCards(cardsHand);
                }
            }
        }

        return new SwapTuple(indexHandBestSwap, indexTabelBestSwap, pointsOnHandAfterBestSwapp);
    }
}
