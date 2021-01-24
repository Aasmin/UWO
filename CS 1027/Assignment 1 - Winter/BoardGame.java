public class BoardGame {
	private String[][] matrix;
	private int board_length, board_width;
	private Snake theSnake;
	
	public BoardGame(String boardFile) {
			int row, col;
			String type;
			MyFileReader in = new MyFileReader(boardFile);
			String line;

			int tile_length = in.readInt();
			int tile_width = in.readInt();
			board_length = in.readInt();
			board_width = in.readInt();
			matrix = new String[board_width][board_length];
			row = in.readInt();
			col = in.readInt();
			theSnake = new Snake(row,col);
			
			for(int i = 0; i < board_width; i++)
				for(int j = 0; j < board_length; j++) 
					matrix[i][j] = "empty";					
			
			while (in.endOfFile() == false) {
				row = in.readInt();
				col = in.readInt();
				type = in.readString();
				matrix[row][col] = type;
			}
	}
	
	public String getObject(int row, int col) {
		return matrix[row][col];
	}
	
	public void setObject(int row, int col, String newObject) {
		matrix[row][col] = newObject;
	}
	
	public Snake getSnake() {
		return theSnake;
	}
	
	public void setSnake(Snake newSnake) {
		theSnake = newSnake;
	}
	
	public int getLength() {
		return board_length;
	}
	
	public int getWidth() {
		return board_width;
	}
	
	public String getType(int row, int col) {
		return matrix[row][col];
	}
}