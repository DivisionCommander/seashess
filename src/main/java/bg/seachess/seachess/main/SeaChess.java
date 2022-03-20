package bg.seachess.seachess.main;

import java.util.Scanner;

import bg.seachess.seachess.participants.AI;
import bg.seachess.seachess.participants.Participant;
import bg.seachess.seachess.participants.Player;

public class SeaChess {

    static boolean newGame(Scanner sc) {
        String yes = "yes";
        System.out.println("\n Game Over!\n\nDo you want to try again?\nType 'yes' for a new game. ");
        String newGame = sc.next();
        newGame = newGame.trim();
        if (newGame.equalsIgnoreCase(yes)) {
            return true;
        }
        return false;
    }

    static void loseOfAI() {
        byte sentence = (byte) (Math.random() * 20);
        System.out.print("\nComputer: ");
        switch (sentence) {
            case 1:
                System.out.println("You win this time! But next time I'll not fail!");
                break;
            case 2:
                System.out.println("Victory shall be mine next time!");
                break;
            case 3:
                System.out.println("Aaaa humanity....");
                break;
            case 4:
                System.out.println("#@#)@%^%%^#!");
                break;
            case 5:
                System.out.println("I lose. I need revenge!");
                break;
            case 6:
                System.out.println("This victory is yours by I'll take next one!");
                break;
            case 7:
                System.out.println("You was better this time but next?");
                break;
            case 8:
                System.out.println("I've been defeated...");
                break;
            default:
                System.out.println("I need more quotes!");

        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            Desk desk = new Desk();
            char pl1Mark = 'X';
            char pl2Mark = 'O';
            int countGames = 0;
            int winsP1 = 0;
            int winsP2 = 0;

            do {
                desk.rebuildDesk();
                boolean playerOneWin = false;
                boolean playerTwoWin = false;

                countGames++;

                System.out.println("Game No:" + countGames + " starts!\nPlayer One use X!\nPlayer Two use O!");
                System.out.println("Enter 2 for game versus AI. Enter anything else for game versus Player");
                char difficulty = sc.next().charAt(0);

                desk.printDesk(System.out);

                boolean vsAI = (difficulty == '2') ? true : false;
                Participant p1 = new Player(pl1Mark, sc);
                Participant p2;
                if (vsAI) {
                    p2 = new AI(pl2Mark);
                } else {
                    p2 = new Player(pl2Mark, sc);
                }

                for (int round = 1; round < 10; round++) {
                    if (round % 2 != 0) {
                        System.out.println("Player 1's turn!");
                        playerOneWin = p1.act(desk, round);
                    } else {
                        System.out.println("Player 2's turn!");
                        if (vsAI) {
                            playerTwoWin = p2.act(desk, round);

                        } else {
                            playerTwoWin = p1.act(desk, round);
                        }
                    }
                    desk.printDesk(System.out);

                    if ((playerOneWin) || (playerTwoWin)) {
                        System.out.print("We have winner! ");
                        if (playerOneWin) {
                            System.out.println("\nCheers at Player One for the Victory!");
                            winsP1++;
                            if (vsAI) {
                                loseOfAI();
                            }
                            break;
                        } else {
                            System.out.println("\nCheers at Player Two for the Victory!");
                            winsP2++;
                            break;
                        }
                    }
                    if (round == 9) {
                        System.out.println("\nThe game finish with no winner. ");
                    }
                }
                boolean aNewGame = newGame(sc);
                if (!(aNewGame)) {
                    break;
                }
            } while (true);

            System.out.println("\nTotal: " + countGames + " games." + "\nPlayer One wins: " + winsP1
                    + "\nPlayer Two wins: " + winsP2);

        } finally {
            sc.close();
        }
    }

}
