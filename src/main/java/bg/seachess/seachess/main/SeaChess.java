package bg.seachess.seachess.main;

import java.util.Scanner;

public class SeaChess {

  private  static boolean actOfAI(Desk desk, char mark) {
        while (true) {
            byte positionX = (byte) (Math.random() * 3);
            byte positionY = (byte) (Math.random() * 3);
            if (desk.isValidPosition(positionX, positionY)) {
                return desk.checkVictory(mark);
            }
        }
    }

    static boolean playerAct(Desk desk, char mark) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Select coordinates: ");
            byte positionX = sc.nextByte();
            byte positionY = sc.nextByte();
            positionX--;
            positionY--;
            if (!desk.isValidPosition(positionX, positionY)) {
                System.out.println("Invalid coordinates! Please, enter correct ones!");
                continue;
            }
            return desk.checkVictory(mark);
        }
    }

//    static boolean advancedAI(Desk desk, char mark, int round) {
//        char free = ' ';
//        if ((round <= 2) || (round >= 9)) {
//            return actOfAI(desk, mark);
//        }
//        for (byte index = 0; index < desk.length; index++) {
//            if (desk[index][0] != free) {
//                if ((desk[index][0] == desk[index][1]) && (desk[index][2] == free)) {
//                    desk[index][2] = mark;
//                    return victory(desk, mark);
//                }
//                if ((desk[index][0] == desk[index][2]) && (desk[index][1] == free)) {
//                    desk[index][1] = mark;
//                    return victory(desk, mark);
//                }
//            }
//            if (desk[index][0] == free) {
//                if ((desk[index][1] == desk[index][2]) && (desk[index][1] != free)) {
//                    desk[index][0] = mark;
//                    return victory(desk, mark);
//                }
//            }
//            if (desk[index][0] != free) {
//                if ((desk[0][index] == desk[1][index]) && (desk[2][index] == free)) {
//                    desk[2][index] = mark;
//                    return victory(desk, mark);
//                }
//                if ((desk[0][index] == desk[2][index]) && (desk[1][index] == free)) {
//                    desk[1][index] = mark;
//                    return victory(desk, mark);
//                }
//            }
//            if (desk[0][index] == free) {
//                if ((desk[1][index] == desk[2][index]) && (desk[1][index] != free)) {
//                    desk[0][index] = mark;
//                    return victory(desk, mark);
//                }
//            }
//
//        }
//        if ((desk[0][0] == desk[1][1]) && (desk[2][2]) == free) {
//            desk[2][2] = mark;
//            return victory(desk, mark);
//        }
//        return actOfAI(desk, mark);
//    }

    static boolean newGame() {
        Scanner sc = new Scanner(System.in);
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
            // System.out.println("Select difficulty:\n0. Player versus Player\t1.Standard
            // AI\t2.Easy AI")
            System.out.println("Enter 2 for game versus AI. Enter anything else for game versus Player");
            char difficulty = sc.next().charAt(0);

            desk.printDesk(System.out);

            boolean vsAI = (difficulty == '2') ? true : false;

            for (int round = 1; round < 10; round++) {
                if (round % 2 != 0) {
                    System.out.println("Player 1's turn!");
                    playerOneWin = playerAct(desk, pl1Mark);
                } else {
                    System.out.println("Player 2's turn!");
                    if (vsAI) {
                        playerTwoWin = actOfAI(desk, pl2Mark);

                    } else {
                        playerTwoWin = playerAct(desk, pl2Mark);
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
            boolean aNewGame = newGame();
            if (!(aNewGame)) {
                break;
            }
        } while (true);

        System.out.println(
                "\nTotal: " + countGames + " games." + "\nPlayer One wins: " + winsP1 + "\nPlayer Two wins: " + winsP2);

    }

}
