package bg.seachess.seachess.main;

import java.io.PrintStream;

import bg.seachess.seachess.desk.DeskImpl;
import bg.seachess.seachess.participants.Participant;

public class Game {

    private Participant playerOne;
    private Participant playerTwo;
    private DeskImpl        desk;
    private PrintStream print;

    public Game(Participant playerOne, Participant playerTwo, DeskImpl desk, PrintStream print) {
        super();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.desk = desk;
        this.print = print;
    }

    public ResultEnum game() {
        for (int round = 1; round < 10; round++) {

            Participant currentPlayer = round % 2 != 0 ? playerOne : playerTwo;

            boolean victory = currentPlayer.act(desk, round);
            desk.printDesk(print);
            if (victory) {
                return currentPlayer == playerOne ? ResultEnum.PLAYER_ONE_WIN : ResultEnum.PLAYER_TWO_WIN;
            }
        }

        return ResultEnum.DRAW;
    }
}
