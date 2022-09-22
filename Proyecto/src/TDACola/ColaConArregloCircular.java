package TDACola;

import Exceptions.EmptyQueueException;

/**
 * Implementa los datos y operaciones aplicables sobre una cola con arreglo circular
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 * @param <E> El tipo de elemento que va a ser almacenado en la cola.
 * @version 1.0
 */
public class ColaConArregloCircular <E> implements Queue <E>
{

	protected E[] q;

    protected int f;

    protected int r;
   
    /** 
     * Crea una cola vacia. 
     */
	@SuppressWarnings("unchecked")
	public ColaConArregloCircular()
    {
    	q = (E[]) new Object[10];
    	f = 0;
    	r = 0;
    }
    
    @Override
	public int size() 
	{
		return ((q.length - f + r) % q.length);
	}

    @Override
	public boolean isEmpty() 
	{
		return f == r;
	}
    
    @Override
	public E front() throws EmptyQueueException 
	{
		if(f==r) {
			throw new EmptyQueueException("Cola vac�a.");
		}
		else {
			return q[f];
		}
	}
    
    @Override
	public void enqueue(E element) 
	{
		if(size() == q.length-1) 
		{
			E[] aux = copiar(f);
			r = size();
			f = 0;
			q = aux;
		}
			q[r] = element;
			r = (r + 1) % q.length;
	}
	
	/**
	 * Metodo privado que duplica el tamanio del arreglo de la cola circular.
	 * @param start Posicion en el arreglo, a partir del cual se realizara la copia de los elementos.
	 * @return Arreglo de elementos con el doble de tamanio que el que se tenia anteriormente.
	 */
	@SuppressWarnings("unchecked")
	private E[] copiar(int start) {
		int j = 0;
		E[] aux = (E[]) new Object[q.length*2];
		
		for(int i = f;!(start == r);i++) {
			start = i % q.length;
			aux[j++] = q[start];
		}
		return aux;
	}
	@Override
	public E dequeue() throws EmptyQueueException 
	{
		if( isEmpty()) {
			throw new EmptyQueueException("Cola vac�a.");
		}
		else {
			E aux = q[f];
			q[f] = null;
			f = (f + 1) % (q.length);
			return aux;
		} 
	}
	
}
 

