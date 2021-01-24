package shoonye.util.bean;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 * @param <K> Key
 * @param <V> Value
 */
public class Duple<K, V> {
	private K key;
	private V value;
	
	public Duple() {
		super();
	}
	
	public Duple(K key, V value) {
		super();
		this.key = key;	
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Duple other = (Duple) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}	
	
	public Duple<V,K> inverse(){
		Duple<V,K> inverse = new Duple<V, K>(value, key);
		return inverse;
	}
}

