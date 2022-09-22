 package TDAPila;

 /**
  * Implementa los metodos de la clase Nodo:
  * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
  * @version 1.0
  * @param <E> Tipo de dato de los elementos a almacenar en el nodo.
  */
public class Nodo<E> 
{
	private E elemento;
	private Nodo<E> siguiente;
	
	
	/**
	 * Inicializa el nodo con el elemento item y su siguiente.
	 * Guarda el elemento pasado por parámetro y tiene como siguiente el nodo pasado por parametro
	 * @param item rotulo del elemento
	 * @param sig nodo siguiente 
	 */
	public Nodo( E item, Nodo<E> sig )
	{ 
		elemento=item; 
		siguiente=sig; 
    }
	/**
	 * Inicializa el nodo con el elemento item.
	 * @param item
	 */
	public Nodo( E item )
	{ 
		this(item,null); 
	}
	
	/**
	 * Setea el elemento pasado por parametro.
	 * @param elemento
	 */
	public void setElemento( E elemento )
	{ 
		this.elemento = elemento; 
	}
	/**
	 * Setea el siguiente 
	 * @param siguiente es el nodo siguiente a insertar
	 */
	public void setSiguiente( Nodo<E> siguiente )
	{ 
		this.siguiente = siguiente; 
	}
	
	/**
	 * Retorna el elemento.
	 * @return el parametro elemento
	 */
	public E getElemento() 
	{ 
		return elemento; 
	}
	/**
	 * Retorna el siguiente.
	 * @return el siguiente
	 */
	public Nodo<E> getSiguiente() 
	{ 
		return siguiente; 
	}
	
}
