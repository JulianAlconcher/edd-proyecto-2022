package TDALista;

import java.util.Iterator;

import Auxiliares.Position;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

/**
 * Clase Lista Doblemente Enlazada que implementa los metodos de la interfaz PositionList
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 * @version 1.0
 * @param E  Tipo de dato de los elementos a almacenar en la lista.
 */
public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	
	protected DNodo<E> head;
	protected DNodo<E> tail;
	protected int tamanio;
	/**
	 * Inicializa una lista vacia.
	 */
	public ListaDoblementeEnlazada() {
		head = new DNodo<E>(null);
		tail = new DNodo<E>(null);
		head.setPrev(null);
		head.setNext(tail);
		tail.setPrev(head);
		tail.setNext(null);
		tamanio=0;
		
	}

	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return (tamanio==0);
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(tamanio==0)
			throw new EmptyListException("La lista es vacia");
		return head.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(tamanio==0)
			throw new EmptyListException("La lista es vacia");
		return tail.getPrev();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if(n.getNext()==tail) 
			throw new BoundaryViolationException("No se puede pedir la posicion siguiente de la ultima posicion");
		return n.getNext();
		
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if(n.getPrev()==head)
			throw new BoundaryViolationException("No se puede pedir la posicion anterior de la primera posicion");
		return n.getPrev();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> nuevo = new DNodo<E>(element);
		(head.getNext()).setPrev(nuevo);
		nuevo.setNext(head.getNext());
		nuevo.setPrev(head);
		head.setNext(nuevo);
		tamanio++;
		
	}

	@Override
	public void addLast(E element) {
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setPrev(tail.getPrev());
		nuevo.setNext(tail);
		(tail.getPrev()).setNext(nuevo);
		tail.setPrev(nuevo);
		tamanio++;
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setNext(n.getNext());
		nuevo.setPrev(n);
		nuevo.getNext().setPrev(nuevo);
		n.setNext(nuevo);
		tamanio++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setPrev(n.getPrev());
		nuevo.setNext(n);
		n.getPrev().setNext(nuevo);
		n.setPrev(nuevo);
		tamanio++;
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if(tamanio==0)
			throw new InvalidPositionException("no se puede remover una posicion de una lista que es vacia");
		DNodo<E> n = checkPosition(p);
		E aux = n.element();
		(n.getPrev()).setNext((n.getNext()));
		(n.getNext()).setPrev((n.getPrev()));
		
		
		n.setElement(null);
		n.setNext(null);
		n.setPrev(null);
		
		
		tamanio--;
		
		return aux;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if(tamanio==0)
			throw new InvalidPositionException("Posicion invalida");
		DNodo<E> n = checkPosition(p);
		E aux = n.element();//Guardo el elemento anterior para devolverlo.
		n.setElement(element);//seteo en la posicion p el elemento "element".
		return aux;//Devuelvo el elemento que antes estaba en esa posicion.
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override	
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> p = new ListaDoblementeEnlazada<Position<E>>();
		if(!isEmpty()) {
			try {
				Position<E> pos = head.getNext();
				while( pos != tail.getPrev() ) 
				{
					p.addLast(pos);
					pos = next(pos);
				}
				p.addLast(pos); 
				}
			catch(BoundaryViolationException e){
				System.out.println("Violacion de limites");
				return null;
			}
			catch(InvalidPositionException e){
				System.out.println("Posicion invalida");
				return null;
			}
		} 	
		return p;
	}
	/**
	 * Metodo Privado que chequea que la posicion pasada por parametro sea valida.
	 * @return Posicion p casteada a nodo.
	 * @exception InvalidPositionException si p no es un nodo de la lista.
	 * @exception InvalidPositionException si p es un nodo centinela.
	 */
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if(p==null)
				throw new InvalidPositionException("p es una posicion invalida");
			if(p==head || p==tail)
				throw new InvalidPositionException("p es nodo centinela");
			return (DNodo<E>) p;
		}catch(ClassCastException e){throw new InvalidPositionException("p no es un nodo de la lista");}
			
		}
	

}