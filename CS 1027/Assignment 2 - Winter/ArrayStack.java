/**
 * This class implements the ArrayStackADT interface and creates a stack using an array data structure.
 * @author Aasminpreet Singh Kainth
 *
 * @param <T> Represents a generic type variable. This class can hold a stack of any non-primitive object. 
 */
public class ArrayStack<T> implements ArrayStackADT<T> {
	
	/**
	 * Instance variable that stores the objects in the stack.
	 */
	private T[] stack;
	
	/**
	 * Instance variable that tracks the last data item position in the stack.
	 */
	private int top;
	
	/**
	 * Instance variable that stores what the initial size of the array for the stack should be.
	 */
	private int initialCapacity;
	
	/**
	 * Instance variables that determine how much a full array should increase by, and how much the array should 
	 * decrease by when specifications are met, respectively. 
	 */
	private int sizeIncrease;
	private int sizeDecrease; 
	
	/**
	 * This is the constructor that initializes the various instance variables and array. 
	 * @param initialCap This value is the initial capacity of the array.
	 * @param sizeInc This is the size increase value.
	 * @param sizeDec This is the size decrease value.
	 */
	public ArrayStack(int initialCap, int sizeInc, int sizeDec) {
		
		//Create a new stack of objects based on initial capacity value, and initialize the instance variables
		this.stack = (T[])(new Object[initialCap]); 
		this.initialCapacity = initialCap;
		this.sizeIncrease = sizeInc;
		this.sizeDecrease = sizeDec;
		
		//Initialize "top" to -1
		this.top = -1;
		
	}
	
	/**
	 * This method adds an object to the stack. It also updates the "top" instance variable accordingly.
	 * @param dataItem This is the object being added to the stack.
	 */
	public void push(T dataItem){

		// Increase the size of the array if it's full.
		if (stack.length-1 == top) {
			expandCapacity();
		}
		
		stack[top+1] = dataItem; 
		
		top++;
		
	}
	
	/**
	 * This method removes and returns the object at the top of the stack. 
	 * The "top" instance variable is also updated accordingly. 
	 * @return This is the object from the top of the stack being returned.
	 * @throws EmptyStackException Thrown if the stack is found empty.
	 */
	public T pop() throws EmptyStackException{
		
		T item; //This variable is used to store the removed object.
		
		//Check if the stack is empty. If it is, throw EmptyStackException.
		if (top == -1) {
			throw new EmptyStackException("This stack is empty.");
		}
		
		item = stack[top]; 
		stack[top] = null;
		top--;
		
		//After removing the object, check if the stack is less than 1/4 of the array length and the array length is larger 
		//than the initial capacity. If it is, decrease the size of the array.
		if ((top+1<stack.length/(float)4)&&(stack.length>this.initialCapacity)) {
			
			reduceCapacity();
			
		}
			
		return item;

	}
	
	/**
	 * This method returns the top item in the stack. An EmptyStackException will be thrown if the stack is found
	 * to be empty.
	 * @return This is the object from the top of the stack being returned.
	 * @throws EmptyStackException Thrown if the stack is found empty.
	 */
	public T peek() throws EmptyStackException{
		
		//Check if the stack is empty. If it is, throw EmptyStackException.
		if (top == -1) {
			throw new EmptyStackException("This stack is empty."); 
		}
		
		return stack[top]; 
		
	}
	
	/**
	 * This method determines if the stack is empty.
	 * @return True if the stack is empty, false if not.
	 */
	public boolean isEmpty() {
		
		return (top == -1);
		
	}
	
	/**
	 * This method returns the size of the stack.
	 * @return The stack size.
	 */
	public int size() { 
		
		return this.top+1;
		
	}
	
	/**
	 * This method returns the size of the array.
	 * @return The array size.
	 */
	public int length() {
		
		return this.stack.length;
		
	}
	
	/**
	 * This method creates and returns an unique String representation of the stack.
	 * @return The string representation.
	 */
	public String toString() {
		
		String result = "Stack: ";
		
		//Add the members of the stack to the string, from bottom to one from the top.
		for (int i = 0; i<top; i++) {
			result = result + this.stack[i].toString() + ", ";
		}
		
		//Add the top member to the string
		result = result + this.stack[top].toString() + ".";
		
		return result;
		
	}
	
	/**
	 * This method increases the array size by the value in sizeIncrease.
	 */
	private void expandCapacity() {
		
		//Create a bigger empty array. Size is the current size plus the sizeIncrease value.
		T[] bigger = (T[])(new Object[stack.length + this.sizeIncrease]);
		
		//Copy the objects into the new array.
		for(int i = 0; i < stack.length; i++) {
			bigger[i] = this.stack[i]; 
		}
		
		//Set the current array to the new array.
		this.stack = bigger;
		
	}
	
	/**
	 * This method reduces the size of the array by the sizeDecrease value.
	 */
	private void reduceCapacity() {
		
		//Create new smaller empty array. Size is current size minus sizeDecrease value.
		T[] smaller = (T[])(new Object[stack.length - this.sizeDecrease]); 
		
		//Copy the objects into the smaller array.
		for (int i = 0; i<top+1; i++) {
			
			smaller[i] = stack[i]; 
			
		}
		
		//Set the current array to the smaller array.
		stack = smaller;
		
	}
	
}
