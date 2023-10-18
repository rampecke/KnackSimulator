import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cardInHand = new ArrayList<Card>();
    private String playerName;

    private Game game;

    private Boolean playerKlopft = false;

    public Player(String playerName, Game game) {
        this.playerName = playerName;
        this.game = game;
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

    public int pointsCardInHands() {
        PointCalculator pointCalculator = new PointCalculator();
        return pointCalculator.pointsInCards(cardInHand);
    }

    public void changeOneCard(int indexCardInPlayerHand, int indexCardOnTableCards) {
        if(pointsCardInHands() >= 31 || playerKlopft) {
            game.endGame();
        } else {
            ArrayList<Card> cardOnTable = game.getCardOnTable();

            if (indexCardInPlayerHand >= cardInHand.size() || indexCardOnTableCards >= cardOnTable.size()) {return;}

            Card cardPlayer = cardInHand.get(indexCardInPlayerHand);
            Card cardTable = cardOnTable.get(indexCardOnTableCards);

            cardOnTable.remove(indexCardOnTableCards);
            cardOnTable.add(indexCardOnTableCards, cardPlayer);

            cardInHand.remove(indexCardInPlayerHand);
            cardInHand.add(indexCardInPlayerHand, cardTable);

            System.out.println(this.playerName + ": Changed HandOfPlayerCard: " + (indexCardInPlayerHand + 1) + " with TableCard: " + (indexCardOnTableCards + 1));
            game.printHandandTableCards();

            game.nextPlayer();
        }
    }

    public void swapHandWithTableCards() {
        if(pointsCardInHands() >= 31 || playerKlopft) {
            game.endGame();
        } else {
            ArrayList<Card> cardOnTable = game.getCardOnTable();

            game.setCardOnTable(this.cardInHand);
            this.setCardInHand(cardOnTable);

            System.out.println( this.playerName + ": Swapped HandCards with TableCards");
            game.printHandandTableCards();

            game.nextPlayer();
        }
    }

    public void playerKnocked() {
        if(pointsCardInHands() >= 31 || playerKlopft) {
            game.endGame();
        } else {
            System.out.println(playerName + ": Has Knocked\n");
            System.out.println("__________________________ \n");
            game.nextPlayer();

            playerKlopft = true;
        }
    }

    public void pass() {
        if(pointsCardInHands() >= 31 || playerKlopft) {
            game.endGame();
        } else {
            System.out.println(playerName + ": Has Passed\n");
            System.out.println("__________________________ \n");
            game.nextPlayer();
        }
    }

    //Helper print function
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
