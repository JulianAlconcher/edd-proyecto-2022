package TDAArbol;

import java.util.Iterator;

import Auxiliares.Position;
import Exceptions.*;

/**
 * Interface Tree
 * Es la version extendida de la interfaz presentada por Goodrich y Tamassia en su cuarta edicion. En esta interfaz se incluyen las operaciones necesarias 
 * para modificar el arbol.
 * @author Catedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computacion, UNS.
 */

public interface Tree<E> extends Iterable<E>
{
	/**
	 * Consulta la cantidad de nodos en el arbol.
	 * @return Cantidad de nodos en el arbol.
	 */
	public int size();
	
	/**
	 * Consulta si el arbol esta vacio.
	 * @return Verdadero si el arbol esta vacio, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el arbol en preorden.
	 * @return Iterador de los elementos almacenados en el arbol.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una coleccion iterable de las posiciones de los nodos del arbol.
	 * @return Coleccion iterable de las posiciones de los nodos del arbol.
	 */
	public Iterable<Position<E>> positions();
	
	/**
	 * Reemplaza el elemento almacenado en la posicion dada por el elemento pasado por parametro. Devuelve el elemento reemplazado.
	 * @param v Posicion de un nodo.
	 * @param e Elemento a reemplazar en la posicion pasada por parametro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException;
	
	/**
	 * Devuelve la posicion de la raiz del arbol.
	 * @return Posicion de la raiz del arbol.
	 * @throws EmptyTreeException si el arbol esta vacio.
	 */
	public Position<E> root() throws EmptyTreeException;
	
	/**
	 * Devuelve la posicion del nodo padre del nodo correspondiente a una posicion dada.
	 * @param v Posicion de un nodo.
	 * @return Posicion del nodo padre del nodo correspondiente a la posicion dada.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 * @throws BoundaryViolationException si la posicion pasada por parametro corresponde a la raiz del �rbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve una coleccion iterable de los hijos del nodo correspondiente a una posicion dada.
	 * @param v Posicion de un nodo.
	 * @return Coleccion iterable de los hijos del nodo correspondiente a la posicion pasada por parametro.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posicion corresponde a un nodo interno.
	 * @param v Posicion de un nodo.
	 * @return Verdadero si la posicion pasada por parametro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posicion pasada por parametro es inv�lida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posicion dada corresponde a un nodo externo.
	 * @param v Posicion de un nodo.
	 * @return Verdadero si la posicion pasada por parametro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n dada corresponde a la raiz del arbol.
	 * @param v Posicion de un nodo.
	 * @return Verdadero, si la posicion pasada por parametro corresponde a la raiz del arbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posicion pasada por parametro es inv�lida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Crea un nodo con rotulo e como raiz del arbol.
	 * @param E Rotulo que se asignara a la raiz del arbol.
	 * @throws InvalidOperationException si el arbol ya tiene un nodo raiz.
	 */
	public void createRoot(E e) throws InvalidOperationException;
	
	/**
	 * Agrega un nodo con rotulo e como primer hijo de un nodo dado.
	 * @param e Rotulo del nuevo nodo.
	 * @param padre Posicion del nodo padre.
	 * @return La posicion del nuevo nodo creado.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida o el arbol esta vacio.
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws	InvalidPositionException;
	
	/**
	 * Agrega un nodo con rotulo e como ultimo hijo de un nodo dado.
	 * @param e Rotulo del nuevo nodo.
	 * @param p Posicion del nodo padre.
	 * @return La posicion del nuevo nodo creado.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida o el arbol esta vacio.
	 */
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException;
	
	/**
	 * Agrega un nodo con rotulo e como hijo de un nodo padre dado. El nuevo nodo se agregara delante de otro nodo tambien dado.
	 * @param e Rotulo del nuevo nodo.
	 * @param p Posicion del nodo padre.
	 * @param rb Posicion del nodo que sera el hermano derecho del nuevo nodo.
	 * @return La posicion del nuevo nodo creado.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida, o el arbol esta vacio, o la posicion rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException;
	
	/**
	 * Agrega un nodo con rotulo e como hijo de un nodo padre dado. El nuevo nodo se agregara a continuacion de otro nodo tambien dado.
	 * @param e Rotulo del nuevo nodo.
	 * @param p Posicion del nodo padre.
	 * @param lb Posicion del nodo que sera el hermano izquierdo del nuevo nodo.
	 * @return La posicion del nuevo nodo creado.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida, o el arbol esta vacio, o la posicion lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posicion dada, si se trata de un nodo externo. 
	 * @param n Posicion del nodo a eliminar.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida o no corresponde a un nodo externo, o el arbol esta vacio.
	 */
	public void removeExternalNode (Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posicion dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raiz del arbol,  unicamente podra ser eliminado si tiene un solo hijo, el cual lo reemplazara.
	 * @param n Posicion del nodo a eliminar.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida o no corresponde a un nodo interno o corresponde a la ra�z (con mas de un hijo), o el �rbol est� vac�o.
	 */
	public void removeInternalNode (Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posicion dada. Si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raiz del arbol,  unicamente podra ser eliminado si tiene un solo hijo, el cual lo reemplazara.
	 * @param n Posicion del nodo a eliminar.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida o corresponde a la raiz (con mas de un hijo), o el arbol esta vacio.
	 */
	public void removeNode (Position<E> p) throws InvalidPositionException;

	
	
	

}