/**
 * This class holds the value and identifier of a data item for use in the ordered circular array.
 * @author Aasminpreet Singh Kainth
 *
 */
public class CellData<T> {
	
	/**
	 * Instance variables to hold the generic identifier and integer value in the object.
	 */
	private T id;
	private int value;
	
	/**
	 * This constructor initializes the identifier and integer value for the data item.
	 * @param theId The identifier.
	 * @param theValue The integer value.
	 */
	public CellData(T theId, int theValue) {
		
		id = theId;
		value = theValue;
		
	}
	
	/**
	 * This method returns the integer value of the data item.
	 * @return The integer value.
	 */
	public int getValue() {
		
		return value;
		
	}
	
	/**
	 * This method returns the identifier of the data item.
	 * @return The identifier.
	 */
	public T getId() {
		
		return id;
		
	}
	
	/**
	 * This method changes the integer value to a new specified value.
	 * @param newValue The new integer value to update the data item to.
	 */
	public void setValue(int newValue) {
		
		value = newValue;
		
	}
	
	/**
	 * This method changes the identifier to a new one.
	 * @param newId The new identifier to update the data item to.
	 */
	public void setId(T newId) {
		
		id = newId;
		
	}

}
