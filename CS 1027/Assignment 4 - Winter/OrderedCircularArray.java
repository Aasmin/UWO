/**
 * This class represents an ordered list that is created using a circular array, and implements the SortedListADT interface.
 * @author Aasminpreet Singh Kainth
 *
 */
public class OrderedCircularArray<T> implements SortedListADT<T> {
	
	/**
	 * This instance variable represents the circular array of CellData objects that composes the ordered list.
	 */
	private CellData<T>[] list;
	
	/**
	 * Instance variables that indicate the position of the first data item and the last data item, respectively.
	 */
	private int front; 
	private int rear;
	
	/**
	 * This instance variable tracks the number of data items in the ordered list.
	 */
	private int count;
	
	/**
	 * This constructor initializes 'front' to 1, 'rear' to 0, 'count' to 0, and creates an array of type CellData of size 5.
	 */
	public OrderedCircularArray() {
		
		front = 1; 
		rear = 0;
		count = 0;
		list = (CellData<T>[])(new CellData[5]); 
		
	}
	
	/**
	 * This method inserts a new CellData object with the specified identifier and integer value in the correct sorted position
	 * of the ordered list.
	 * @param id The identifier of the new data item.
	 * @param value The integer value of the new data item.
	 */
	public void insert(T id, int value) {
		
		CellData<T> cell = new CellData<T>(id, value); //Create a new CellData object for the data item.
		int current = front; //This variable is used to traverse the array.
		
		//Expand the array if it's full.
		if(count == list.length) {
			expandCapacity();
		}
		
		//If the array is empty, add the data item to the front and update rear to the same position as 'front'.
		if(list[front]==null) {
			
			list[front] = cell;
			rear = current;
			
		} else {
			
			//As long as the traversing variable hasn't reached the spot after the last data item, and it isn't at a data item
			//that has a greater integer value than the data item to be added, keep traversing the array.
			while(current!=((rear+1)%list.length)&&list[current].getValue()<cell.getValue()) {
				
				current = (current+1)%list.length;
				
			}
			
			//This variable is used to swap data items in the array, initially starting at the spot after the last data item.
			int iterator = (rear+1)%list.length;
			
			//Work backwards from the position after the last data item, and swap each data item one spot after until it
			//clears a spot at the index for the current data item to be added.
			while(iterator!=current) {
				
				int index = (iterator-1)%list.length;
				
				if(index<0) {
					index = index + list.length; //Necessary because Java doesn't modulus negatives as expected.
				}
				
				list[iterator] = list[index];
				iterator = index;
					
			}
			
			//Insert the current data item at the newly cleared index in the circular array, and update the rear. 
			list[current] = cell;
			rear = (rear+1)%list.length;
			
		}
		
		count++;
		
	}
	
	/**
	 * This method is used to get the integer value of a data item in the list with the specified identifier.
	 * @param id This is the identifier of the data item to be searched for.
	 * @return The integer value of the specified data item.
	 * @throws InvalidDataItemException This is thrown when the specified data item cannot be found in the list.
	 */
	public int getValue(T id) throws InvalidDataItemException {
		
		//Acquire the index of the specified data item.
		int index = find(id);
		
		//If the index is -1, the data item wasn't found in the list. Throw the exception.
		if(index == -1) {
			
			throw new InvalidDataItemException("The data item requested cannot be found in the ordered list!");
			
		} else {
			
			return list[index].getValue(); //Acquire the integer value of the data item, and return it.
			
		}
		
	}
	
	/**
	 * This method removes a data item from the list with the specified identifier, without changing the value of 'front'.
	 * @param id The identifier of the data item.
	 * @throws InvalidDataItemException Thrown if the specified data item could not be found.
	 */
	public void remove(T id) throws InvalidDataItemException{
		
		//Acquire the index of the specified data item.
		int index = find(id);
		
		//If the index is -1, the data item is not in the list. Throw the exception.
		if(index == -1) {
			
			throw new InvalidDataItemException("The data item cannot be removed because it is not found in the ordered list!");
			
		} else {
			
			//Swap every data item in the array up by one spot, except the last one.
			while(index!=rear) {
				
				list[index] = list[(index+1)%list.length];
				index = (index+1)%list.length;
				
			}
			
			//Set the last data item to null to remove it.
			list[rear] = null;
			
			//This variable is used to calculate the new value of 'rear'. Then, 'rear' is updated accordingly.
			int temp = (rear-1)%list.length;
			
			if(temp<0) {
				temp = temp+list.length;
			}
			
			rear = temp;
			
			count--;
			
		}
		
	}
	
	/**
	 * This method is used to change the integer value of the data item with the specified identifier.
	 * @param id The identifier of the specified data item.
	 * @param newValue The new integer value of the data item.
	 * @throws InvalidDataItemException Thrown if the specified data item is not in the list.
	 */
	public void changeValue(T id, int newValue) throws InvalidDataItemException{
		
		//Acquire the index of the specified data item.
		int index = find(id);
		
		//If the index is -1, the data item is not in the list. Throw the exception.
		if(index == -1) {
			
			throw new InvalidDataItemException("Could not change value because no object with specified id was found.");
			
		} else {
			
			try {
				
				//First try removing the specified data item from the list.
				remove(id);
				
			}catch(InvalidDataItemException e) {
				
				System.out.println(e.getMessage());
				System.out.println("Changing value in ordered list execution has stopped.");
				
			}
			
			//Then, insert it again into the list, but this time with the new integer value to maintain order.
			insert(id, newValue);
			
		}
		
	}
	
	/**
	 * This method removes the data item with the smallest integer value, returns the identifier of the data item, 
	 * and changes the value of 'front'.
	 * @return The identifier of the data item with the smallest integer value.
	 * @throws EmptyListException Thrown if the ordered list is empty.
	 */
	public T getSmallest() throws EmptyListException{
		
		//If the ordered list is empty, throw the exception.
		if(isEmpty()) {
			
			throw new EmptyListException("The ordered list is empty. Cannot return and remove smallest.");
			
		} else {
			
			//This variable is used to store the identifier of the front data item.
			T dataItem = list[front].getId();
			
			//Remove the first data item, and update the value of 'front'.
			list[front] = null;
			front = (front+1)%list.length;
			
			count--;
			return dataItem;
			
		}
		
	}
	
	/**
	 * This method is used to check if the ordered list is empty.
	 * @return True if the list is empty, false if otherwise.
	 */
	public boolean isEmpty() {
		
		//Use 'count' to evaluate if the list is empty.
		if(count == 0) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	/**
	 * This method returns the number of data items in the ordered list.
	 * @return The number of data items in the ordered list.
	 */
	public int size() {
		
		return count;
		
	}
	
	/**
	 * This method returns the value of 'front'. 
	 * @return The value of 'front'.
	 */
	public int getFront() {
		
		return front;
		
	}
	
	/**
	 * This method returns the value of 'rear'.
	 * @return The value of 'rear'.
	 */
	public int getRear() {
		
		return rear;
		
	}
	
	/**
	 * This method doubles the size of the circular array, while maintaining the position of 'front'. 
	 */
	private void expandCapacity() {
		
		//Create a new array that is twice the size of the current circular array.
		CellData<T>[] largerList = (CellData<T>[])(new CellData[list.length*2]);
		
		//Add all the data items from the current array into the larger array.
		//Keep the position of 'front' the same, so start adding from there. 
		for(int i = front; i<front+list.length; i++) {
			
			largerList[i%largerList.length] = list[i%list.length];
			
		}
		
		//Update the value of rear, and set the current array to the larger array.
		rear = (front+list.length-1)%largerList.length;
		
		list = largerList;
		
	}
	
	/**
	 * This method is used to find the data item with the specified identifier in the ordered list.
	 * @param id The identifier of the data item.
	 * @return The index in the circular array of the data item.
	 */
	private int find(T id) {
		
		//The index is initially set to -1, and will change to the position of the data item if it exists.
		int index = -1;
		
		//This is a variable to traverse the array.
		int iterator = front;
		
		if(!isEmpty()) {
			
			//Loop through the array to the spot before 'rear', and try to find the data item with the proper identifier.
			while(iterator!=rear) {
				
				if(list[iterator].getId().equals(id)) {
					
					index = iterator; //Update the index if the data item is found.
				}
				
				iterator = (iterator+1)%list.length;
			
			}
			
			if(list[rear].getId().equals(id)) {
				
				index = rear;
				
			}
			
		}
		
		//Return the index, which is -1 if the data item wasn't found.
		return index;
		
	}

}
