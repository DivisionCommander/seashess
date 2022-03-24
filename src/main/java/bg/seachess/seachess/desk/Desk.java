package bg.seachess.seachess.desk;

import java.util.Collection;

import bg.seachess.seachess.communications.Output;

public interface Desk {

    void resetDesk();

    void showDesk(Output output);
    
    int getSize();

    boolean isFieldFree(Position position);

    boolean victoryCondition();

    boolean isValidPosition(Position position);

    char getField(Position position);

    void occupyPosition(Position position, char mark);

    Collection<Position> victoryPositions();

}
