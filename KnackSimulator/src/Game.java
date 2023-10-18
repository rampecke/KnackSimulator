import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    private KartenDeck cardDeck = new KartenDeck();
    private ArrayList<Card> cardOnTable = new ArrayList<Card>();

    private int playingPlayerIndex = 0;

    private Boolean gameEnded = false;
    private ArrayList<Integer> indexOfLoser = new ArrayList<Integer>();

    public Game(int numberOfPlayers) {
        if (numberOfPlayers > 0 && numberOfPlayers <= 9) {

        for(int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player("Player" + (i + 1), this));
        }
        System.out.println("Game created");

        distributeCards();

        selectFirstPlayerToStart();
        } else {
            System.out.println("Wrong numbers for player");
        }
    }

    public Player getPlayingPlayer() {
        return players.get(playingPlayerIndex);
    }

    public ArrayList<Card> getCardOnTable() {
        return cardOnTable;
    }

    public void setCardOnTable(ArrayList<Card> cardOnTable) {
        this.cardOnTable = cardOnTable;
    }

    public Boolean getGameEnded() {
        return gameEnded;
    }

    private void distributeCards() {
        for(Player player: players) {
            player.setCardInHand(cardDeck.getThreeCardsFromCardDeck());
        }

        this.cardOnTable = cardDeck.getThreeCardsFromCardDeck();

        System.out.println("Cards distributed \n");
    }

    private void selectFirstPlayerToStart() {
        Random rand = new Random();
        playingPlayerIndex = rand.nextInt(players.size());

        System.out.println("First player is: " + players.get(playingPlayerIndex).getPlayerName());
        printHandandTableCards();
    }

    public void endGame() {
        if(!gameEnded) {
            //End Game
            gameEnded = true;
            //Set who lost the game
            int lowestPoints = players.get(0).pointsCardInHands();

            for (Player player : players) {
                int pointsOfPlayer = player.pointsCardInHands();

                if (pointsOfPlayer < lowestPoints) {
                    lowestPoints = pointsOfPlayer;
                }
            }

            for (int i = 0; i < players.size(); i++) {
                int pointsOfPlayer = players.get(i).pointsCardInHands();

                if (lowestPoints == pointsOfPlayer) {
                    this.indexOfLoser.add(i);
                }
            }

            System.out.println("Game is over");
            players.forEach(player -> System.out.println(player.pointsAndNameToString()));
            System.out.println("Player(s) Lost: " + indexOfLoser.stream().map(index -> players.get(index).getPlayerName()).collect(Collectors.toList()));
        }
    }

    public int pointsCardOnTable() {
        CardHandler cardHandler = new CardHandler();
        return cardHandler.pointsInCards(cardOnTable);
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

    public void printHandandTableCards() {
        System.out.println("Karten in Hand: \n" + players.get(playingPlayerIndex).cardsInHandToString());
        System.out.println("Punktzahl: \n" + players.get(playingPlayerIndex).pointsCardInHands() + "\n");
        System.out.println("Karten on Table: \n" + this.cardsOnTableToString() + "\n");
        System.out.println("__________________________ \n");
    }
}
