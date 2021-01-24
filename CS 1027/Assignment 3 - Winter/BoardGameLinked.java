/**
 * This class contains the information and methods of the board of the game in which a snake moves around eating apples and avoiding
 * obstacles and/or predators. 
 * @author Aasminpreet Singh Kainth
 *
 */
public class BoardGameLinked {
	
	/**
	 * These instance variables contain the number of columns and number of rows of the board, respectively.
	 */
	private int boardLength;
	private int boardWidth;
	
	/**
	 * This instance variable holds the snake object of the game.
	 */
	private SnakeLinked theSnake; 
	
	/**
	 * This instance variable contains the tiles of the board, held in an array of doubly linked lists (each index in the array
	 * represents a row). 
	 */
	private DoubleList<String>[] board;
	
	/**
	 * This is the constructor for the board, used to transfer all items contained in the board file into the actual class.
	 * @param boardFile The file from which the items will be read.
	 */
	public BoardGameLinked(String boardFile) {
		
		// Try all operations including ones involving the linked list.
		try {
			
			MyFileReader reader = new MyFileReader(boardFile); // Used to read the file
			
			// Row and column of the initial position of the snake head.
			int snakeRow;
			int snakeCol;
			
			// Row and column of an item to be placed on the board (will be updated accordingly).
			int gameRow;
			int gameCol;
			
			// Item to be placed on the board.
			String gameObject; 
			
			// First two numbers are not necessary...skip them.
			reader.readInt();
			reader.readInt();
			
			// Initialize the number of columns and rows of the board with the next two numbers.
			boardLength = reader.readInt();
			boardWidth = reader.readInt();
			
			// Read the next two integers, initialize the snake object's head at those coordinates.
			snakeRow = reader.readInt();
			snakeCol = reader.readInt();
			
			theSnake = new SnakeLinked(snakeRow, snakeCol);
			
			// Create the array of doubly linked lists of the required size (number of rows).
			board = new DoubleList[boardWidth];
	
			// Initialize every index in the array to a doubly linked list of the required size (number of columns).
			// Set every node's data to "empty". 
			for (int i = 0; i<board.length; i++) {
				
				board[i] = new DoubleList<String>(); 
				
				for (int j = 0; j < boardLength; j++) {
					
					board[i].addData(0, "empty");
					
				}
				
			}
			
			// Read through the triplets of text, with the first two being the coordinates and the third being the item.
			// Add it to the correct spot in the board. 
			while(!reader.endOfFile()) {
				
				gameRow = reader.readInt();
				gameCol = reader.readInt(); 
				
				gameObject = reader.readString(); 
				
				if (gameRow != -1 && gameCol != -1 && gameObject != null) {
					
					board[gameRow].setData(gameCol, gameObject);
					
				}
				
			}
			
		// If an exception is thrown by the linked list, or any other operation, indicate that the constructor was halted.
		} catch (Exception e){
			
			System.out.println(e.getMessage());
			System.out.println("Constructor for BoardGameLinked class execution incomplete.");
			
		}
		
	}
	
	/**
	 * This method returns the String held by the node at the specified coordinate in the board. 
	 * @param row The row number of the node.
	 * @param col The column number of the node.
	 * @return The String held by the node.
	 * @throws InvalidPositionException Thrown if either coordinate is negative, or if row >= boardWidth, or col >=boardLength. 
	 */
	public String getObject(int row, int col) throws InvalidPositionException{
		
		String returnObject;  // Variable to hold the String of the node.
		
		// Throw exception with appropriate message if either coordinate value is invalid.
		if (row < 0 || row>=boardWidth) {
			
			throw new InvalidPositionException("The row number "+row+" is invalid. Cannot return object.");
			
		} else if (col < 0 || col>=boardLength) {
			
			throw new InvalidPositionException("The column number "+col+" is invalid. Cannot return object."); 
			
		}
		
		// Obtain the String from the node and return it.
		returnObject = board[row].getData(col);
		
		return returnObject;
		
	}
	
	/**
	 * This method updates the String held in a node of the board at a specified coordinate.
	 * @param row The row of the node.
	 * @param col The column of the node.
	 * @param newObject The new String.
	 * @throws InvalidPositionException Thrown if either coordinate is negative, or if row >= boardWidth, or col >=boardLength. 
	 */
	public void setObject(int row, int col, String newObject) throws InvalidPositionException{
		
		// Throw exception with appropriate message if either coordinate value is invalid.
		if (row < 0 || row>=boardWidth) {
			
			throw new InvalidPositionException("The row number "+row+" is invalid. Cannot update data of node.");
			
		} else if (col < 0 || col>=boardLength) {
			
			throw new InvalidPositionException("The column number "+col+" is invalid. Cannot update data of node."); 
			
		}
		
		// Update data in node.
		board[row].setData(col, newObject);
		
	}
	
	/**
	 * This method returns the Snake object.
	 * @return The Snake object.
	 */
	public SnakeLinked getSnakeLinked() {
		
		return theSnake; 
		
	}
	
	/**
	 * This method updates the snake object to a new one. 
	 * @param newSnake The new snake to replace the old one.
	 */
	public void setSnakeLinked(SnakeLinked newSnake) {
		
		theSnake = newSnake; 
		
	}
	
	/**
	 * This method returns the number of columns of the board.
	 * @return The number of columns.
	 */
	public int getLength() {
		
		return boardLength;
		
	}
	
	/**
	 * This method returns the number of rows of the board.
	 * @return The number of rows.
	 */
	public int getWidth() {
		
		return boardWidth;
		
	}

}
