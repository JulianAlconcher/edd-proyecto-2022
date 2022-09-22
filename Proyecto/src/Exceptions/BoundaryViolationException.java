package Exceptions;
/**
 * Modela la excepcion de violacion de Limites
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 */
public class BoundaryViolationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public BoundaryViolationException (String msg)
	{
		super(msg);
	}
}
