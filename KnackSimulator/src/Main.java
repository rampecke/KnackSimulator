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

        int playerWithStrategie = 0;
        int playerWithOutStratagie = 1;

        for(int i = 0; i <= 10000; i++) {
            Game game = new Game(numberOfPlayers);

            while (!game.getGameEnded()) {
                game.getPlayingPlayer().play();
            }

            if (game.getIndexOfLoser().contains(0)) {
                playerWithStrategie++;
            }

            if (game.getIndexOfLoser().contains(1)) {
                playerWithOutStratagie++;
            }
        }

        System.out.println("Player with special Stratagie lost " + playerWithStrategie + " from 10000 times with " + numberOfPlayers + " Players");
        System.out.println("Player with special Stratagie lost " + playerWithOutStratagie + " from 10000 times with " + numberOfPlayers + " Players");
    }
}