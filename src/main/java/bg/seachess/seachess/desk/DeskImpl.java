package bg.seachess.seachess.desk;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import bg.seachess.seachess.communications.Output;

public class DeskImpl implements Desk {

    public static final char FILLER = ' ';

    private final int size;
    private final char[][] desk;

    public DeskImpl() {
	this(3);
    }

    DeskImpl(int sizeArg) {
	desk = new char[sizeArg][sizeArg];
	this.size = sizeArg;
	resetDesk();
    }

    @Override
    public char getField(Position position) {
	validatePosition(position);
	return desk[position.getAxisX()][position.getAxisY()];
    }

    @Override
    public boolean isFieldFree(Position position) {
	validatePosition(position);
	return desk[position.getAxisX()][position.getAxisY()] == FILLER;
    }

    @Override
    public void occupyPosition(Position position, char mark) {
	validatePosition(position);
	if (desk[position.getAxisX()][position.getAxisY()] != FILLER) {
	    throw new IllegalArgumentException("Field is already occupied");
	}
	desk[position.getAxisX()][position.getAxisY()] = mark;
    }

    @Override
    public void resetDesk() {
	for (int row = 0; row < desk.length; row++) {
	    for (int col = 0; col < desk[row].length; col++) {
		desk[row][col] = FILLER;
	    }
	}
    }

    @Override
    public void showDesk(Output output) {
	new UnsupportedOperationException();
    }

    public void printDesk(PrintStream ps) {
	for (int row = 0; row < ((desk.length * 2) + 1); row++) {

	    for (int col = 0; col < ((desk.length * 2) + 1); col++) {
		if (row % 2 == 0) {
		    ps.print("-");
		} else {
		    if (col % 2 == 0) {
			ps.print("|");
		    } else {
			ps.print(desk[row / 2][col / 2]);
		    }
		}
	    }
	    ps.println();
	}
    }

    /**
     * @deprecated {@link DeskImpl#isFieldFree(Position)}
     */
    @Deprecated(forRemoval = true, since = "0.0.2")
    public boolean isPositionFree(int positionX, int positionY) {
	if (positionX > desk.length || positionY > desk.length) {
	    return false;
	}
	if (positionX < 0 || positionY < 0) {
	    return false;
	}
	if (desk[positionX][positionY] != FILLER) {
	    return false;
	}
	return true;
    }

    @Override
    public boolean victoryCondition() {
	return !victoryPositions().isEmpty();
    }

    @Override
    public Collection<Position> victoryPositions() {

	int deskSize = desk.length - 1;
	char markMainDiagonal = desk[0][0];
	char markReverseDiagonal = desk[deskSize][0];

	boolean winMainDiagonal = markMainDiagonal != FILLER;
	boolean winReverseDiagonal = markReverseDiagonal != FILLER;

	List<Position> rowPosition = new ArrayList<>(size);
	List<Position> columnPosition = new ArrayList<>(size);
	List<Position> mainDiagonalPositions = new ArrayList<>(size);
	List<Position> reverseDiagonalPositons = new ArrayList<>(size);

	for (int row = 0; row < desk.length; row++) {

	    char markRow = desk[row][0];
	    char markColumn = desk[0][row];
	    boolean winRow = markRow != FILLER;
	    boolean winColumn = markColumn != FILLER;

	    for (int column = 0; column < desk[row].length; column++) {
		if (winRow) {
		    if (desk[row][column] != markRow) {
			winRow = false;
			rowPosition.clear();
		    } else {
			rowPosition.add(new Position(row, column));
		    }
		}
		if (winColumn) {
		    if (desk[column][row] != markColumn) {
			winColumn = false;
			columnPosition.clear();
		    } else {
			columnPosition.add(new Position(column, row));
		    }
		}
	    }
	    if (winRow) {
		return rowPosition;
	    }
	    if (winColumn) {
		return columnPosition;
	    }

	    if (winMainDiagonal)
		if (desk[row][row] != markMainDiagonal) {
		    winMainDiagonal = false;
		    mainDiagonalPositions.clear();
		} else {
		    mainDiagonalPositions.add(new Position(row, row));
		}
	    if (winReverseDiagonal) {
		if (desk[deskSize - row][row] != markReverseDiagonal) {
		    winReverseDiagonal = false;
		    reverseDiagonalPositons.clear();
		} else {
		    reverseDiagonalPositons.add(new Position(deskSize - row, row));
		}
	    }
	}
	if (winMainDiagonal) {
	    return mainDiagonalPositions;
	}
	if (winReverseDiagonal) {
	    return reverseDiagonalPositons;
	}
	return Collections.emptyList();
    }

    /**
     * @deprecated {@link DeskImpl#victoryCondition()}
     */
    @Deprecated(forRemoval = true, since = "0.0.3")
    public boolean checkForVictory(char mark) {
	for (byte row = 0; row < desk.length; row++) {
	    boolean rowWin = true;
	    boolean colWin = true;
	    boolean diag1 = true;
	    boolean diag2 = true;

	    for (byte col = 0; col < desk.length; col++) {
		if (desk[row][col] != mark) {
		    rowWin = false;
		}
		if (desk[col][row] != mark) {
		    colWin = false;
		}
		if (desk[col][col] != mark) {
		    diag1 = false;
		}
		if (desk[col][(desk.length - 1) - col] != mark) {
		    diag2 = false;
		}
	    }
	    if ((rowWin) || (colWin) || (diag1) || (diag2)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * @deprecated {@link DeskImpl#occupyPosition(Position, char)}
     */
    @Deprecated(forRemoval = true, since = "0.0.2")
    public void occupy(int positionX, int positionY, char mark) {
	desk[positionX][positionY] = mark;
    }

    /**
     * @deprecated {@link DeskImpl#getField(Position)}
     */
    @Deprecated(forRemoval = true, since = "0.0.2")
    public char get(int positionX, int positionY) {
	return desk[positionX][positionY];
    }

    private void validatePosition(Position position) {
	if (position == null) {
	    throw new IllegalArgumentException("Invalid Position!");
	}
	int axisX = position.getAxisX();
	int axisY = position.getAxisY();
	if (axisX < 0 || axisX >= desk.length || axisY < 0 || axisY >= desk[axisX].length) {
	    throw new IllegalArgumentException("Invalid Position!");
	}

    }

    @Override
    public boolean isValidPosition(Position position) {
	try {
	    validatePosition(position);
	    return true;
	} catch (IllegalArgumentException e) {
	    return false;
	}
    }

    @Override
    public int getSize() {
	return size;
    }
}
