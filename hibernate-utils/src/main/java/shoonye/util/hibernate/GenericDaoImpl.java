package shoonye.util.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
@Component("genericDao")
@SuppressWarnings("unchecked")
public class GenericDaoImpl  implements GenericDao{
	@Autowired private SessionFactory sessionFactory;
	
	protected Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public <E> E findById(String id, Class<?> clazz) {
		return (E)session().get(clazz, id);
	}

	@Override
	public <E> E findById(Long id, Class<?> clazz) {
		return (E)session().get(clazz, id);
	}
	
	@Override
	public <E> E saveOrUpdate(E entity) {
		session().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public <E> Collection<E> saveOrUpdateAll(Collection<E> entities) {
		if(entities!=null)
			for(E entity: entities){
				session().saveOrUpdate(entity);
			}
		return entities;
	}
	
	@Override
	public <E> Collection<E> saveOrUpdateAll(Collection<E> entities, int batchSize) {
		if(entities!=null) {
			int count=0;
			for(E entity: entities){
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
	public <E> void delete(E entity) {
		session().delete(entity);
	}

	@Override
	public <E> void deleteAll(List<E> entities) {
		if(entities!=null)
			for(E entity: entities){
				session().delete(entity);
			}
	}

	@Override
	public <E> void lock(E entity) {
		session().buildLockRequest(LockOptions.UPGRADE).lock(entity);
	}

    @Override
    public <E> List<E> findAll(Class<?> clazz) {
        Query query = session().createQuery("from " + clazz.getName());
        return query.list();
    }
    
	@Override
	public <E> E findByKey(Class<E> clazz, Map<String, Object> keyValue) {
		Criteria crit = session().createCriteria(clazz);

		for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
			crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}

		return (E) crit.uniqueResult();

	}

	
	
	@Override
	public  <E> List<E> findAllByKey(Class<E> clazz, Map<String, Object> keyValue) {
		Criteria crit = session().createCriteria(clazz);

		for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
			crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();

	}

	@Override
	public  <E> List<E> findAllByKey(Class<E> clazz, Map<String, Object> keyValue, int pageSize, int pageIndex) {
		Criteria crit = session().createCriteria(clazz);
		for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
			crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		crit.setFirstResult(pageIndex);
		crit.setMaxResults(pageSize);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();

	}

	@Override
	public <E> List listProperty(Class<E> clazz, String propName,
			Map<String, Object> keyValue) {
		Criteria crit = session().createCriteria(clazz);
		for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
			crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		//crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.setProjection(Projections.property(propName));
		return crit.list();
	}
}
