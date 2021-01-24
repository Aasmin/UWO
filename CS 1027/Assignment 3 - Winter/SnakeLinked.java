/**
 * This class contains the information and methods of the Snake that moves on the board. 
 * @author Aasminpreet Singh Kainth
 *
 */
public class SnakeLinked {

	/**
	 * This instance variable tracks the number of tiles the snake occupies. 
	 */
	private int snakeLength;
	
	/**
	 * This instance variable holds the Position objects of the snake in a doubly linked list (where the first node is the head). 
	 */
	private DoubleList<Position> snakeBody;
	
	/**
	 * This constructor initializes the head of the snake to a Position object of the specified coordinates, and sets the length to 1.
	 * @param row The row of the head.
	 * @param col The column of the head.
	 */
	public SnakeLinked(int row, int col) {
		
		// Create a new Position object with the parameters.
		Position head = new Position(row, col);
		
		snakeLength = 1; 
		snakeBody = new DoubleList<Position>(); // Create the doubly linked list of type Position
		snakeBody.addData(0, head);  // Add the Position object to the list.
		
	}
	
	/**
	 * This method returns the number of tiles occupied by the snake.
	 * @return The number of tiles occupied by the snake.
	 */
	public int getLength() {
		
		return snakeLength;
		
	}
	
	/**
	 * This method returns the Position object at the specified node in the snake list. 
	 * @param index The index of the node whose Position object is to be returned.
	 * @return The Position object of the specified node.
	 */
	public Position getPosition(int index) {
		
		// Check to see if the index is valid. If not, return null. 
		if (index < 0 || index >= snakeLength) {
			
			return null;
			
		} else {
			
			// Try to return the data of the node at the specified index.
			try {
				
				return snakeBody.getData(index);  
				
			// If an exception arises from the call to the doubly linked list, indicate that the position object could not be 
			// accessed and returned.
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
				System.out.println("Could not access and return Position object.");
				
			}
			
			return null; // If an exception arose, return null instead. 
			
		}
		
	}
	
	/**
	 * This method checks to see if a certain Position object is also found in the Snake doubly linked list.
	 * @param pos The Position object of interest to check.
	 * @return true if the Snake contains the Position object, false if otherwise.
	 */
	public boolean snakePosition(Position pos) {
		
		// Try to find the Position object in the Snake. 
		try {
			
			// Traverse through the Snake list, and use .equals() to compare the Position objects. Return true if found.
			for(int i = 0; i<snakeLength; i++) {
			
				if(snakeBody.getData(i).equals(pos)) {  
				
					return true;
				
				}
			
			} 
			
		// If an exception arose from the linked list methods, or anything else, indicate that the Snake data could not be traversed. 
		} catch (Exception e) {
				
				System.out.println(e.getMessage());
				System.out.println("Snake position data could not be accessed.");
				
			}
		
		return false; // Return false if no matching Position Object was found.
		
	}
	
	/**
	 * This method returns a Position object that represents the new position of the head of the Snake when moved in a certain direction.
	 * @param direction The direction of snake movement.
	 * @return The new head Position object.
	 */
	public Position newHeadPosition(String direction) {
		
		// Variable to hold the new Position object. 
		Position newPosition = null;
		
		// Try to use methods to access various nodes and the linked list
		try {
			
			// Based on the direction of movement, create a new Position object that is based on the current head position.
			if(direction.equals("right")) {
				
				newPosition = new Position(snakeBody.getData(0).getRow(), snakeBody.getData(0).getCol()+1);
				
			} else if (direction.equals("left")) {
				
				newPosition = new Position(snakeBody.getData(0).getRow(), snakeBody.getData(0).getCol()-1);
				
			} else if (direction.equals("up")) {
				
				newPosition = new Position(snakeBody.getData(0).getRow()-1, snakeBody.getData(0).getCol());
				
			} else if (direction.equals("down")) {
				
				newPosition = new Position(snakeBody.getData(0).getRow()+1, snakeBody.getData(0).getCol());
				
			}
		
		// If an exception was thrown, indicate that the new head position could not be created. 
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println("Excecution to calculate new snake head position stopped.");
			
		}
		
		return newPosition; // Return the new Position object.
		
	}
	
	/**
	 * This method updates the entire Snake linked list to represent movement in a specified direction. 
	 * @param direction The direction of snake movement. 
	 */
	public void moveSnakeLinked(String direction) {
		
		// Obtain a Position object that represents the new location of the head of the snake after movement. 
		Position newHead = newHeadPosition(direction);
		
		// Try to use linked list methods. 
		try {
			
			// Add the new head to the front of the list, and remove the last node in the list.
			snakeBody.addData(0, newHead);
			snakeBody.removeData(snakeLength);
			
		// If exception was thrown, indicate that the snake could not be updated.
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println("Could not move snake.");
			
		}
		
	}
	
	/**
	 * This method deletes the last node in the Snake object, and decreases the count by 1. 
	 */
	public void shrink() {
		
		// Try to use linked list method.
		try {
			
			// Remove last node, decrease snake length by 1. 
			snakeBody.removeData(snakeLength-1);
			snakeLength--;
			
		// If exception was thrown, indicate that the snake could not be shrunk. 
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println("Could not perform shrink method on snake.");
			
		}
		
	}
	
	/**
	 * This method updates the Snake linked list to represent movement in a specified direction after growing by 1 tile. 
	 * @param direction The direction of snake movement.
	 */
	public void grow(String direction) {
		
		// Obtain a Position object that represents the new location of the head of the snake after movement. 
		Position newHead = newHeadPosition(direction);
		
		// Try to use linked list method
		try {
			
			// Add the new head to the Snake linked list. Increase the snake length by 1.
			snakeBody.addData(0, newHead);
			snakeLength++;
			
		// If an exception was thrown, indicate that the Snake could not be grown.
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println("Could not grow snake.");
			
		}
		
	}
	
	
}
