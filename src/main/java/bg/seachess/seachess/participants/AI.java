package bg.seachess.seachess.participants;

import bg.seachess.seachess.main.Desk;

public class AI implements Participant {
    private char mark;

    public AI(char mark) {
        this.mark = mark;
    }

    private boolean randomMove(Desk desk, char mark) {
        while (true) {
            byte positionX = (byte) (Math.random() * 3);
            byte positionY = (byte) (Math.random() * 3);
            if (desk.isPositionFree(positionX, positionY)) {
                desk.occupy(positionX, positionY, mark);
                return desk.checkVictory(mark);
            }
        }
    }

    @Override
    public boolean act(Desk desk, int round) {
        if ((round <= 2) || (round >= 9)) {
            return randomMove(desk, mark);
        }
        char free = ' ';
        if ((round <= 2) || (round >= 9)) {
            return randomMove(desk, mark);
        }
        for (byte index = 0; index < 3; index++) {
            if (desk.get(index, 0) != free) {
                if ((desk.get(index, 0) == desk.get(index, 1)) && (desk.get(index, 2) == free)) {
                    desk.occupy(index, 2, mark);
                    return desk.checkVictory(mark);
                }
                if ((desk.get(index, 0) == desk.get(index, 2)) && (desk.get(index, 1) == free)) {
                    desk.occupy(index, 1, mark);
                    return desk.checkVictory(mark);
                }
            }
            if (desk.get(index, 0) == free) {
                if ((desk.get(index, 1) == desk.get(index, 2)) && (desk.get(index, 1) != free)) {
                    desk.occupy(index, 0, mark);
                    return desk.checkVictory(mark);
                }
            }
            if (desk.get(index, 0) != free) {
                if ((desk.get(0, index) == desk.get(1, index)) && (desk.get(2, index) == free)) {
                    desk.occupy(2, index, mark);
                    return desk.checkVictory(mark);
                }
                if ((desk.get(0, index) == desk.get(2, index)) && (desk.get(1, index) == free)) {
                    desk.occupy(1, index, mark);
                    return desk.checkVictory(mark);
                }
            }
            if (desk.get(0, index) == free) {
                if ((desk.get(1, index) == desk.get(2, index)) && (desk.get(1, index) != free)) {
                    desk.occupy(0, index, mark);
                    return desk.checkVictory(mark);
                }
            }}
            return randomMove(desk, mark);
    }

}
