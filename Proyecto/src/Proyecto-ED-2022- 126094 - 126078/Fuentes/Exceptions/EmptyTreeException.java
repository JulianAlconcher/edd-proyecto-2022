package Exceptions;

/**
 *  Modela la excepcion que advierte que el arbol esta vacio.
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 */
public class EmptyTreeException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public EmptyTreeException(String msg){
		super(msg);
	}
}