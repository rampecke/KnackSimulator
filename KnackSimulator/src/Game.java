import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    private KartenDeck cardDeck = new KartenDeck();
    private ArrayList<Card> cardOnTable = new ArrayList<Card>();

    private int playingPlayerIndex = 0;
    private int playerKlopftIndex = -1;

    private Boolean gameEnde = false;
    private int[] indexOfLoser = new int[] {};

    public Game(int numberOfPlayers) {
        for(int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player("Player" + (i + 1)));
        }
        System.out.println("Game created");

        distributeCards();

        System.out.println();
    }

    private void distributeCards() {
        for(Player player: players) {
            player.setCardInHand(cardDeck.getThreeCardsFromCardDeck());
        }

        this.cardOnTable = cardDeck.getThreeCardsFromCardDeck();

        System.out.println("Cards distributed");
    }

    public void normalPlayerPlay() {
        //Game is Already over
        if (gameEnde) {
            System.out.println("Game is over");
            return;
        }

        //Player Knopft and Round is over
        if(playerKlopftIndex == playingPlayerIndex) {
            endGame();
        }

        //Check if player won already
        if (players.get(playingPlayerIndex).pointsCardInHands() >= 31) {
            endGame();
        }

        //Swapp all if Cards on Table are higher then own
        if (players.get(playingPlayerIndex).pointsCardInHands() < pointsCardOnTable()) {
            swapHandWithTableCards();
            nextPlayer();
            return;
        } else {
            
        }
    }

    private void endGame() {
        gameEnde = true;

        //indexOfLoser = players.stream().reduce(0, (acc, currentPlayer) -> currentPlayer.pointsCardInHands() < acc.pointsCardInHands());

        System.out.println("Game is over");
    }

    private int pointsCardOnTable() {
        PointCalculator pointCalculator = new PointCalculator();
        return pointCalculator.pointsInCards(cardOnTable);
    }

    public void selectFirstPlayerToStart() {
        Random rand = new Random();
        playingPlayerIndex = rand.nextInt(players.size());

        System.out.println("First player is: " + players.get(playingPlayerIndex).getPlayerName());
        printHandandTableCards();
    }

    public void nextPlayer() {
        if( playingPlayerIndex == players.size() - 1 ) {
            playingPlayerIndex = 0;
        } else {
            playingPlayerIndex++;
        }

        System.out.println("Now playing: " + players.get(playingPlayerIndex).getPlayerName());
        printHandandTableCards();
    }

    public void changeOneCard(int indexCardInPlayerHand, int indexCardOnTableCards) {
        ArrayList<Card> cardInHandPlayer = players.get(playingPlayerIndex).getCardInHand();

        if (indexCardInPlayerHand >= cardInHandPlayer.size() || indexCardOnTableCards >= cardOnTable.size()) {return;}

        Card cardPlayer = cardInHandPlayer.get(indexCardInPlayerHand);
        Card cardTable = this.cardOnTable.get(indexCardOnTableCards);

        cardOnTable.remove(indexCardOnTableCards);
        cardOnTable.add(indexCardOnTableCards, cardPlayer);

        cardInHandPlayer.remove(indexCardInPlayerHand);
        cardInHandPlayer.add(indexCardInPlayerHand, cardTable);

        players.get(playingPlayerIndex).setCardInHand(cardInHandPlayer);

        System.out.println(players.get(playingPlayerIndex).getPlayerName() + ": Changed HandOfPlayerCard: " + (indexCardInPlayerHand + 1) + " with TableCard: " + (indexCardOnTableCards + 1));
        printHandandTableCards();
    }

    public void swapHandWithTableCards() {
        ArrayList<Card> cardInHandPlayer = players.get(playingPlayerIndex).getCardInHand();

        players.get(playingPlayerIndex).setCardInHand(this.cardOnTable);
        this.cardOnTable = cardInHandPlayer;

        System.out.println(players.get(playingPlayerIndex).getPlayerName() + ": Swapped HandCards with TableCards");
        printHandandTableCards();
    }

    public void playerKnocked() {
        playerKlopftIndex = playingPlayerIndex;

        System.out.println(players.get(playingPlayerIndex).getPlayerName() + ": Has Knocked\n");
        System.out.println("__________________________ \n");
    }

    public void pass() {
        System.out.println(players.get(playingPlayerIndex).getPlayerName() + ": Has Passed\n");
        System.out.println("__________________________ \n");

        this.nextPlayer();
    }

    //Helper
    private String cardsOnTableToString() {
        String returnString = "";
        int arraySize = this.cardOnTable.size();

        for (int i = 0; i < arraySize; i++) {
            returnString += "Karte " + ( i + 1) + ": "+ this.cardOnTable.get(i).toString();
            if(i != arraySize-1) {
                returnString += "\n";
            }
        }

        return returnString;
    }

    private void printHandandTableCards() {
        System.out.println("Karten in Hand: \n" + players.get(playingPlayerIndex).cardsInHandToString());
        System.out.println("Punktzahl: \n" + players.get(playingPlayerIndex).pointsCardInHands() + "\n");
        System.out.println("Karten on Table: \n" + this.cardsOnTableToString() + "\n");
        System.out.println("__________________________ \n");
    }
}
