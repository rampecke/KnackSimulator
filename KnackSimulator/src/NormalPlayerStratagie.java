import java.net.StandardSocketOptions;
import java.util.Random;

public class NormalPlayerStratagie implements Strategy {
    private Game game;
    private Player player;

    public NormalPlayerStratagie(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void playMove() {
        //If game is AllReady over
        if(game.getGameEnded()) {
            return;
        }

        //Player will knock based on points in hand(if between 0-10 points with 0,0001%, if between 11-15 with 0,01%, if betweem 16-20  1,0%, if between 21-22 5,0%, between 23-25 50%, if between 26-27 80%, if bigger than 95%)
        Random random = new Random();
        int pointsInHand = player.pointsCardInHands();

        double knockProbability = 0.0;

        if (pointsInHand >= 0 && pointsInHand <= 10) {
            knockProbability = 0.0001;
        } else if (pointsInHand >= 11 && pointsInHand <= 15) {
            knockProbability = 0.01;
        } else if (pointsInHand >= 16 && pointsInHand <= 20) {
            knockProbability = 1.0;
        } else if (pointsInHand >= 21 && pointsInHand <= 22) {
            knockProbability = 5.0;
        } else if (pointsInHand >= 23 && pointsInHand <= 25) {
            knockProbability = 50.0;
        } else if (pointsInHand >= 26 && pointsInHand <= 27) {
            knockProbability = 80.0;
        } else if (pointsInHand > 27) {
            knockProbability = 95.0;
        }

        if (random.nextDouble() < knockProbability / 100.0) {
            player.playerKnocked();
        }

        //Play best Move
        playIdealMove();
    }

    public void playIdealMove() {
        CardHandler cardHandler = new CardHandler();

        int cardPointsPass = player.pointsCardInHands();
        int cardPointsSwapAll = game.pointsCardOnTable();
        SwapTuple cardPointsBestSwap = cardHandler.findBestSwap(player.getCardInHand(), game.getCardOnTable());

        System.out.println("SwapBest: " + cardPointsBestSwap.pointsOnHandAfterSwap + " PassBest: " + cardPointsPass + " SwapAllBest: " + cardPointsSwapAll);
        if(cardPointsBestSwap.pointsOnHandAfterSwap > cardPointsPass && cardPointsBestSwap.pointsOnHandAfterSwap > cardPointsSwapAll) {
            //System.out.println("Swap was called [" + cardPointsBestSwap.indexHandCard + ", " + cardPointsBestSwap.indexTableCard + "]");
            player.swapOneCard(cardPointsBestSwap.indexHandCard, cardPointsBestSwap.indexTableCard);
        } else if (cardPointsSwapAll > cardPointsPass) {
            //System.out.println("SwapAll");
            player.swapHandWithTableCards();
        } else {
            //System.out.println("Pass");
            player.pass();
        }
    }
}
