/**
 * This class contains the information and methods of a doubly linked list, consisting of nodes of the class DoubleNode.
* @author Aasminpreet Singh Kainth
 *
 */
public class DoubleList<T> {

	/**
	 * These instance variables reference the first node in the linked list and the last node in the linked list, respectively.
	 */
	private DoubleNode<T> head;
	private DoubleNode<T> rear; 
	
	/**
	 * This instance variable tracks the number of nodes in the linked list.
	 */
	private int numDataItems;
	
	/**
	 * This constructor initializes the head and rear variables to null, while setting the number of nodes to 0. 
	 */
	public DoubleList() {
		
		head = null;
		rear = null;
		numDataItems = 0;
		
	}
	
	/**
	 * This method adds a new node (storing newData) to the existing linked list at the specified index.
	 * @param index The index of insertion for the new node. 
	 * @param newData The data that the new node will contain.
	 * @throws InvalidPositionException This exception will be thrown if the index is less than 0, or greater than the number of nodes.
	 */
	public void addData(int index, T newData) throws InvalidPositionException{
		
		DoubleNode<T> newNode = new DoubleNode<T>(newData); // Create a new node containing the specified data
		DoubleNode<T> current = head; // This reference is used to traverse the list without altering 'head'
		
		// Throw exception if index is invalid.
		if (index < 0 || index>numDataItems) {
			
			throw new InvalidPositionException("The index "+index+" is invalid. Cannot ADD new node to list."); 
			
		}
		
		// If the node is being inserted at the front of the list.
		if (index == 0) {
			
			newNode.setPrev(null);
			
			//If the list is empty, set head and rear both to point to the new node.
			if (numDataItems == 0) {
				
				newNode.setNext(null);
				head = newNode;
				rear = newNode; 
				
			//If the list isn't empty, only set head to the new node. 
			} else {
				
				newNode.setNext(current);
				current.setPrev(newNode);
				head = newNode; 
				
			}
			
		// If the node isn't being inserted at the front of the list.
		} else {
			
			// Traverse through the linked list until current points to the node before the point of insertion.
			for(int i = 0; i < index-1; i++) {
				
				current = current.getNext();
				
			}
			
			newNode.setPrev(current);
			newNode.setNext(current.getNext());
			
			// If the place of insertion is NOT at the rear.
			if (index < numDataItems) {
				
				current.getNext().setPrev(newNode);
				
			// If the place of insertion is at the rear, update the 'rear' variable.
			} else {
				
				rear = newNode;
				
			}
			
			current.setNext(newNode);
			
		}
		
		numDataItems++; // Update the counter
		
	}
	
	/**
	 * This method will return the entire node at the indicated index, without removing the node itself.
	 * @param index The index of the node to return.
	 * @return The node to be retrieved.
	 * @throws InvalidPositionException Thrown if the index is less than 0, or greater than or equal to the number of nodes.
	 */
	public DoubleNode<T> getNode(int index) throws InvalidPositionException{
		
		DoubleNode<T> returnNode = head; // This variable will hold a reference to the return node.
		
		// Throw exception if the index is invalid.
		if (index < 0 || index >= numDataItems) {
			
			throw new InvalidPositionException("The index "+index+" is invalid. Cannot RETURN node from list."); 
			
		}
		
		// Traverse through the linked list to the specified index.
		for(int i = 0; i < index; i++) {
			
			returnNode = returnNode.getNext(); 
			
		}
		
		return returnNode; // Return the specified node, without removal.
		
	}
	
	/**
	 * This method will remove the node at the specified index in the linked list.
	 * @param index The index of the node to remove.
	 * @throws InvalidPositionException Thrown if the index is less than 0, or greater than or equal to the number of nodes.
	 */
	public void removeData(int index) throws InvalidPositionException{
		
		DoubleNode<T> current = head; // A variable to traverse through the list without changing 'head'.
		
		// Throw exception if index is invalid.
		if (index < 0 || index >= numDataItems) {
			
			throw new InvalidPositionException("The index "+index+" is invalid. Cannot REMOVE node from list.");
			
		}
		
		// If the node is the first node.
		if (index == 0) {
			
			// If the node is the ONLY node, set head and rear to null.
			if (numDataItems == 1) {
				
				head = null;
				rear = null; 
				
			// If the node is not the only node, update head.
			} else {
				current = current.getNext();
				current.setPrev(null);
				head = current;
			}
			
		// If we're not removing the first node.
		} else {
			
			// Traverse through the list to the node BEFORE the node of removal.
			for (int i = 0; i < index-1; i++) {
				
				current = current.getNext();
				
			}
			
			// If the node is the LAST node, update rear accordingly.
			if (index == numDataItems-1) {
				
				current.setNext(null);
				rear = current;
				
			// If the node is not the last node.
			} else {
				
				current.getNext().getNext().setPrev(current);  
				current.setNext(current.getNext().getNext());  
				
			}
			
		}
		
		numDataItems--; // Decrease the counter by 1
		
	}
	
	/**
	 * This method is used to obtain the data of the node at the specified index. 
	 * @param index The index of the node whose data is to be returned.
	 * @return The data of the node.
	 * @throws InvalidPositionException Thrown if the index is less than 0, or greater than or equal to the number of nodes.
	 */
	public T getData(int index) throws InvalidPositionException{
		
		T returnData; // Holds the return data.
		DoubleNode<T> current = head;  // Variable to traverse through the list without changing 'head'.
		
		// Throw exception if index is invalid.
		if (index < 0 || index >= numDataItems) {
			
			throw new InvalidPositionException("The index "+index+" is invalid. Cannot return data from node."); 
			
		}
		
		// Traverse through the list to the node specified by the index.
		for (int i = 0; i<index; i++) {
			
			current = current.getNext(); 
			
		}
		
		// Obtain the data from the node and return it.
		returnData = current.getData();
		
		return returnData; 
		
	}
	
	/**
	 * This method is used to update the data in a node at the specified index.
	 * @param index The index of the node whose data is to be updated.
	 * @param newData The new data.
	 * @throws InvalidPositionException Thrown if the index is less than 0, or greater than or equal to the number of nodes.
	 */
	public void setData(int index, T newData) throws InvalidPositionException{
		
		DoubleNode<T> current = head; // Variable to traverse through the list without changing 'head'.
		
		// Throw exception if index is invalid.
		if (index < 0 || index >= numDataItems) {
			
			throw new InvalidPositionException("The index "+index+" is invalid. Cannot add data to node."); 
			
		}
		
		// Traverse the list to the node specified by the index.
		for (int i = 0; i < index; i++) {
			
			current = current.getNext();
			
		}
		
		// Set the data of the node to the new data.
		current.setData(newData);
		
	}
	
}
