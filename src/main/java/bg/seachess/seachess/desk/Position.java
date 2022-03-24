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
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + positionX;
	result = prime * result + positionY;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Position other = (Position) obj;
	if (positionX != other.positionX)
	    return false;
	if (positionY != other.positionY)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "[X="+positionX+";Y="+positionY+"]";
    }
}
