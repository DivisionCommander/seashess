package bg.seachess.seachess.participants;

import java.util.Scanner;

import bg.seachess.seachess.communications.Input;
import bg.seachess.seachess.communications.ScannerInput;
import bg.seachess.seachess.desk.Desk;
import bg.seachess.seachess.desk.Position;

public class PlayerParticipant implements Participant {
    private Input input;
    private char mark;

    /**
     * @deprecated use {@link PlayerParticipant#PlayerParticipant(Input)}
     */
    @Deprecated(since = "0.0.2", forRemoval = true)
    public PlayerParticipant(Scanner scanner) {
	this(PLAYER_ONE_MARK, scanner);
    }

    /**
     * @deprecated use {@link PlayerParticipant#PlayerParticipant(Input, char)}
     */
    @Deprecated(since = "0.0.2", forRemoval = true)
    public PlayerParticipant(char mark, Scanner scanner) {
	this(new ScannerInput(scanner), mark);
    }

    /**
     * @since 0.0.2
     */
    public PlayerParticipant(Input inputArg) {
	this(inputArg, PLAYER_ONE_MARK);
    }

    /**
     * @since 0.0.2
     */
    public PlayerParticipant(Input inputArg, char mark) {
	this.input = inputArg;
	this.mark = mark;
    }

    @Override
    public boolean act(Desk desk, int round) {
	try {
	    Position next;
	    while (true) {
		next = input.getNextPosition();
		if (desk.isValidPosition(next) && desk.isFieldFree(next)) {
		    desk.occupyPosition(next, mark);
		    return desk.victoryCondition();
		}
		System.out.println("Invalid coordinates! Please, enter correct ones!");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw e;
	}
    }

    @Override
    public String defeat() {
	return "Player has been defeated!";
    }
}
