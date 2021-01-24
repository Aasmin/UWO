/**
 * This class contains the information and methods of a node in a doubly linked list.
* @author Aasminpreet Singh Kainth
 *
 */
public class DoubleNode<T> {
	
	/**
	 * Instance variables that reference the next and previous nodes of the current node, respectively.
	 */
	private DoubleNode<T> next;
	private DoubleNode<T> prev;
	
	/**
	 * Instance variable that holds the data of the current node.
	 */
	private T data; 
	
	/**
	 * This constructor initializes the instance variables to null.
	 */
	public DoubleNode() {
		
		next = null;
		prev = null;
		data = null; 
		
	}
	
	/**
	 * This constructor initializes the next and previous nodes to null, and sets the data for the current node.
	 * @param newData The data for the current node.
	 */
	public DoubleNode(T newData) {
		
		next = null;
		prev = null;
		data = newData; 
		
	}
	
	/**
	 * This method returns the next node of the current node.
	 * @return The next node. 
	 */
	public DoubleNode<T> getNext(){
		
		return next;
		
	}
	
	/**
	 * This method returns the previous node of the current node.
	 * @return The previous node.
	 */
	public DoubleNode<T> getPrev(){
		
		return prev;
		
	}
	
	/**
	 * This method returns the data of the current node.
	 * @return The data.
	 */
	public T getData() {
		
		return data; 
		
	}
	
	/**
	 * This method sets the next node of the current node to the parameter. 
	 * @param nextNode The new node for 'next' to be set to. 
	 */
	public void setNext(DoubleNode<T> nextNode) {
		
		next = nextNode;
		
	}
	
	/**
	 * This method sets the previous node of the current node to the parameter.
	 * @param prevNode The new node for 'prev' to be set to.
	 */
	public void setPrev(DoubleNode<T> prevNode) {
		
		prev = prevNode; 
		
	}
	
	/**
	 * This method stores the parameter newData in the data of the current node.
	 * @param newData The new data for 'data' to be set to.
	 */
	public void setData(T newData) {
		
		data = newData;
		
	}

}
