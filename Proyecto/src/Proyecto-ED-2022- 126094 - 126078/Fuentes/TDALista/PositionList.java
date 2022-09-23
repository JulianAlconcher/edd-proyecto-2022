package TDALista;

import java.util.Iterator;

import Auxiliares.Position;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;



/**
 * Interface PositionList - Estructura de Datos (DCIC-UNS).
 * Define los datos y operaciones aplicables sobre una lista.
 * @author Estructuras de Datos, DCIC-UNS.
 * @version 1.0 - Maria Lujan Ganuza (2013-2018)
 * @version 2.0 - Federico Joaquin (2019-2020) 
 * @param <E> Tipo de dato de los elementos a almacenar en la lista.
 */
public interface PositionList<E> extends Iterable<E>{
	
	/**
	 * Consulta la cantidad de elementos de la lista.
	 * @return Cantidad de elementos de la lista.
	 */
	public int size();
	
	/**
	 * Consulta si la lista esta vacia.
	 * @return Verdadero si la lista esta vacia, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve la posicion del primer elemento de la lista. 
	 * @return Posicion del primer elemento de la lista.
	 * @throws EmptyListException si la lista esta vacia.
	 */
	public Position<E> first() throws EmptyListException;
	
	/**
	 * Devuelve la posicion del ultimo elemento de la lista. 
	 * @return Posicion del ultimo elemento de la lista.
	 * @throws EmptyListException si la lista esta vacia.
	 * 
	 */
	public Position<E> last() throws EmptyListException;
	
	/**
	 * Devuelve la posicion del elemento siguiente a la posicion pasada por parametro.
	 * @param p Posicion a obtener su elemento siguiente.
	 * @return Posicion del elemento siguiente a la posicion pasada por parametro.
	 * @throws InvalidPositionException si el posicion pasada por parametro es invalida o la lista esta vacia.
	 * @throws BoundaryViolationException si la posicion pasada por parametro corresponde al ultimo elemento de la lista.
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve la posicion del elemento anterior a la posicion pasada por parametro.
	 * @param p Posicion a obtener su elemento anterior.
	 * @return Posicion del elemento anterior a la posicion pasada por parametro.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida o la lista esta vacia.
	 * @throws BoundaryViolationException si la posicion pasada por parametro corresponde al primer elemento de la lista.
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Inserta un elemento al principio de la lista.
	 * @param element Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E element);
	
	/**
	 * Inserta un elemento al final de la lista.
	 * @param element Elemento a insertar al final de la lista.
	 */
	public void addLast(E element);
	
	/**
	 * Inserta un elemento luego de la posicion pasada por paramatro.
	 * @param p Posicion en cuya posicion siguiente se insertara el elemento pasado por parametro.
	 * @param element Elemento a insertar luego de la posicion pasada como parametro.
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia.
	 */
	public void addAfter(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Inserta un elemento antes de la posicion pasada como parametro.
	 * @param p Posicion en cuya posicion anterior se insertara el elemento pasado por parametro. 
	 * @param element Elemento a insertar antes de la posicion pasada como parametro.
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia.
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Remueve el elemento que se encuentra en la posicion pasada por parametro.
	 * @param p Posicion del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia.
	 */	
	public E remove(Position<E> p) throws InvalidPositionException;

	/**
	 * Establece el elemento en la posicion pasados por parametro. Reemplaza el elemento que se encontraba anteriormente en esa posicion y devuelve el elemento anterior.
	 * @param p Posicion a establecer el elemento pasado por parametro.
	 * @param element Elemento a establecer en la posicion pasada por parametro.
	 * @return Elemento anterior.
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia.	 
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una coleccion iterable de posiciones.
	 * @return Una coleccion iterable de posiciones.
	 */
	public Iterable<Position<E>> positions();	
}