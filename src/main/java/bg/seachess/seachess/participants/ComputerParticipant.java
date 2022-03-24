package bg.seachess.seachess.participants;

import java.util.HashMap;
import java.util.Map;

import bg.seachess.seachess.desk.Desk;
import bg.seachess.seachess.desk.Position;

public class ComputerParticipant implements Participant {
    private static final Map<Integer, String> SENTENCES = populateSentences();
    private final char mark;

    public ComputerParticipant(char mark) {
	this.mark = mark;
    }

    private boolean randomMove(Desk desk, char mark) {
	while (true) {
	    byte positionX = (byte) (Math.random() * desk.getSize());
	    byte positionY = (byte) (Math.random() * desk.getSize());
	    Position position = new Position(positionX, positionY);
	    if (desk.isValidPosition(position) && desk.isFieldFree(position)) {
		desk.occupyPosition(position, mark);
		return desk.victoryCondition();
	    }
	}
    }

    @Override
    public boolean act(Desk desk, int round) {
	char free = ' ';
	if ((round <= 2) || (round >= desk.getSize()*desk.getSize())) {
	    return randomMove(desk, mark);
	}

	for (byte index = 0; index < desk.getSize(); index++) {
	    if (desk.getField(new Position(index, 0)) != free) {
		if (desk.getField(new Position(index, 0)) == desk.getField(new Position(index, 1)) && (desk.getField(new Position(index, 2)) == free)) {
		    desk.occupyPosition(new Position(index, 2), mark);
		    return desk.victoryCondition();
		}
		if (desk.getField(new Position(index, 0)) == desk.getField(new Position(index, 2)) && (desk.getField(new Position(index, 1)) == free)) {
		    desk.occupyPosition(new Position(index, 1), mark);
		    return desk.victoryCondition();
		}
	    }
	    if (desk.getField(new Position(index, 0)) == free) {
		if (desk.getField(new Position(index, 1) )== desk.getField(new Position(index, 2)) && (desk.getField(new Position(index, 1) )!= free)) {
		    desk.occupyPosition(new Position(index, 0), mark);
		    return desk.victoryCondition();
		}
	    }
	    if (desk.getField(new Position(index, 0) )!= free) {
		if (desk.getField(new Position(0, index) )== desk.getField(new Position(1, index)) && (desk.getField(new Position(2, index)) == free)) {
		    desk.occupyPosition(new Position(2, index), mark);
		    return desk.victoryCondition();
		}
		if (desk.getField(new Position(0, index) )== desk.getField(new Position(2, index)) && (desk.getField(new Position(1, index) )== free)) {
		    desk.occupyPosition(new Position(1, index), mark);
		    return desk.victoryCondition();
		}
	    }
	    if (desk.getField(new Position(0, index)) == free) {
		if (desk.getField(new Position(1, index)) == desk.getField(new Position(2, index)) && (desk.getField(new Position(1, index) )!= free)) {
		    desk.occupyPosition(new Position(0, index), mark);
		    return desk.victoryCondition();
		}
	    }
	}
	return randomMove(desk, mark);
    }

    private static Map<Integer, String> populateSentences() {
	Map<Integer, String> sentences = new HashMap<>();
	sentences.put(1, "You win this time! But next time I'll not fail!");
	sentences.put(2, "Victory shall be mine next time!");
	sentences.put(3, "Aaaa humanity....");
	sentences.put(4, "#@#$@%^%%^#!");
	sentences.put(5, "I lose. I need revenge!");
	sentences.put(6, "This victory is yours by I'll take next one!");
	sentences.put(7, "You was better this time but next?");
	sentences.put(8, "I've been defeated...");
	sentences.put(9, "I need more quotes!");
	sentences.put(0, "In vino veritas");
	return sentences;
    }

    @Override
    public String defeat() {
	int sentence = (int) (Math.random() * SENTENCES.size());
	return SENTENCES.get(sentence);
    }
}
