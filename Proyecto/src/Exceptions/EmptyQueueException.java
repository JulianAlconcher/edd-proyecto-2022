package Exceptions;
/**
 *  Modela la excepcion de cola vacia
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 */
public class EmptyQueueException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EmptyQueueException(String s) {
		super(s);
	}
}
