package bg.seachess.seachess.desk;

public final class Position {

    private final int positionX;
    private final int positionY;
    
    public Position(int axisX,int axisY)
    {
	this.positionX = axisX;
	this.positionY = axisY;
    }

    public int getAxisX() {
	return positionX;
    }

    public int getAxisY() {
	return positionY;
    }

    @Override
    public String toString() {
	return "[X="+positionX+";Y="+positionY+"]";
    }
}
