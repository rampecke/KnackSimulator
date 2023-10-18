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

        //Search for Card Swap with highest highest point on HandCards after swap
        for (int i = 0; i < cardsHand.size(); i++) {
            for(int j = 0; j < cardsTable.size(); j++) {
                ArrayList<Card> newArrayHand = new ArrayList<Card>(cardsHand);
                ArrayList<Card> newArrayTable = new ArrayList<Card>(cardsTable);

                Card hand = cardsHand.get(i);
                Card table = cardsTable.get(j);

                newArrayTable.remove(j);
                newArrayTable.add(j, hand);

                newArrayHand.remove(i);
                newArrayHand.add(i, table);

                if(pointsInCards(newArrayHand) == pointsOnHandAfterBestSwapp) {
                    //if both options are the same pointwise then we want to drop the card with lower value
                    Card handBestSwapOld = cardsHand.get(indexHandBestSwap);
                    Card handBestSwapNew = cardsHand.get(i);

                    //System.out.println("Old: " + handBestSwapOld.toString() + "or New: " + handBestSwapNew.toString());

                    if(handBestSwapOld.getCardValue() > handBestSwapNew.getCardValue()) {
                        indexHandBestSwap = i;
                        indexTabelBestSwap = j;
                        pointsOnHandAfterBestSwapp = pointsInCards(newArrayHand);
                        //System.out.println(handBestSwapNew.toString() + "was smaller, so it will be swapped");
                    } /*else {
                        System.out.println(handBestSwapOld.toString() + "was smaller or same, so the old will be swapped");
                    }*/
                } else if (pointsInCards(newArrayHand) > pointsOnHandAfterBestSwapp) {
                    indexHandBestSwap = i;
                    indexTabelBestSwap = j;
                    pointsOnHandAfterBestSwapp = pointsInCards(newArrayHand);
                }
            }
        }

        return new SwapTuple(indexHandBestSwap, indexTabelBestSwap, pointsOnHandAfterBestSwapp);
    }
}
