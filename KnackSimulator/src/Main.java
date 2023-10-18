public class Main {
    public static void main(String[] args) {
        Game game = new Game(9);

        game.selectFirstPlayerToStart();

        game.changeOneCard(2,2);

        game.nextPlayer();

        game.swapHandWithTableCards();

        game.nextPlayer();

        game.playerKnocked();
    }
}