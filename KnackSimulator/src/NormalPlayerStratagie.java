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

        //Player will knock
        Random random = new Random();

        if(random.nextInt(1, 100) < 2) {
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
            System.out.println("Swap was called [" + cardPointsBestSwap.indexHandCard + ", " + cardPointsBestSwap.indexTableCard + "]");
            player.swapOneCard(cardPointsBestSwap.indexHandCard, cardPointsBestSwap.indexTableCard);
        } else if (cardPointsSwapAll > cardPointsPass) {
            System.out.println("SwapAll");
            player.swapHandWithTableCards();
        } else {
            System.out.println("Pass");
            player.pass();
        }
    }
}
