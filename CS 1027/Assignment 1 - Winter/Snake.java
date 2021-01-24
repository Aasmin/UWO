public class Snake {
	private Position[] snakeBody;
	private final int indexHead = 0;
	private int length;
	private int initialSize = 5;
	
	public Snake(int row, int col) {
		Position pos = new Position(row,col);
		snakeBody = new Position[initialSize];
		snakeBody[0] = pos;
		length = 1;
	}

	private void increaseArraySize() {
		Position[] newArray = new Position[snakeBody.length*2];
		for (int i = 0; i < snakeBody.length; ++i)
			newArray[i] = snakeBody[i];
		snakeBody = newArray;
	}
	
	public void grow(String direction) {
		if (length == snakeBody.length) increaseArraySize();
	    Position newHead = newHeadPosition(direction);
	    for (int i = length-1; i >= 0; --i)
		snakeBody[i+1] = snakeBody[i];
	    snakeBody[0] = newHead;
	    ++length;
	}	
	
	public void shrink() {
		--length;
	}
	
	private Position newHeadPosition(String direction) {
		int row = snakeBody[indexHead].getRow();
		int col = snakeBody[indexHead].getCol();
		if (direction.equals("right")) ++col;
		else if (direction.equals("left")) --col;
		else if (direction.equals("up")) --row;
		else ++row;	
		return new Position(row,col);
	}
	
	public void moveSnake(String direction) {
		Position newPos = newHeadPosition(direction);
		if (length >= 2)
			for (int i = length-2; i >= 0; --i)
				snakeBody[i+1] = snakeBody[i];
		snakeBody[0] = newPos;
	}
	
	public boolean snakePosition (Position pos) {
		for (int i = 0; i < length; ++i) 
			if (snakeBody[i].equals(pos)) return true;
		return false;
	}

	public Position getPosition(int index) {
		if (index < 0 || index >= length) return null;
		else return snakeBody[index];
	}

    public int getLength() {
	    return length;
	}
	
}