package TDALista;

import Auxiliares.Position;

/**
 * Clase que modela las operaciones sobre un nodo con referencia a su previo y su siguiente.
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 * @version 1.0
 * @param <E> Tipo de dato de los elementos a almacenar en el nodo.
 */
public class DNodo <E> implements Position <E> {

	protected E element;

	protected DNodo <E> prev;

	protected DNodo <E> next;
	/**
	 * Crea un nuevo Nodo con rotulo "item", especificando su anterior y posterior.
	 * @param item Es el elemento que va a guardar el nodo.
	 * @param n nodo siguiente.
	 * @param p nodo anterior.
	 */
	public DNodo(E item, DNodo<E> n, DNodo<E> p) {
		element=item;
		next=n;
		prev=p;
	}
	/**
	 * Crea un nuevo Nodo especificando su rotulo.
	 * @param item Es el elemento que va a guardar el nodo.
	 */
	public DNodo(E item){
		this(item,null,null);
	}
	/**
	 * Cambia el elemento actual del nodo por el espesificado en el parametro.
	 * @param item El elemento nuevo del nodo.
	 */
	public void setElement(E item){
		element = item;
	}	
	/**
	 * Cambia el siguiente del nodo por el espesificado en el parametro.
	 * @param siguiente El nuevo nodo siguiente. 
	 */
	public void setNext(DNodo<E> siguiente){
		next= siguiente;
	}	
	/**
	 * Cambia el anterior del nodo por el espesificado en el parametro.
	 * @param anterior El nuevo nodo anterior.
	 */
	public void setPrev(DNodo<E> anterior){
		prev = anterior;
	}	
	/**
	 * Retorna el rotulo del nodo.
	 */
	public E element() {
		return element;
	}	
	/**
	 * Consulta cual es el siguiente del nodo.
	 * @return El siguiente del nodo.
	 */
	public DNodo<E> getNext(){
		return next;
	}
	/**
	 * Consulta cual es el anterior del nodo.
	 * @return El anterior del nodo.
	 */
	public DNodo<E> getPrev(){
		return prev;
	}
}
