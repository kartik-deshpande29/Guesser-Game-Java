package GuesserGame;

import java.util.*;

class Guesser {
    private int guesserNumber;

    public int GuesserNum() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Guesser, guess a number (1-100): ");
        while (true) {
            try {
                guesserNumber = Integer.parseInt(sc.nextLine());
                if (guesserNumber < 1 || guesserNumber > 100) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please guess a number between 1 and 100: ");
            }
        }
        return guesserNumber;
    }
}

class Player {
    private int playerNumber;

    public int PlayerNum(int playerId) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Player " + playerId + ", guess a number (1-100): ");
        while (true) {
            try {
                playerNumber = Integer.parseInt(sc.nextLine());
                if (playerNumber < 1 || playerNumber > 100) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please guess a number between 1 and 100: ");
            }
        }
        return playerNumber;
    }
}

class Umpire {
    private int guesserNum;
    private int[] playerNums;
    private int[] scores;
    private int numPlayers;

    public Umpire(int numPlayers) {
        this.numPlayers = numPlayers;
        this.playerNums = new int[numPlayers];
        this.scores = new int[numPlayers];
    }

    public void collectFromGuesser() {
        Guesser gu = new Guesser();
        guesserNum = gu.GuesserNum();
    }

    public void collectFromPlayers() {
        Player p = new Player();
        for (int i = 0; i < numPlayers; i++) {
            playerNums[i] = p.PlayerNum(i + 1);
        }
    }

    public void compare() {
        boolean winnerFound = false;
        for (int i = 0; i < numPlayers; i++) {
            if (guesserNum == playerNums[i]) {
                System.out.println("Player " + (i + 1) + " won this round!!!");
                scores[i]++;
                winnerFound = true;
            }
        }
        if (!winnerFound) {
            System.out.println("No player guessed the number correctly!!!");
        }
    }

    public void displayScores() {
        System.out.println("Current Scores:");
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + ": " + scores[i]);
        }
    }

    public boolean checkForWinner(int winningScore) {
        for (int i = 0; i < numPlayers; i++) {
            if (scores[i] >= winningScore) {
                System.out.println("Player " + (i + 1) + " wins the game with " + scores[i] + " points!!!");
                return true;
            }
        }
        return false;
    }
}

public class MyGuesserGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------- Guesser Game ---------------");
        System.out.print("Enter the number of players: ");
        int numPlayers = sc.nextInt();
        System.out.print("Enter the winning score: ");
        int winningScore = sc.nextInt();
        sc.nextLine();  // consume newline

        Umpire ump = new Umpire(numPlayers);

        while (true) {
            ump.collectFromGuesser();
            ump.collectFromPlayers();
            ump.compare();
            ump.displayScores();
            if (ump.checkForWinner(winningScore)) {
                break;
            }
            System.out.print("Play another round? (yes/no): ");
            String response = sc.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        }

        System.out.println("--------------- Game Over ---------------");
    }
}
