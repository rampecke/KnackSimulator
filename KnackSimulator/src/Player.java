import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cardInHand = new ArrayList<Card>();
    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<Card> getCardInHand() {
        return cardInHand;
    }

    public void setCardInHand(ArrayList<Card> cardInHand) {
        this.cardInHand = cardInHand;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int pointsCardInHands() {
        PointCalculator pointCalculator = new PointCalculator();
        return pointCalculator.pointsInCards(cardInHand);
    }

    public String cardsInHandToString() {
        String returnString = "";
        int arraySize = this.cardInHand.size();

        for (int i = 0; i < arraySize; i++) {
            returnString += "Karte " + (i + 1) + ": "+ this.cardInHand.get(i).toString();

            if(i != arraySize-1) {
                returnString += "\n";
            }
        }

        return returnString;
    }
}
