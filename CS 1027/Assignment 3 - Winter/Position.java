/**
 * This class holds the information for the position of a grid square in the game. 
* @author Aasminpreet Singh Kainth
 *
 */
public class Position {
	
	/**
	 * Instance variables hold the row and column number of the square.
	 */
	private int positionRow;
	private int positionColumn; 
	
	/**
	 * This constructor sets the initial values for the row and column.
	 * @param row Row number of the square.
	 * @param col Column number of the square.
	 */
	public Position(int row, int col){
		
		this.positionRow = row;
		this.positionColumn = col; 
		
	}
	
	/**
	 * This method returns the row number of the square.
	 * @return Row number.
	 */
	public int getRow() {
		
		return this.positionRow; 
		
	}
	
	/**
	 * This method returns the column number of the square.
	 * @return Column number.
	 */
	public int getCol() {
		
		return this.positionColumn;
		
	}
	
	/**
	 * This method is used to set the row number to a new value.
	 * @param newRow The new row number. 
	 */
	public void setRow(int newRow) {
		
		this.positionRow = newRow;
		
	}
	
	/**
	 * This method is used to set the column number to a new value.
	 * @param newCol The new column number.
	 */
	public void setCol(int newCol) {
		
		this.positionColumn = newCol; 
		
	}
	
	/**
	 * This method checks to see if the current Position object has the same row and column number 
	 * as the other Position object.
	 * @param otherPosition The other Position object to compare the current Position object to.
	 * @return true if the two objects share the same values, false if otherwise.
	 */
	public boolean equals(Position otherPosition) {
		
		// Compare the row and column numbers of the current Position with the other Position. They must both be equal to return 
		// 'true'. 
		if (this.positionRow == otherPosition.getRow() && this.positionColumn == otherPosition.getCol()) {
			
			return true;
			
		}
		
		return false; 
	}
}
