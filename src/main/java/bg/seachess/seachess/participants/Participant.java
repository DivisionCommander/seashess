package bg.seachess.seachess.participants;

import bg.seachess.seachess.main.Desk;

public interface Participant {
    public static final char PLAYER_ONE_MARK = 'X';
    public static final char PLAYER_TWO_MARK = 'O';

    public boolean act(Desk desk, int round);

    public String defeat();

}
