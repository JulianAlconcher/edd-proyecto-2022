package TDACola;

import Exceptions.EmptyQueueException;

/**
 * Interface Queue - Estructura de Datos (DCIC-UNS).
 * Define los datos y operaciones aplicables sobre una cola.
 * @author Estructuras de Datos, DCIC-UNS.
 * @version 1.0 - Maria Lujan Ganuza (2013-2018)
 * @version 2.0 - Federico Joaquin (2019-2020) 
 * @param <E> Tipo de dato a almacenar en la cola.
 */
public interface Queue <E>{
	
	/**
	 * Devuelve la cantidad de elementos en la cola.
	 * @return Cantidad de elementos en la cola.
	 */
	public int size();
	
	/**
	 * Consulta si la cola esta vacia.
	 * @return Verdadero si la cola esta vacia, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Inspecciona el elemento que se encuentra en el frente de la cola.
	 * @return Elemento que se encuentra en el frente de la cola.
	 * @throws EmptyQueueException si la cola esta vacia.
	 */
	public E front() throws EmptyQueueException;
	
	/**
	 * Inserta un elemento en el fondo de la cola.
	 * @param element Nuevo elemento a insertar.
	 */
	public void enqueue(E element);
	
	/**
	 * Remueve el elemento en el frente de la cola.
	 * @return Elemento removido.
	 * @throws EmptyQueueException si la cola esta vacia.
	 */
	public E dequeue() throws EmptyQueueException;
	
}