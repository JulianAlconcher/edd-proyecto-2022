package TDAPila;

import Exceptions.EmptyStackException;

/**
 * Interface Stack
 * @author Catedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computacion, UNS.
 */

public interface Stack<E> {

	/**
	 * Consulta la cantidad de elementos de la pila.
	 * @return Cantidad de elementos de la pila.
	 */
	public int size();

	/**
	 * Consulta si la pila esta vacia.
	 * @return Verdadero si la pila esta vacia, falso en caso contrario.
	 */
	public boolean isEmpty();

	/**
	 * Examina el elemento que se encuentra en el tope de la pila.
	 * @return Elemento que se encuentra en el tope de la pila.
	 * @throws EmptyStackException si la pila esta vacia. 
	 */
	public E top()throws EmptyStackException;

	/**
	 * Inserta un elemento en el tope de la pila.
	 * @param element Elemento a insertar.
	 */
	public void push(E element);

	/**
	 * Remueve el elemento que se encuentra en el tope de la pila.
	 * @return Elemento removido.
	 * @throws EmptyStackException si la pila esta vacia. 
	 */
	public E pop() throws EmptyStackException;
}