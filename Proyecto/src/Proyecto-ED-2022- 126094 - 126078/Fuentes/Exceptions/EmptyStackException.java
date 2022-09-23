package Exceptions;
/**
 * Modela la excepcion de pila vacia
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 */
public class EmptyStackException extends Exception
{
	
	private static final long serialVersionUID = 1L;
	
	public EmptyStackException(String msg)
	{
		super(msg);
	}
}
