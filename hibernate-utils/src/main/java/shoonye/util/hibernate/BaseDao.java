package shoonye.util.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 * @param <E>
 * @param <ID>
 */
public interface BaseDao<E extends BaseEntity<ID>, ID extends Serializable> {	
	E findById(ID id);
	E findById(ID id, boolean lock);
	List<E> findByExample(E example);

	E saveOrUpdate(E entity);
	Collection<E> saveOrUpdateAll(Collection<E> entity);
	Collection<E> saveOrUpdateAll(Collection<E> entity, int batchSize);
    E merge(E detachedInstance);

	void delete(E entity);
	void deleteAll(List<E> entities);

	void lock(E entity);
	E findByKey(Map<String, Object> keyValue);
	List<E> findAllByKey(Map<String, Object> keyValue);
}