/**
 * This class holds a city map, and has the methods required to find the shortest legal path from the starting block to the
 * destination block, if it exists.
 * @author Aasminpreet Singh Kainth
 *
 */
public class ShortestPath {
	
	/**
	 * This instance variable holds the city map object, whose shortest legal path is to be found.
	 */
	CityMap cityMap; 
	
	/**
	 * This constructor initializes the city map instance variable to the parameter.
	 * @param theMap The city map object.
	 */
	public ShortestPath(CityMap theMap) {
		
		cityMap = theMap;
		
	}
	
	/**
	 * This method finds the shortest legal path from the starting block to the destination block, if it exists, 
	 * and prints the appropriate message afterwards.
	 */
	public void findShortestPath() {
		
		//Create a circular array that will store the possible next tiles in the different paths.
		OrderedCircularArray<MapCell> path = new OrderedCircularArray<MapCell>();
		
		//Acquire the starting cell.
		MapCell starting = cityMap.getStart();
		
		//This variable is to be updated continuously with the cell with the shortest distance value in the circular array.
		MapCell current=null;
		
		//This variable is to check if the destination has been reached, which is initially false.
		boolean destination = false;
		
		//Add the starting cell into the circular array, and mark it in the list.
		path.insert(starting, 0);
		
		starting.markInList(); 
		
		try {
			
			//As long as the circular array isn't empty, and the destination hasn't been reached, run this loop.
			while(!path.isEmpty()&&!destination) {
				
				//Take the cell with the shortest distance out of the circular array, and mark it accordingly.
				current = path.getSmallest();
				current.markOutList();
				
				MapCell next;
				int distance;
				
				if(current.isDestination()) {
					
					destination = true; //If the current cell is the destination, end the loop.
					
				} else {
					
					next = nextCell(current); //Acquire the next possible neighbour of the current cell.
					
					//Don't run if next is null, meaning there is no other possible neighbour in the path for the current cell.
					while(next!=null) {
						
						distance = current.getDistanceToStart() + 1;
						
						//If the distance of the selected neighbouring cell is currently more than 1 more than the current cell's 
						//distance, update the distance of the neighbouring cell. Then, set the current cell as its predecessor.
						if(next.getDistanceToStart()>distance) {
							
							next.setDistanceToStart(distance);
							next.setPredecessor(current);
							
						}
						
						distance = next.getDistanceToStart(); 
						
						if(next.isMarkedInList() && distance<path.getValue(next)) {
							
							path.changeValue(next, distance); //If the neighbouring cell is in the circular array, but with a 
															  //larger distance value than the new updated distance, change its value.
							
						} else if(!next.isMarkedInList()){
							
							path.insert(next, distance);  //If the neighbouring cell isn't in the circular array, add it in.
							next.markInList();
							
						}
						
						next = nextCell(current); //Look for the next possible neighbour, if any.
						
					}
					
				}
				
			}
			
			//Catch all the possible exceptions that might be thrown by the method calls. Print the appropriate messages.
		} catch (EmptyListException e) {
			
			System.out.println(e.getMessage());
			System.out.println("Path finding execution stopped.");
			
		} catch (InvalidNeighbourIndexException e) {
			
			System.out.println("The program tried to access an invalid neighbour of a tile.");
			System.out.println("Path finding execution stopped.");
			
		} catch (InvalidDataItemException e) {
			
			System.out.println(e.getMessage());
			System.out.println("Path finding execution stopped.");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage()+" Path finding execution has stopped.");
			
		}
		
		//If a path was found, print the number of tiles in the path.
		if(destination) {
			
			System.out.println("Number of tiles in path: " + (current.getDistanceToStart()+1));
			
		} else {
			
			System.out.println("No path found."); //Otherwise, indicate that a path wasn't found.
			
		}
		
	}
	
	/**
	 * This method is used to select the next possible tile in the path from the current tile, if it exists.
	 * @param cell The current tile.
	 * @return The next possible tile. Null if it doesn't exist.
	 */
	private MapCell nextCell(MapCell cell) {
		
		//This is an array that holds the possible neighbouring tiles.
		MapCell[] potentialCells = {null,null,null,null};
		
		//This variable is an indicator. If it remains -1, that means no possible neighbouring tiles were found.
		int next = -1; 
			
		//If the cell is a north road, check that its neighbour to the north is not null or marked. Then, see if it's either
		//an intersection, the destination, or another north road.
		if(cell.getNeighbour(0)!=null && cell.isNorthRoad()) {

			if(!cell.getNeighbour(0).isMarked()) {

				if(cell.getNeighbour(0).isIntersection()||cell.getNeighbour(0).isDestination()||cell.getNeighbour(0).isNorthRoad()) {

					//Add the north neighbour to the array of potential next tiles.
					potentialCells[0] = cell.getNeighbour(0);
					next = 0;

				}

			}

		//If the cell is an east road, check that its neighbour to the east is not null or marked. Then, see if it's either
		//an intersection, the destination, or another east road.
		} else if (cell.getNeighbour(1)!=null && cell.isEastRoad()) {

			if(!cell.getNeighbour(1).isMarked()) {

				if(cell.getNeighbour(1).isIntersection()||cell.getNeighbour(1).isDestination()||cell.getNeighbour(1).isEastRoad()) {

					//Add the east neighbour to the potential next tiles.
					potentialCells[1] = cell.getNeighbour(1);
					next = 0;

				}

			}

		//If the cell is a south road, check that its neighbour to the south is not null or marked. Then, see if it's either
		//an intersection, the destination, or another south road.
		} else if (cell.getNeighbour(2)!=null && cell.isSouthRoad()) {

			if(!cell.getNeighbour(2).isMarked()) {

				if(cell.getNeighbour(2).isIntersection()||cell.getNeighbour(2).isDestination()||cell.getNeighbour(2).isSouthRoad()) {

					//Add the south neighbour to the potential next tiles.
					potentialCells[2] = cell.getNeighbour(2);
					next = 0;

				}

			}

		//If the cell is a west road, check that its neighbour to the west is not null or marked. Then, see if it's either
		//an intersection, the destination, or another west road.
		} else if (cell.getNeighbour(3)!=null && cell.isWestRoad()) {

			if(!cell.getNeighbour(3).isMarked()) {

				if(cell.getNeighbour(3).isIntersection()||cell.getNeighbour(3).isDestination()||cell.getNeighbour(3).isWestRoad()) {

					//Add the west neighbour to the potential next tiles.
					potentialCells[3] = cell.getNeighbour(3);
					next = 0;

				}

			}

		//If the current cell is an intersection, go through all 4 of its potential neighbours.
		} else {

			for(int i = 0; i<4; i++) {

				//Check that the neighbour isn't null or marked.
				if(cell.getNeighbour(i)!=null) {

					if(!cell.getNeighbour(i).isMarked()) {

						//Check to see if the neighbour is either an intersection, or a one-way road in the proper orientation to
						//continue the path.
						if(cell.getNeighbour(i).isIntersection()||cell.getNeighbour(i).isDestination()||(i==0
								&&cell.getNeighbour(i).isNorthRoad())||(i==1&&cell.getNeighbour(i).isEastRoad())||(i==2
								&&cell.getNeighbour(i).isSouthRoad())||(i==3&&cell.getNeighbour(i).isWestRoad())) {

							//Add the tile to the possible next tiles.
							potentialCells[i] = cell.getNeighbour(i);
							next = 0;

						}

					}

				}

			}

		}

		//If no tiles were found, return null.
		if(next == -1) {

			return null;

		//Otherwise, select one (or one of) the next possible tiles and return it.
		} else {

			for(int i = 3; i >= 0; i--) {

				if(potentialCells[i]!=null) {

					next = i;
				}

			}

			return potentialCells[next];

		}
		
	}

}
