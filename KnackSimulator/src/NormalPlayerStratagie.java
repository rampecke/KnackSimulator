public class NormalPlayerStratagie implements Strategy {
    private Game game;
    private Player player;

    public NormalPlayerStratagie(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void playMove() {
        CardHandler cardHandler = new CardHandler();

       SwapTuple cardPointsBestSwap = cardHandler.findBestSwap(player.getCardInHand(), game.getCardOnTable());
       int cardPointsPass = cardHandler.pointsInCards(player.getCardInHand());
       int cardPointsSwapAll = cardHandler.pointsInCards(game.getCardOnTable());

       if(cardPointsBestSwap.pointsOnHandAfterSwap > cardPointsPass && cardPointsBestSwap.pointsOnHandAfterSwap > cardPointsSwapAll) {
           player.swapOneCard(cardPointsBestSwap.indexHandCard, cardPointsBestSwap.indexTableCard);
       } else if (cardPointsSwapAll > cardPointsPass) {
           player.swapHandWithTableCards();
       } else {
           player.pass();
       }
    }
}
