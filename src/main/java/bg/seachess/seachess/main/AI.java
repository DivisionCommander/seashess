package bg.seachess.seachess.main;

public class AI {

    private boolean simpleAI(Desk desk, char mark) {
        while (true) {
            byte positionX = (byte) (Math.random() * 3);
            byte positionY = (byte) (Math.random() * 3);
            if (desk.isPositionFree(positionX, positionY)) {
                desk.occupy(positionX, positionY, mark);
                return desk.checkVictory(mark);
            }
        }
    }

    public boolean actOfAI(Desk desk, char mark, int round) {
        if ((round <= 2) || (round >= 9)) {
            return simpleAI(desk, mark);
        }
        for (byte index = 0; index < 3; index++) {
            if (!desk.isPositionFree(index, 0)) {
                if (desk.isPair(index, 0, index, 1) && desk.isPositionFree(index, 2)) {
                    desk.occupy(index, 2, mark);
                    return desk.checkVictory(mark);
                }
                if (desk.isPair(index, 0, index, 2) && desk.isPositionFree(index, 1)) {
                    desk.occupy(index, 1, mark);
                    return desk.checkVictory(mark);
                }

            }
            if (desk.isPositionFree(index, 0)) {
                if (desk.isPair(index, 1, index, 2) && desk.isPositionFree(index, 1)) {
                    desk.occupy(index, 1, mark);
                    return desk.checkVictory(mark);
                }
                if (desk.isPair(index, 0, index, 2) && desk.isPositionFree(index, 1)) {
                    desk.occupy(index, 1, mark);
                    return desk.checkVictory(mark);
                }
            }
            if (!desk.isPositionFree(index, 0)) {
                if(desk.isPair(0, index, 1, index) && desk.isPositionFree(2, index))
                {
                    desk.occupy(2, round, mark);
                    return desk.checkVictory(mark   );
                }
                if(desk.isPair(0, index, 2, index) && (desk.isPositionFree(1, index)))
                {
                    desk.occupy(1, index, mark);
                    return desk.checkVictory(mark);
                }
                
            }
            if(desk.isPositionFree(0, index))
            {
                if(desk.isPair(1, index, 2, index)&& desk.isPositionFree(1, index))
                {
                    desk.occupy(0, index, mark);
                    return desk.checkVictory(mark);
                }
            }

        }
        if (desk.isPair(0, 0, 1, 1) && desk.isPositionFree(2, 2)) {
            desk.occupy(2, 2, mark);
            return desk.checkVictory(mark);
        }
        return simpleAI(desk, mark);
    }

}
