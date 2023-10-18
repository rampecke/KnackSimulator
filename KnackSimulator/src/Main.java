import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of players: ");
        int numberOfPlayers = scanner.nextInt();

        if(numberOfPlayers <= 1 || numberOfPlayers > 9) {
            System.out.println("Wrong PlayerNumber");
            return;
        }

        Game game = new Game(numberOfPlayers);

        while (!game.getGameEnded()) {
            game.getPlayingPlayer().play();
        }
    }
}