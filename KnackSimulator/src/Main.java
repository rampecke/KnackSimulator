public class Main {
    public static void main(String[] args) {
        Game game = new Game(2);

        game.getPlayingPlayer().changeOneCard(2,2);

        game.getPlayingPlayer().swapHandWithTableCards();

        game.getPlayingPlayer().playerKnocked();

        game.getPlayingPlayer().changeOneCard(2,2);

        game.getPlayingPlayer().swapHandWithTableCards();
    }
}