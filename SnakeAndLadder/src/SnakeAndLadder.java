import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SnakeAndLadder {

    static final int WIN_POINT = 100;
    static Map<Integer, Integer> snake = new HashMap<>();
    static Map<Integer, Integer> ladder = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Setup snakes
        snake.put(99, 54);
        snake.put(70, 55);
        snake.put(52, 42);
        snake.put(25, 2);
        snake.put(95, 72);

        // Setup ladders
        ladder.put(6, 25);
        ladder.put(11, 40);
        ladder.put(60, 85);
        ladder.put(46, 90);
        ladder.put(17, 69);

        System.out.println("Welcome to the Snake and Ladder game!");

        do {
            playGame();
            System.out.println("Do you want to play again? (yes/no)");
        } while (sc.nextLine().equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing!");
        sc.close();
    }

    public static void playGame() {
        System.out.print("Enter Player 1 name: ");
        String player1Name = sc.nextLine().trim();

        System.out.print("Enter Player 2 name: ");
        String player2Name = sc.nextLine().trim();

        int player1Pos = 0, player2Pos = 0;
        boolean turn = true; // true = Player 1, false = Player 2

        while (player1Pos < WIN_POINT && player2Pos < WIN_POINT) {
            System.out.println("----------------------------------");
            System.out.println((turn ? player1Name : player2Name) + "'s turn, press Enter to roll the dice...");
            sc.nextLine();

            int dice = rollDice();
            int oldPosition = turn ? player1Pos : player2Pos;
            int newPosition = oldPosition + dice;

            if (newPosition > WIN_POINT) {
                System.out.println("Roll exceeds 100, you stay at position " + oldPosition);
                newPosition = oldPosition;
            } else {
                if (snake.containsKey(newPosition)) {
                    System.out.println("Oops! Bitten by a snake!");
                    newPosition = snake.get(newPosition);
                } else if (ladder.containsKey(newPosition)) {
                    System.out.println("Yay! Climbed a ladder!");
                    newPosition = ladder.get(newPosition);
                }
            }

            if (turn) {
                player1Pos = newPosition;
                System.out.println(player1Name + " is now at position: " + player1Pos);
                if (player1Pos == WIN_POINT) {
                    System.out.println(" Congratulations " + player1Name + ", you won!");
                    break;
                }
            } else {
                player2Pos = newPosition;
                System.out.println(player2Name + " is now at position: " + player2Pos);
                if (player2Pos == WIN_POINT) {
                    System.out.println(" Congratulations " + player2Name + ", you won!");
                    break;
                }
            }

            System.out.println("Current Positions: " + player1Name + " - " + player1Pos + ", " + player2Name + " - " + player2Pos);
            turn = !turn;
        }
    }

    public static int rollDice() {
        int dice = 1 + (int)(Math.random() * 6);
        System.out.println("Dice rolled: " + dice);
        return dice;
    }
}
