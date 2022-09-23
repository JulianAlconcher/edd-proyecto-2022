package Programa;


import Auxiliares.Position;
import Exceptions.*;
import TDAArbol.*;
import TDACola.ColaConArregloCircular;
import TDACola.Queue;
import TDADiccionario.DiccionarioConHashAbierto;
import TDADiccionario.Dictionary;
import TDADiccionario.Entry;
import TDALista.*;
import TDAPila.PilaConNodosEnlazados;
import TDAPila.Stack;

/**
 * Clase Logica. Encargada de manejar los metodos principales
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 * @version 1.1
 */
public class Logica {

	protected Tree<Pair<Character,Integer>> mainTree;
	
	/**
	 * Inicializa un arbol con raiz "tag"
	 * @param tag, rotulo de la raiz del arbol
	 */
	public Logica(Character tag) {
		mainTree = new Arbol<Pair<Character,Integer>>();
		Pair<Character,Integer> p = new Pair<Character,Integer>(tag);
		try {
			mainTree.createRoot(p);
			p.setGrade(0);
		} catch (InvalidOperationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Agrega un nodo con rotulo "r" y padre "parent".
	 * @param parent rotulo del padre del nodo a ingresar
	 * @param r rotulo del nodo a ingresar.
	 * @return TRUE si encuentra al padre, FALSE en caso contrario.
	 * @throws InvalidOperationException si el rotulo del padre es igual al rotulo del nodo a insertar.
	 */
	public boolean addNode(Character parent, Character r) throws InvalidOperationException {
		boolean encontre = false;
		for(Position<Pair<Character,Integer>> pos : mainTree.positions()) {
			if(pos.element().getTag().equals(parent)) {
				encontre = true;
				Pair<Character,Integer> nuevo = new Pair<Character,Integer>(r);
				nuevo.setGrade(0);
				try {
					if(parent != r) {
						mainTree.addLastChild(pos, nuevo);
						pos.element().setGrade(pos.element().getGrade()+1);}
					else
						throw new InvalidOperationException("El padre no puede ser el mismo nodo a insertar");
				} catch (InvalidPositionException e) {
					e.printStackTrace();
				}
			}
		}
		return encontre;
	}
	/**
	 * Elimina un nodo del arbol.
	 * Si el nodo eliminado tiene hijos, incrementa el grado del padre del nodo eliminado acorde a la cantidad de hijos que tiene el nodo eliminado.
	 * @param r rotulo del nodo a remover
	 * @return Retorna true si encuentra el nodo y lo elimina. False en caso contrario.
	 * @throws InvalidPositionException 
	 */
	public boolean removeNode(Character r) throws InvalidPositionException {
		boolean encontre = false;
		try {
		Position<Pair<Character,Integer>> pos = getPosition(r);
		if(pos==null) 
			throw new InvalidPositionException("No existe el nodo");
		if(pos.equals(mainTree.root())) {
			encontre = true;
			mainTree.removeNode(pos);}
		else{
			mainTree.parent(pos).element().setGrade(mainTree.parent(pos).element().getGrade()-1);
			
			if(mainTree.isInternal(pos))
				mainTree.parent(pos).element().setGrade(mainTree.parent(pos).element().getGrade()+amountToAdd(pos));
			
			mainTree.removeNode(pos);
			encontre = true;
			}
		} catch (InvalidPositionException e) {throw new InvalidPositionException(e.getMessage());
		} catch (BoundaryViolationException e) {e.printStackTrace();
		} catch (EmptyTreeException e) {e.printStackTrace();}
		
		return encontre;
	}
	/**
	 * Retorna la cantidad de hijos que tiene una posicion.
	 * @param p posicion a calcular sus hijos.
	 * @return cantidad de hijos de la posicion pasada por parametro.
	 * @throws InvalidPositionException si la posicion no tiene hijos.
	 */
	@SuppressWarnings("unused")
	public int amountToAdd(Position<Pair<Character,Integer>> p) throws InvalidPositionException {
		int contador = 0;
		for(Position<Pair<Character,Integer>> pos : mainTree.children(p))
			contador++;
		
		return contador;
	}
	/**
	 * Retorna un entero que almacena el grado del arbol.
	 * @return grado del arbol
	 */
	public int treeGrade() {
		int max = 0;
		for(Position<Pair<Character,Integer>> pos : mainTree.positions()) {
			if(pos.element().getGrade() > max) 
				max = pos.element().getGrade();
		}
		return max;
	}
	/**
	 * Retorna un string con el camino del arbol desde la raiz hasta llegar al rotulo "c".
	 * @param c rotulo del nodo a obtener el camino.
	 * @return String con el camino desde la raiz hasta el nodo "c".
	 * @throws InvalidOperationException si la posicion obtenida es nula, es decir que el rotulo no existe.
	 */
	public String getPath(Character c) throws InvalidOperationException {
		Stack<Pair<Character,Integer>> pila = new PilaConNodosEnlazados<Pair<Character,Integer>>();
		String toReturn ="";
		Position<Pair<Character,Integer>> pos =  getPosition(c);
		if(getPosition(c)!=null)
		{
			try {
			while(pos != mainTree.root()) {
				pila.push(pos.element());
				pos = mainTree.parent(pos);
			}
			pila.push(mainTree.root().element());
			while(!pila.isEmpty()) {
				toReturn = toReturn + pila.pop().toStringPair();
			}
			}catch (EmptyStackException e) {e.getMessage();
			}catch (EmptyTreeException e) {e.getMessage();
			}catch (InvalidPositionException e) {e.getMessage();
			}catch (BoundaryViolationException e) {e.getMessage();}
		}
		if(getPosition(c)==null)
			throw new InvalidOperationException("El nodo con el rotulo ingresado no existe");
		return toReturn;
		
	}
	/**
	 * Retorna el orden del nodo. 
	 * @param posicion a obtener su orden.
	 * @return numero entero que representa la cantidad de ancestro del nodo.
	 */
	private int getOrder(Position<Pair<Character,Integer>> p) {
		int contador = 0;
		try {
		while(mainTree.parent(p) != null) {
			contador++;
			p = mainTree.parent(p);
		}
		} catch (InvalidPositionException | BoundaryViolationException e) {e.getMessage();}
		
		return contador;
	}
	
	/**
	 * Busca y retorna la posicion del rotulo "c", si no lo encuentra devuelve null.
	 * @param c rotulo del caracter a obtener la posicion
	 * @return posicion de c, null si no encuentra.
	 */
	private Position<Pair<Character,Integer>> getPosition(Character c){
		Position<Pair<Character,Integer>> toReturn = null;
		for(Position<Pair<Character,Integer>> pos : mainTree.positions()) 
			if(pos.element().getTag().equals(c)) 
				toReturn = pos;
		
		return toReturn;
	}
	
	/**
	 * Remueve todos los nodos del arbol con grado k.
	 * @param k orden de los nodos a eliminar.
	 * @return un string de todos los nodos que fueron eliminados.
	 * @throws InvalidPositionException 
	 * @throws InvalidKeyException 
	 * @throws InvalidOperationException si no se encontro ningun nodo con el grado ingresado.
	 */
	public String removeOrdenK(int k) throws InvalidPositionException, InvalidKeyException, InvalidOperationException {
		String toReturn = "" ;
		int cumplen = 0;
		for(Position<Pair<Character,Integer>> pos : mainTree.positions()) {
			Integer gradoK = k;
			if(mainTree.isRoot(pos) && k>1 && pos.element().getGrade() == gradoK) {} 
			
			else if(pos.element().getGrade()==(gradoK)) {
				cumplen++;
				toReturn = toReturn + pos.element().toStringPair() + ",";
				removeNode(pos.element().getTag());
			}
		}
		if(cumplen == 0)
			throw new InvalidOperationException("No es posible eliminar un nodo con grado: ");
		return toReturn;
	}
	/**
	 * Retorna un string que muestra los grados de los nodos.
	 * @return string que muestra los grados con los nodos que tienen ese grado.
	 */
	public String printGrades() {
		Dictionary<Integer,Character> dic = new DiccionarioConHashAbierto<Integer,Character>();
		String toReturn ="";
		try {
		for(Position<Pair<Character,Integer>> pos : mainTree.positions()) {
			dic.insert(pos.element().getGrade(), pos.element().getTag());
		}
			toReturn = toReturn +("");
			for(int i=0; i<=treeGrade(); i++) {
				Iterable<Entry<Integer,Character>> gradesIt = dic.findAll(i);
				toReturn = toReturn + ("Grado " +  i + ": ");
				for(Entry<Integer, Character> pos : gradesIt) {
					toReturn = toReturn + (" [" + pos.getValue() + "] ");
				}
				toReturn = toReturn + "\n";
			}
		} catch (InvalidKeyException e1) {e1.getMessage();}
		
		return toReturn;
	}
	/**
	 * Retorna un string que representa el arbol.
	 * @return string del arbol
	 */
	public String printTree() {
			String toReturn = "";
			for(Position<Pair<Character,Integer>> pos : mainTree.positions() ) {
				for(int i=1; i<=getOrder(pos); i++) 
					toReturn = toReturn + " |     ";
				toReturn = toReturn + ("├─" + " ");
				toReturn = toReturn + (pos.element().toStringPair() );
				toReturn = toReturn + ("\n" );
			}
			return toReturn;
	}
	
	/**
	 * Retorna el tamaño del arbol.
	 * @return entero con la cantidad de nodos del arbol
	 */
	public int size() {
		return mainTree.size();
	}
	
	/**
	 * Retorna una lista con los elementos organizados por un preorden.
	 * @return lista ordenada con preorden de los elementos del arbol.
	 * @throws EmptyTreeException si el arbol es vacio.
	 */
	public PositionList<Pair<Character,Integer>> preOrden(){
		PositionList<Pair<Character,Integer>> l = new ListaDoblementeEnlazada<Pair<Character,Integer>>();
		if(mainTree.size()>0)
			try {
				recorridoPreorden(mainTree.root(), l);
			} catch (EmptyTreeException e) {e.printStackTrace();}
		return l;
	}
	
	/**
	 * Recorrido pre-orden del arbol
	 * @param v posicion de la cual se parte de pares de caracter,enteros
	 * @param l lista de pares de caracteres,enteros a completar.
	 */
	private void recorridoPreorden(Position<Pair<Character,Integer>> v, PositionList<Pair<Character,Integer>> l) {
		l.addLast( v.element() ); 
		try {
			for(Position<Pair<Character,Integer>> h : mainTree.children(v)) 
				recorridoPreorden( h, l );
		} catch (InvalidPositionException e) {e.printStackTrace();} 
	}
	
	/**
	 * Retorna una lista con los elementos organizados por un postorden.
	 * @return lista ordenada con postorden de los elementos del arbol
	 * @throws EmptyTreeException si el arbol es vacio
	 */
	public PositionList<Pair<Character,Integer>> postOrden() {
		PositionList<Pair<Character,Integer>> l = new ListaDoblementeEnlazada<Pair<Character,Integer>>();
		if(mainTree.size()>0)
			try {
				recorridoPostorden(mainTree.root(), l);
			} catch (EmptyTreeException e) {e.printStackTrace();}
		return l;
	}
	
	/**
	 * Recorrido post-orden del arbol
	 * @param v posicion por la cual se comienza
	 * @param l lista para agregar los elementos
	 */
	private void recorridoPostorden(Position<Pair<Character,Integer>> v, PositionList<Pair<Character,Integer>> l) {
		try {
			for(Position<Pair<Character,Integer>> h : mainTree.children(v)) 
				recorridoPostorden( h, l );
		} catch (InvalidPositionException e) {e.printStackTrace();}
		
		l.addLast( v.element() ); 
	}
	
	/**
	 * Retorna una lista con los elementos organizados por niveles.
	 * @return lista ordenada por niveles.
	 */
	public PositionList<Pair<Character,Integer>> recorridoPorNiveles(){
		Queue<Position<Pair<Character,Integer>>> cola = new ColaConArregloCircular<Position<Pair<Character,Integer>>>();
		PositionList<Pair<Character,Integer>> l = new ListaDoblementeEnlazada<Pair<Character,Integer>>();
		try {
			Position<Pair<Character,Integer>> r = mainTree.root();
			cola.enqueue(r);
			while(!cola.isEmpty()) {
				Position<Pair<Character,Integer>> aux = cola.dequeue();
				l.addLast(aux.element());
				if(mainTree.children(aux) != null)
					for(Position<Pair<Character,Integer>> pos : mainTree.children(aux))
						cola.enqueue(pos);
			}
			
		}catch(EmptyTreeException e1) {e1.getMessage();
		}catch(EmptyQueueException e2) {e2.getMessage();
		}catch(InvalidPositionException e3) {e3.getMessage();}
		
		return l;
		
	}
	
	/**
	 * Retorna un dato de tipo string con los elementos de la lista pasada por parametro.
	 * @param i lista a imprimir
	 * @return string con los elementos de la lista
	 */
	public String stringizer(PositionList<Pair<Character, Integer>> i) {
		String toReturn = "";
		for(Position<Pair<Character,Integer>> pos : i.positions()) {
			toReturn = toReturn + "[" + pos.element().getTag()+ "]" + " , ";
		}
		return toReturn;
	}
	
}

