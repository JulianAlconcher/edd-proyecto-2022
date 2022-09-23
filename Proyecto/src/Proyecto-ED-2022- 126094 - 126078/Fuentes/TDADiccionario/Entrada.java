package TDADiccionario;

/**
 * Clase Entrada que implementa los metodos de la clase Entry
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 * @version 1.0
 * @param <K> Tipo de dato de la clave de la entrada.
 * @param <V> Tipo de dato del valor de la entrada.
 */
public class Entrada<K,V> implements Entry<K,V>{
	private K clave;
	private V valor;
	
	/**
	 * Constructor de la clase entrada:
	 * Crea una nueva entrada con la clave y valor pasados por parametro.
	 * @param clave valor para setear en clave
	 * @param valor valor para setear en valor
	 */
	public Entrada(K clave, V valor){
		this.clave = clave;
		this.valor = valor;
	}
	/**
	 * Retorna el parametro K
	 */
	public K getKey(){
		return clave;
	}
	/**
	 * Retorna el parametro V
	 */
	public V getValue(){
		return valor;
	}
	/**
	 * Setea el parametro "key" en "clave"
	 * @param key clave a setear
	 */
	public void setKey(K key){
		clave = key;
	}
	/**
	 * Setea el parametro "value" en "valor"
	 * @param value valor a setear
	 */
	public void setValue(V value){
		valor = value;
	}
	/**
	 * Retorna un string con los parametros clave y valor
	 * @return string con el formato " (clave,valor) "
	 */
	public String toStringE(){
		return "("+clave+","+valor+")";
	}
}
