package shoonye.util.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public interface GenericDao {
	public <E> E findById(String id, Class<?> clazz);
	public <E> E findById(Long id, Class<?> clazz);
	public <E> void lock(E entity);
	public <E> List<E> findAll(Class<?> clazz);
	
	public <E> E saveOrUpdate(E entity);
	public <E> Collection<E> saveOrUpdateAll(Collection<E> entity);
	public <E> Collection<E> saveOrUpdateAll(Collection<E> entity, int batchSize);
	public <E> void delete(E entity);
	public <E> void deleteAll(List<E> entities);
	public <E> E findByKey(Class<E> clazz, Map<String, Object> keyValue);
	public <E> List<E> findAllByKey(Class<E> clazz, Map<String, Object> keyValue);
	public <E> List<E> findAllByKey(Class<E> clazz, Map<String, Object> keyValue, int pageSize, int pageIndex);
	public <E> List listProperty(Class<E> clazz, String propName, Map<String, Object> keyValue);
}
