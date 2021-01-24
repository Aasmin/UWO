/**
 * This class represents the path from the starting cell on the map to the destination cell, if there is one. 
 * It is dependent on the specific Map object used.
 * @author Aasminpreet Singh Kainth
 *
 */
public class Path {
	
	/**
	 * This instance variable stores the Map object to find a path for.
	 */
	private Map cityMap; 
	
	/**
	 * This constructor initializes the instance variable for the Map object.
	 * @param theMap
	 */
	public Path(Map theMap) {
		
		this.cityMap = theMap; 
		
	}
	
	/**
	 * This method looks for a path from the starting cell to the destination cell on the Map object provided.
	 * It will print a message depending on if it was successful, or not. 
	 */
	public void findPath(){
		
		try {
			
			//Declare and initialize an empty array stack object of type MapCell, 
			//sending appropriate parameters to constructor.
			ArrayStack<MapCell> stack = new ArrayStack<MapCell>(10, 5, 2); 
			
			//Declare a variable to store the next cell attempted in the path.
			MapCell next;
			
			//Put the start cell as the first item in the stack and mark it as "in".
			stack.push(this.cityMap.getStart());
			this.cityMap.getStart().markInStack();
			
			//Loop as long as the stack isn't empty and the destination isn't the top item in the stack.
			while(!stack.isEmpty()&&!stack.peek().isDestination()) {
				
				//The next cell attempted in the path is based on sending the top item in the stack to the method nextCell().
				next = nextCell(stack.peek());
				
				//If the next cell isn't null, add it to the stack and mark it as "in". Otherwise, remove the current top item 
				//and mark it as "out". 
				if(next!=null) {
					stack.push(next);
					next.markInStack();
				} else {
					stack.pop().markOutStack();
				}
			}

			//Print the final messages. If no path was found, print appropriate message. If path was found, print message
			//and also specify how many cells the path is composed of.
			if(stack.isEmpty()) {
				System.out.println("Sorry, no path was found..."); 
			} else if (stack.peek().isDestination()) {
				System.out.println("The destination has been reached!"); 
				System.out.println("The total number of cells in path: " + stack.size()); 
			}
		
		} catch(EmptyStackException e) {
			
			//Print the message that there was an empty stack and path finding had to stop.
			System.out.println(e.getMessage());
			System.out.println("Path finding execution stopped.");
			
		} catch(InvalidNeighbourIndexException e) {
			
			//Print message about trying to index inappropriate neighbour. 
			System.out.println(e.getMessage());
			System.out.println("Path finding execution stopped.");
		}
		
	}
	
	/**
	 * This method is used to find the next best cell to continue the path based on the top cell in the path finding stack.
	 * @param cell This is the top cell in the stack, used to evaluate neighbours.
	 * @return This is the next best cell to add to the stack, null if there are none.
	 */
	private MapCell nextCell(MapCell cell){

		//This is a marker variable. It will be -1 throughout the method if no new cell can be found. 
		//If a new cell can be found, it will take the value -2. 
		int bestCell = -1;
		
		//This is an array that holds new cells that can be legally accessed. Index 0 holds a cell to the North, 1 to the East, etc.
		MapCell[] potentialCells = {null,null,null,null}; 
		
		
		//The first set of "if" and "else if" statements are used to determine if the current cell is a one-way road. 
		//It also checks to see if the neighbouring cell (there can only be one legal neighbour for a one-way road) is not null.
		
		//The second set of "if" statements WITHIN the first set is used to make sure that the one neighbour cell is not 
		//already marked or a block. It also makes sure that the cell is either a destination, intersection, or a one-way road
		//that points in the same direction. 
		
		if(cell.isNorthRoad()&&cell.getNeighbour(0)!=null) {
			
			if(!cell.getNeighbour(0).isMarked()&&!cell.getNeighbour(0).isBlock()&&(cell.getNeighbour(0).isNorthRoad()||
					cell.getNeighbour(0).isDestination()||cell.getNeighbour(0).isIntersection())) {
				
				//Add to the array of legal cells and update marker.
				potentialCells[0] = cell.getNeighbour(0);
				bestCell = -2;
				
			}
			
		} else if (cell.isEastRoad()&&cell.getNeighbour(1)!=null) {
			
			if(!cell.getNeighbour(1).isMarked()&&!cell.getNeighbour(1).isBlock()&&(cell.getNeighbour(1).isEastRoad()||
					cell.getNeighbour(1).isDestination()||cell.getNeighbour(1).isIntersection())) {
				
				//Add to array of legal cells and update marker.
				potentialCells[1] = cell.getNeighbour(1); 
				bestCell = -2;
				
			}
			
		} else if (cell.isSouthRoad()&&cell.getNeighbour(2)!=null) {
			
			if(!cell.getNeighbour(2).isMarked()&&!cell.getNeighbour(2).isBlock()&&(cell.getNeighbour(2).isSouthRoad()||
					cell.getNeighbour(2).isDestination()||cell.getNeighbour(2).isIntersection())) {
				
				//Add to array of legal cells and update marker.
				potentialCells[2] = cell.getNeighbour(2);
				bestCell = -2;
				
			}
			
		} else if (cell.isWestRoad()&&cell.getNeighbour(3)!=null) {
			
			if(!cell.getNeighbour(3).isMarked()&&!cell.getNeighbour(3).isBlock()&&(cell.getNeighbour(3).isWestRoad()||
					cell.getNeighbour(3).isDestination()||cell.getNeighbour(3).isIntersection())) {
				
				//Add to array of legal cells and update marker.
				potentialCells[3] = cell.getNeighbour(3);
				bestCell = -2;
				
			}
			
		//This last "else" branch is used for current cells that aren't a one-way road.
		} else {
		
			//The current cell must therefore be an intersection. Loop through all potentially legal neighbours and check if it is.
			//Add it to the array of legal cells if it is. 
			for (int i = 0; i<4; i++) {
				
				if(cell.getNeighbour(i)!=null){
					
					if(!cell.getNeighbour(i).isMarked()&&!cell.getNeighbour(i).isBlock()) {
						
						if (cell.getNeighbour(i).isDestination()||cell.getNeighbour(i).isIntersection()||
							(cell.getNeighbour(i).isNorthRoad()&&i==0)||(cell.getNeighbour(i).isEastRoad()&&i==1)||
							(cell.getNeighbour(i).isSouthRoad()&&i==2)||(cell.getNeighbour(i).isWestRoad()&&i==3)) {
							
								//Add to array of legal cells and update marker.
								potentialCells[i] = cell.getNeighbour(i); 
								bestCell = -2;
								
						}
						
					}
					
				}
				
			}
		}
		
		//If the marker was not updated, there was no legal next cell. Return null.
		if(bestCell == -1) {
			return null;
		}
		
		//First, see if there are any one-way roads in the array of legal cells. If there are, take the one with the smallest index.
		//This index number will be stored in the marker "bestCell". 
		for(int y = 3; y>-1; y--) {
			
			if(potentialCells[y]!=null) {

				if(potentialCells[y].isNorthRoad()||potentialCells[y].isSouthRoad()||potentialCells[y].isEastRoad()||
					potentialCells[y].isWestRoad()) {
					
					bestCell = y;
					
				}
				
			}	
			
		}
		
		//Second, see if there are any intersections as legal cells. This will override the one-way roads. Take the smallest index.
		for (int w = 3; w>-1; w--) {
			
			if(potentialCells[w]!=null) {
				
				if(potentialCells[w].isIntersection()) { 
					
					bestCell = w;
					
				}
				
			}
			
		}
		
		//Finally, see if any of the legal cells are a destination cell. This will override all previous cells. 
		for (int j = 0; j<4; j++) {
			
			if(potentialCells[j]!=null) {
				
				if(potentialCells[j].isDestination()) {
					
					bestCell = j;
					
				}
				
			}
				
		}
		
		return potentialCells[bestCell]; 
	
	}

}
