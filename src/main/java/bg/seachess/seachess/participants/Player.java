package bg.seachess.seachess.participants;

import java.util.Scanner;

import bg.seachess.seachess.main.Desk;

public class Player implements Participant {
    private char mark;
    private Scanner sc;

    public Player(char mark, Scanner scanner) {
        this.mark = mark;
        this.sc = scanner;
    }

    @Override
    public boolean act(Desk desk, int round) {
        try {
            while (true) {
                System.out.print("Select coordinates: ");
                byte positionX = sc.nextByte();
                byte positionY = sc.nextByte();
                positionX--;
                positionY--;
                if (!desk.isPositionFree(positionX, positionY)) {
                    System.out.println("Invalid coordinates! Please, enter correct ones!");
                    continue;
                }
                desk.occupy(positionX, positionY, mark);
                return desk.checkVictory(mark);

            }
        }catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }
}
