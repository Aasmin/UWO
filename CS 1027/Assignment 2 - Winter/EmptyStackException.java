/**
 * This class is an exception that results from attempting to return from an empty stack. 
 * A message will be printed that describes the exception origin.
 * @author Aasminpreet Singh Kainth
 */
public class EmptyStackException extends RuntimeException {

	/**
	 * This constructor creates the exception with the message about its origin.
	 * @param message The accompanying message to the exception.
	 */
	public EmptyStackException(String message) {
		
		super(message);
		
	}
}
