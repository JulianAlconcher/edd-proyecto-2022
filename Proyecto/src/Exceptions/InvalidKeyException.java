package Exceptions;
/**
 * Modela la excepcion de clave invalida
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 */
public class InvalidKeyException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidKeyException(String msg){
		super(msg);
	}
}
