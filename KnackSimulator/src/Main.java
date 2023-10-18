public class Main {
    public static void main(String[] args) {
        Game game = new Game(2);

        game.getPlayingPlayer().swapOneCard(2,2);

        game.getPlayingPlayer().swapHandWithTableCards();

        game.getPlayingPlayer().playerKnocked();

        game.getPlayingPlayer().swapOneCard(2,2);

        game.getPlayingPlayer().swapHandWithTableCards();
    }
}