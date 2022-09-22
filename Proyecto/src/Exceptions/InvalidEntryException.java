package Exceptions;
/**
 * Modela la excepcion de entrada invalida
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 */
public class InvalidEntryException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidEntryException(String msg){
		super(msg);
	}
}
