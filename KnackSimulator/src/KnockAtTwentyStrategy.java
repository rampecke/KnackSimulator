public class KnockAtTwentyStrategy implements Strategy{
    private Game game;
    private Player player;

    public KnockAtTwentyStrategy(Game game, Player player) {
        this.game = game;
        this.player = player;
    }
    @Override
    public void playMove() {
        if (game.getPlayingPlayer().getPlayerKlopft()) {
            game.endGame();
        }
        //If game is AllReady over
        if(game.getGameEnded()) {
            return;
        }

        if(player.pointsCardInHands() >= 20) {
            if (!game.getSomeOneKnocked()) { //Only Knock if noone knocked
                player.playerKnocked();
            } else {
                NormalPlayerStratagie normalPlayerStratagie = new NormalPlayerStratagie(game, player);
                normalPlayerStratagie.playIdealMove();
            }
        } else {
            NormalPlayerStratagie normalPlayerStratagie = new NormalPlayerStratagie(game, player);
            normalPlayerStratagie.playIdealMove();
        }

        if(player.pointsCardInHands() >= 31) {
            game.endGame();
            return;
        }

        game.nextPlayer();
    }

    public void printString() {
        System.out.println(player.getPlayerName() + " had special Startagie");
    }
}
