/**
 * This class holds the information representing the board game involving the snake, apples, rocks and scissors. 
 * @author Aasminpreet Singh Kainth
 *
 */
public class BoardGame {

	/**
	 * Instance variables that hold the columns and rows of the board, respectively. 
	 */
	private int board_length;
	private int board_width;
	
	/**
	 * Instance variable that holds the Snake object of the game. 
	 */
	private Snake theSnake; 
	
	/**
	 * 2D array instance variable that stores strings representing the object at each grid location. 
	 */
	private String[][] matrix; 
	
	/**
	 * This constructor reads the input file and assigns the values of the file to objects on the game board.
	 * @param boardFile The input file, containing board dimensions, object positions and initial snake position. 
	 */
	public BoardGame(String boardFile) {
		
		MyFileReader reader = new MyFileReader(boardFile); // Create a new reader object and send the input file to its constructor.
		
		// The initial coordinates of the snake
		int snakeRow; 
		int snakeCol; 
		
		// The coordinates for objects in the file
		int gameRow;
		int gameCol;
		
		// Holds the object string from the file
		String gameObject;
		
		// Read and ignore the first 2 values
		reader.readInt();
		reader.readInt();
		
		// Set the board length and width using the next two numbers.
		this.board_length = reader.readInt(); 
		this.board_width = reader.readInt(); 
		
		// Place the initial position of the snake in variables using the next two numbers.
		snakeRow = reader.readInt();
		snakeCol = reader.readInt(); 
		
		// Create the new snake object for the game using the coordinates from file.
		theSnake = new Snake(snakeRow, snakeCol); 
		
		// Create the 2D array representing the game board and initialize all entries to "empty". 
		matrix = new String[this.board_width][this.board_length]; 
		
		for (int i = 0; i < board_width; i++) {
			for (int j = 0; j<board_length; j++) {
				matrix[i][j] = "empty";
			}
		}
		
		// Read each object in the file and its position, as long as there is still text. 
		while (!reader.endOfFile()) {
			
			// First two numbers in the triplet are the coordinates.
			gameRow = reader.readInt();
			gameCol = reader.readInt();
			
			// Third entry is the object string
			gameObject = reader.readString(); 
			
			// Make sure that neither one of the 3 entries are empty, and then change the 2D array accordingly.
			if (gameRow != -1 && gameCol != -1 && gameObject != null) {
				matrix[gameRow][gameCol] = gameObject;
			}
		}
		
	}
	
	/**
	 * This method returns the object string stored at the specified coordinates.
	 * @param row The row of the object.
	 * @param col The column of the object.
	 * @return The string at the specified position. 
	 */
	public String getObject(int row, int col) {
		
		return matrix[row][col]; 
		
	}
	
	/**
	 * This method sets a new object to the specified coordinates in the 2D array.
	 * @param row The row of the object.
	 * @param col The column of the object.
	 * @param newObject The new object string. 
	 */
	public void setObject(int row, int col, String newObject) {
		
		matrix[row][col] = newObject;
		
	}
	
	/**
	 * Method to return the snake object.
	 * @return The snake object.
	 */
	public Snake getSnake() {
		
		return theSnake;
		
	}
	
	/**
	 * Method to set the game snake object to a new snake object.
	 * @param newSnake The new snake object. 
	 */
	public void setSnake(Snake newSnake) {
		
		this.theSnake = newSnake;
		
	}
	
	/**
	 * Method to return the board length.
	 * @return The board length (columns). 
	 */
	public int getLength() {
		
		return this.board_length;
		
	}
	
	/**
	 * Method to return the board width.
	 * @return The board width (rows). 
	 */
	public int getWidth() {
		
		return this.board_width;
		
	}
	
	/**
	 * This method returns the object string stored at the specified coordinates.
	 * @param row The row of the object.
	 * @param col The column of the object.
	 * @return The string at the specified position. 
	 */
	public String getType(int row, int col) {
		
		return matrix[row][col];
		
	}
}
