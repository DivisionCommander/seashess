package bg.seachess.seachess.communications;

import java.util.Scanner;

import bg.seachess.seachess.desk.Position;

public class ScannerInput implements Input {

    private final Scanner scanner;

    public ScannerInput(Scanner scannerArg) {
	this.scanner = scannerArg;
    }
    
    @Override
    public Position getNextPosition()
    {
	System.out.print("Select coordinates: ");
	byte positionX = scanner.nextByte();
	byte positionY = scanner.nextByte();
	positionX--;
	positionY--;
	return new Position(positionX, positionY);
    }
}
