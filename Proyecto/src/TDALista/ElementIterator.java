package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Auxiliares.Position;
import Exceptions.*;

/**
 * Implementa los metodos de la interfaz Iterator.
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 * @version 1.0
 * @param <E>
 */
public class ElementIterator <E> implements Iterator <E>{
	
	protected PositionList<E> lista;
	
	protected Position<E> cursor;
	/**
	 * Crea un nuevo iterador con la lista pasada por parametro.
	 * @param l La lista a la cual se le quiere generar el iterador.
	 */
	public ElementIterator(PositionList <E> l) {
		lista=l;
		try {
			if(lista.isEmpty()) {
				cursor=null;
			}else {
				cursor=lista.first();
			}
		}catch (EmptyListException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Consulta si hay un siguiente elemento en el iterador.
	 * @return Verdadero si hay un siguiente, falso de lo contrario.
	 */
	public boolean hasNext() {
		 return cursor != null;
	}
	/**
	 * Busca cual es el siguiente elemento en el iterador
	 * @return El siguiente elemento
	 * @throws NoSuchElementException Si no hay un siguiente en el iterador.
	 */
	public E next() throws NoSuchElementException {		
		if(cursor==null) {
			throw new NoSuchElementException ("Error: No hay siguiente");
		}
		E aux=cursor.element();	
		try {
			cursor = (cursor == lista.last()) ? null : lista.next(cursor);
		} catch (EmptyListException e) {
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
		return aux;
	}
}
