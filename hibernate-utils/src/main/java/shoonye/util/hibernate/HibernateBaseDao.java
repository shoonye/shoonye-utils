package shoonye.util.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 * @param <T>
 * @param <ID>
 */
@SuppressWarnings({"unchecked"})
public class HibernateBaseDao<T extends BaseEntity<ID>, ID extends Serializable> implements BaseDao<T, ID> {
	@Autowired private SessionFactory sessionFactory;
	private Class<T> persistentClass;
	
	protected Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public HibernateBaseDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Class<T> persistentClass() {
		return persistentClass;
	}

	@Override
	public T findById(ID id) {
		return (T)session().get(persistentClass(), id);
	}

	@Override
	public T findById(ID id, boolean lock) {
		if(lock)
			return (T)session().get(persistentClass(), id, LockOptions.UPGRADE);
		else
			return (T)session().get(persistentClass(), id);
	}

	@Override
	public List<T> findByExample(T example) {
		Criteria cirteria = session().createCriteria(persistentClass());
		List<T> results = cirteria.add(Example.create(example)).list();
		return results;
	}

	@Override
	public T saveOrUpdate(T entity) {
		session().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public Collection<T> saveOrUpdateAll(Collection<T> entities) {
		if(entities!=null)
			for(T entity: entities){
				session().saveOrUpdate(entity);
			}
		return entities;
	}
	
	@Override
	public Collection<T> saveOrUpdateAll(Collection<T> entities, int batchSize) {
		if(entities!=null) {
			int count=0;
			for(T entity: entities){
				session().saveOrUpdate(entity);
			}
			if (++count % batchSize==0) {
				session().flush();
		        session().clear();
			}
		}
		session().flush();
        session().clear();
		return entities;
	}

	@Override
	public T merge(T detachedInstance) {
	    session().merge(detachedInstance);
	    return detachedInstance;
	}

	@Override
	public void delete(T entity) {
		session().delete(entity);
	}

	@Override
	public void deleteAll(List<T> entities) {
		if(entities!=null)
			for(T entity: entities){
				session().delete(entity);
			}
	}

	@Override
	public void lock(T entity) {
		session().buildLockRequest(LockOptions.UPGRADE).lock(entity);
	}
	
	
	@Override
	public  T findByKey(Map<String, Object> keyValue) {
		Criteria crit = session().createCriteria(persistentClass());

		for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
			crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}

		return (T) crit.uniqueResult();

	}

	
	
	@Override
	public  List<T> findAllByKey( Map<String, Object> keyValue) {
		Criteria crit = session().createCriteria(persistentClass());

		for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
			crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();

	}

	
	
	
	
	
	
}
