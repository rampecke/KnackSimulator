public class Main {
    public static void main(String[] args) {
        Game game = new Game(2);

        while (!game.getGameEnded()) {
            game.getPlayingPlayer().play();
        }
    }
}