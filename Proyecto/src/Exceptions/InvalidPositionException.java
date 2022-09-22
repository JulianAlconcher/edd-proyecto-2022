package Exceptions;
/**
 * Modela la excepcion que advierte que se uso una posicion que no esta en la lista
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 */
public class InvalidPositionException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
