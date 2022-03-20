package bg.seachess.seachess.desk;

import java.io.PrintStream;

public class Desk {

    public static final char FILLER = ' ';

    private final int size;
    private final char[][] desk;

    public Desk() {
	this(3);
    }

    Desk(int sizeArg) {
	desk = new char[sizeArg][sizeArg];
	this.size = sizeArg;
	rebuildDesk();
    }

    public char getField(Position position) {
	validatePosition(position);
	return desk[position.getAxisX()][position.getAxisY()];
    }

    public boolean isFieldFree(Position position) {
	validatePosition(position);
	return desk[position.getAxisX()][position.getAxisY()] == FILLER;
    }

    public void occupyPosition(Position position, char mark) {
	validatePosition(position);
	if (desk[position.getAxisX()][position.getAxisY()] != FILLER) {
	    throw new IllegalArgumentException("Field is already occupied");
	}
	desk[position.getAxisX()][position.getAxisY()] = mark;
    }

    public void rebuildDesk() {
	for (int row = 0; row < desk.length; row++) {
	    for (int col = 0; col < desk[row].length; col++) {
		desk[row][col] = FILLER;
	    }
	}
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
     * @deprecated {@link Desk#isFieldFree(Position)}
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

    public boolean checkVictory(char mark) {
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
     * @deprecated {@link Desk#occupyPosition(Position, char)}
     */
    @Deprecated(forRemoval = true, since = "0.0.2")
    public void occupy(int positionX, int positionY, char mark) {
	desk[positionX][positionY] = mark;
    }

    /**
     * @deprecated {@link Desk#getField(Position)}
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

    public boolean isValidPosition(Position position) {
	try {
	    validatePosition(position);
	    return true;
	} catch (IllegalArgumentException e) {
	    return false;
	}
    }

    public int getSize() {
	return size;
    }
}
