package Exceptions;
/**
 * Modela la excepcion de lista vacia
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 */
public class EmptyListException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public EmptyListException(String s) {
		super(s);
	}
}
