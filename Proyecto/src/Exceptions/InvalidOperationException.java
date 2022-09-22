package Exceptions;
/**
 * Modela la excepcion que advierte que se uso una operacion que no se puede sobre esa posicion.
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 */
public class InvalidOperationException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidOperationException(String msg){
		super(msg);
	}
}
