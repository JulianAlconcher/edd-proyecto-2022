package TDADiccionario;
import Auxiliares.Position;
import Exceptions.*;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

/**
 * Implementa los datos y operaciones aplicables sobre un diccionario.
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 * @param <K> Tipo de dato de las claves a almacenar en el diccionario.
 * @param <V> Tipo de dato de los valores a almacenar en el diccionario.
 * @version 1.0
 */
public class DiccionarioConHashAbierto<K,V> implements Dictionary<K,V> {
	protected PositionList<Entry<K,V>> [] arreglo;
	protected int cantEntradas;
	protected int cubetas;
	protected static final float factorCarga = 0.9f;
	
	
	/**
	 * Inicializa un diccionario vacio.
	 */
	@SuppressWarnings("unchecked")
	public DiccionarioConHashAbierto() {
		cubetas = 13;
		cantEntradas = 0;
		arreglo = (PositionList<Entry<K,V>> []) new PositionList[cubetas];
		for(int i=0; i<arreglo.length; i++)
			arreglo[i] = new ListaDoblementeEnlazada<Entry<K,V>>();
	}

	/**
	 * Metodo privado que computa la posicion del bucket en el que se va a encontrar la entrada con clave
	 * @param clave clave de la entrada a buscar
	 * @return cubeta a insertar
	 */
	private int hashThisKey(K clave) {
		return Math.abs(clave.hashCode() % cubetas);
	}
	@Override
	public int size() {
		return cantEntradas;
	}

	@Override
	public boolean isEmpty() {
		return cantEntradas == 0;
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("La clave es invalida, find");
		int claveCubeta = this.hashThisKey(key);
		
		for(Position<Entry<K,V>> pos : arreglo[claveCubeta].positions()) {
			if(pos.element().getKey().equals(key))
					return (pos.element());}
		
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("La clave es invalida, FindAll");
		PositionList<Entry<K,V>> toReturn = new ListaDoblementeEnlazada<Entry<K,V>>();
		int claveCubeta = this.hashThisKey(key);
		for(Position<Entry<K,V>> pos : arreglo[claveCubeta].positions()) {
			if(pos.element().getKey().equals(key))
				toReturn.addLast(pos.element());
		}
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("la clave es invalida, insert");
		int claveCubeta = this.hashThisKey(key);
		@SuppressWarnings("rawtypes")
		Entry<K,V> toReturn = new Entrada(key,value);
		arreglo[claveCubeta].addLast(toReturn);
		cantEntradas++;
		if(cantEntradas/cubetas > factorCarga)
			reHash();
		return toReturn;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e==null)
			throw new InvalidEntryException("La entrada es invalida, remove");
		
		int claveCubeta = this.hashThisKey(e.getKey());
		
		try {
			for(Position<Entry<K,V>> pos : arreglo[claveCubeta].positions()) {
				if(pos.element() == e) {
					Entry<K,V> toReturn = pos.element();
					arreglo[claveCubeta].remove(pos);
					cantEntradas--;
					return toReturn;
				}
			}
		}catch(InvalidPositionException x) {throw new InvalidEntryException("error");}
		
		throw new InvalidEntryException("no existe esa entrada, remove");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Entry<K, V>> entries() {
		@SuppressWarnings("rawtypes")
		PositionList<Entry<K,V>> le = new ListaDoblementeEnlazada();
		for(int i=0; i<arreglo.length; i++) {
			for(Position<Entry<K,V>> pos : arreglo[i].positions()) {
				le.addLast(pos.element());
			}
		}
		return le;
	}

	/**
	 * Metodo privado:
	 * Redimensiona el tamaño del arreglo para que no haya un factor de carga actual mayor a 0.9f
	 */
	@SuppressWarnings({ "unchecked" })
	private void reHash() {
		Iterable<Entry<K,V>> entradas = entries();
		cubetas = cubetas*2;
		while(!esPrimo(cubetas)) {
			cubetas++;
		}
		cantEntradas = 0;
		arreglo = new PositionList[cubetas];
		
		for(int i=0; i<cubetas; i++) {
			arreglo[i] = new ListaDoblementeEnlazada<Entry<K,V>>();
		}
		try 
		{
		for(Entry<K,V> e : entradas) {
			this.insert(e.getKey(),e.getValue());
		}
		}catch(InvalidKeyException e) 
			{e.getStackTrace();}
	}
	
	/**
	 * Metodo privado que testea si un numero es primo.
	 * Controla si un numero pasado por parametro es primo
	 * @param tamanio Numero a verificar si es primo
	 * @return True en el caso de que el numero pasado por parametro sea primo, false en caso contrario
	 */
	private boolean esPrimo(int tamanio)
	{
		  if (tamanio == 0 || tamanio == 1 || tamanio == 4) {
		    return false;}
		  for (int i = 2; i < tamanio / 2; i++) {
		    if (tamanio % i == 0)
		      return false;
		  }
		  return true;
	}	
}
