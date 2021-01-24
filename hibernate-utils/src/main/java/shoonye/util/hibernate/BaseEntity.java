package shoonye.util.hibernate;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 * @param <I>
 */
@MappedSuperclass
public abstract class BaseEntity<I extends Serializable> implements Serializable{
	private static final long serialVersionUID = 1L;

	public abstract I getId();
	public abstract void setId(I id);
	
	@Override
	public int hashCode() {
		if(getId()==null) return nullIdHashCode();
		return getId().hashCode();
	}
	
	protected int nullIdHashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity<?> other = (BaseEntity<?>) obj;
		if (getId() == null) {
			return nullIdEquals(other);
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	
	protected boolean nullIdEquals(BaseEntity<?> other) {
		return false;
	}
}
