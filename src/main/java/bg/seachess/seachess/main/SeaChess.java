package bg.seachess.seachess.main;

import java.util.Scanner;

import bg.seachess.seachess.communications.ScannerInput;
import bg.seachess.seachess.desk.DeskImpl;
import bg.seachess.seachess.participants.ComputerParticipant;
import bg.seachess.seachess.participants.Participant;
import bg.seachess.seachess.participants.PlayerParticipant;

public class SeaChess {

    private int gameCount = 1;
    private DeskImpl desk = new DeskImpl();
    private int winsP1;
    private int winsP2;
    private Participant playerOne;
    private Participant playerTwo;

    public void game() {
	try (Scanner scanner = new Scanner(System.in)) {
	    do {
		System.out.println("Game No:" + gameCount + " starts!\nPlayer One use X!\nPlayer Two use O!");
		System.out.println("Enter 2 for game versus AI. Enter anything else for game versus Player");

		playerOne = new PlayerParticipant(new ScannerInput(scanner));
		playerTwo = getSecondParticipant(scanner.next().charAt(0), scanner);
		desk.printDesk(System.out);

		Game game = new Game(playerOne, playerTwo, desk, System.out);

		ResultEnum result = game.game();

		if (result == ResultEnum.DRAW) {
		    System.out.println("\nThe game finish with no winner. ");
		} else {
		    if (result == ResultEnum.PLAYER_ONE_WIN) {
			System.out.println("\nCheers at Player One for the Victory!");
			winsP1++;
			System.out.println(playerTwo.defeat());
		    } else {
			System.out.println("\nCheers at Player Two for the Victory!");
			winsP2++;
		    }
		}
		boolean aNewGame = newGame(scanner);
		if (!(aNewGame)) {
		    break;
		}
		desk.resetDesk();
	    } while (true);

	    System.out.println("\nTotal: " + gameCount + " games." + "\nPlayer One wins: " + winsP1
		    + "\nPlayer Two wins: " + winsP2);
	}
    }

    private boolean newGame(Scanner sc) {
	String yes = "yes";
	System.out.println("\n Game Over!\n\nDo you want to try again?\nType 'yes' for a new game. ");
	String newGame = sc.next();
	newGame = newGame.trim();
	return newGame.equalsIgnoreCase(yes);
    }

    private Participant getSecondParticipant(char difficulty, Scanner scanner) {
	return difficulty == '2' ? new ComputerParticipant(Participant.PLAYER_TWO_MARK)
		: new PlayerParticipant(new ScannerInput(scanner), Participant.PLAYER_TWO_MARK);
    }
    
    public DeskImpl getDesk() {return desk;}

    public static void main(String[] args) {
	new SeaChess().game();
    }
}
