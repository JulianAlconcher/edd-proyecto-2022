package TDAArbol;

import java.util.Iterator;

import Auxiliares.Position;
import Exceptions.*;
import TDALista.*;

/**
 * Clase de arbol que implementa las operaciones de la interfaz Tree
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 * @param <E> Tipo de dato de los elementos a almacenar en el arbol.
 * @version 1.0
 */
public class Arbol<E> implements Tree<E>{
	
	protected TNodo<E> root;
	protected int size;
	
	/**
	 * Inicializa un arbol vacio.
	 */
	public Arbol() {
		root = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> l = new ListaDoblementeEnlazada<E>();
		for( Position<E> p : positions() )
			l.addLast( p.element() );
		
		return l.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l = new ListaDoblementeEnlazada<Position<E>>();
		if(size>0) 
			recPreorden(root, l);
		return l;
	}
	
	/**
	 * Recorrido pre-orden del arbol
	 * @param v nodo por el cual se parte
	 * @param l lista a completar con posiciones
	 */
	private void recPreorden(TNodo<E> v, PositionList<Position<E>> l) {
		l.addLast( v );
		for( TNodo<E> h : v.getHijos()) 
			recPreorden( h, l ); 
	}
	/**
	 * Recorrido post-orden del arbol
	 * @param v nodo por el cual se comienza
	 * @param l lista a completar con posiciones
	 */
	@SuppressWarnings("unused")
	private void rePostorden(TNodo<E> v, PositionList<Position<E>> l) {
		for( TNodo<E> h: v.getHijos()) 
			rePostorden(h,l);
		l.addLast(v);
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		E elem = nodo.element();
		nodo.setElemento(e);
		
		return elem;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(size == 0)
			throw new EmptyTreeException("Arbol vacio");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
	
		TNodo<E> nodo = checkPosition(v);
		if(nodo == root)
			throw new BoundaryViolationException("el nodo es la raiz");
		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> p = checkPosition(v);
		PositionList<Position<E>> lista = new ListaDoblementeEnlazada<Position<E>>();
		for(TNodo<E> n : p.getHijos())
			lista.addLast(n);
		return lista;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return !nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return nodo.getHijos().isEmpty();
	}
	

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return (root == nodo);
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(size != 0){
			throw new InvalidOperationException("Arbol no vacio");
		}
		root = new TNodo<E>(e);
		size++;
		
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,nodo);
		if(size==0)
			throw new InvalidPositionException("arbol vacio");
		nodo.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		if(size == 0){
			throw new InvalidPositionException("Arbol vacio");
		}
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,nodo);
		nodo.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		TNodo<E> hd = checkPosition(rb);
		TNodo<E> nuevo = new TNodo<E>(e,n);
		PositionList<TNodo<E>> hijos = n.getHijos();
		if(size==0)
			throw new InvalidPositionException("arbol vacio");
		boolean encontre = false;
		try {
		Position<TNodo<E>> pp = hijos.first();
		while(pp != null && !encontre) {
			if(hd == pp.element())
				encontre = true;
			else
				if(pp != hijos.last())
					pp=hijos.next(pp);
				else
					pp = null;
		}	
		
		hijos.addBefore(pp, nuevo);
		
		}catch(EmptyListException | BoundaryViolationException f) {f.getMessage();}
		if(!encontre)
			throw new InvalidPositionException("p no es padre de rb");
		size++;
		return nuevo;
	}
	

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		TNodo<E> hi = checkPosition(lb);
		TNodo<E> nuevo = new TNodo<E>(e,n);
		boolean encontre = false;
		if(size==0)
			throw new InvalidPositionException("arbol vacio");
		PositionList<TNodo<E>> hijos = n.getHijos();
		
		try {
			Position<TNodo<E>> aux = hijos.first();
			while( aux!=null && !encontre) {
				if(hi == aux.element())
					encontre = true;
				else
					if( aux != hijos.last())
						aux = hijos.next(aux);
					else
						aux = null;
				
			}
			hijos.addAfter(aux, nuevo);
		}catch(InvalidPositionException | BoundaryViolationException | EmptyListException f) {f.getMessage();}
		if(!encontre)
			throw new InvalidPositionException("p no es padre de lb");
		
		size++;
		return nuevo;
	}
	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		if(size == 0)
			throw new InvalidPositionException("Arbol vacio");
		TNodo<E> n = checkPosition(p);
		if(isInternal(p))
			throw new InvalidPositionException("p no es una hoja");
		if(n == root)
			removeRoot(p);
		else {
				
		TNodo<E> padre = n.getPadre();
		PositionList<TNodo<E>> hijosPadre = padre.getHijos();
		boolean encontre = false;
		
		Position<TNodo<E>> pp = null;
		Iterable<Position<TNodo<E>>> posiciones = hijosPadre.positions();
		Iterator<Position<TNodo<E>>> it = posiciones.iterator();
		
		while(it.hasNext() && !encontre) {
			pp = it.next();
			if(pp.element() == n)
				encontre = true;
		}
		if(!encontre)
			throw new InvalidPositionException("P no aparece en la lista de hijos de su padre: NO ELIMINE!");
		
		hijosPadre.remove(pp);
		n.setElemento(null);
		size--;
		
		if(size==0)
			root = null;
		}
	}
	
	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		if(size == 0)
			throw new InvalidPositionException("El arbol esta vacio");
		if(isExternal(p))
			throw new InvalidPositionException("El nodo no es interno");
		TNodo<E> n = checkPosition(p);
		TNodo<E> padre = n.getPadre();
		
		if(isRoot(p))
			removeRoot(p);
		else {
			PositionList<TNodo<E>> hPadre = padre.getHijos();
			PositionList<TNodo<E>> hN = n.getHijos();
			try {
			 Position<TNodo<E>> posDeN;
			 Position<TNodo<E>> cursor = hPadre.first();
				
				while(cursor.element() != n && cursor !=null) {
					if(cursor == hPadre.last())
						cursor = null;
					else 
						cursor = hPadre.next(cursor);
				}
				
				if(cursor != null)
					posDeN = cursor;
				
				else throw new InvalidPositionException("La estructura no corresponde a un arbol");
				
				while(!hN.isEmpty()) {
					Position<TNodo<E>> hijoN = hN.first();
					hPadre.addBefore(posDeN, hijoN.element());
					hijoN.element().setPadre(padre);
					hN.remove(hijoN);
				}
				hPadre.remove(posDeN);
			
			}catch(EmptyListException | BoundaryViolationException e) {e.getMessage();}
		}
		size--;
			
	}
	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		if(isRoot(p))
			removeRoot(p);
		else 
			if(isInternal(p))
				removeInternalNode(p);
		else 
			if(isExternal(p))
				removeExternalNode(p);
	}
	/**
	 * Metodo que remueve la raiz del arbol
	 * @param p posicion a remover
	 * @throws InvalidPositionException si no es posible eliminar la raiz
	 */
	protected void removeRoot(Position<E> p) throws InvalidPositionException{
		if(size == 0)
			throw new InvalidPositionException("no se puede eliminar un arbol vacio");
		try {
			if(root.getHijos().size() == 1) 
			{
				Position<TNodo<E>> rN = root.getHijos().first();
				rN.element().setPadre(null);
				TNodo<E> aux = rN.element();
				root.getHijos().remove(rN);
				root = aux;
				size--;
			}
			else if(size == 1) 
			{
				root = null;
				size--;
			}
			else throw new InvalidPositionException("Solo se puede eliminar la raiz: si es el unico elemento o si tiene un solo hijo");
		
		}catch(EmptyListException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Metodo privado que chequea que la posicion pasada por parametro sea valida.
	 * @param p posicion a chequear y castear
	 * @return	p retorna el casteo a TNodo de la posicion
	 * @throws InvalidPositionException
	 */
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try{
			if(p == null){
				throw new InvalidPositionException("Posicion nula");
			}
			if(p.element() == null){
				throw new InvalidPositionException("Posicion eliminada previamente");
			}
			return (TNodo<E>) p;
		}catch(ClassCastException e){
			throw new InvalidPositionException("La posicion no es de esta lista");
		}
	}

	
}
