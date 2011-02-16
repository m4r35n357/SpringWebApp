package uk.me.doitto.mypackage.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uk.me.doitto.mypackage.object.PersistentClass;

/**
 * A generic DAO class for use with Spring's Hibernate support
 * 
 * @author Ian Smith
 *
 * @param <T> parameter representing the class to which this DAO is to apply
 */
public class GenericHibernateDAO<T extends PersistentClass> extends HibernateDaoSupport implements GenericDAOIf<T> {

	private final Log log = LogFactory.getLog(getClass());
	
	/**
	 * the class to which this DAO is to apply
	 */
	final Class<T> clazz;
	
	/**
	 * Constructor, specify class
	 * 
	 * @param clazz the class to which this DAO is to apply 
	 */
	public GenericHibernateDAO (Class<T> clazz) {
		this.clazz = clazz;
	}
	public GenericHibernateDAO (Class<T> clazz, SessionFactory sessionFactory) {
		this(clazz);
		HibernateTemplate hibernateTemplate = new HibernateTemplate();
		hibernateTemplate.setSessionFactory(sessionFactory);
		setHibernateTemplate(hibernateTemplate);
	}
	
//	@SuppressWarnings("unchecked")
//	public List<T> findAllBySubClass (Class<T> clazz) throws DataAccessException {
//		log.debug("");
//		return (List<T>)getHibernateTemplate().loadAll(clazz);
//	}
	
	/**
	 * Find all persistent entities of the class represented by type T
	 * @return a list of entities of type T
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll () throws DataAccessException {
		log.debug("");
		return getHibernateTemplate().loadAll(clazz);
	}

    /**
     * Find entities of type T by example 
     * @param t example entity, with uninteresting fields nulled or zeroed (AVOID booleans !!!)
     * @return a list of entities matching just the given fields in the example object
     */
    @Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample (final T t) throws DataAccessException {
		log.debug(t.toString());
        return (List<T>)getHibernateTemplate().execute(new HibernateCallback() {
            @Override
			public Object doInHibernate (Session session) {
                Criteria criteria = session.createCriteria(clazz);
                criteria.add(Example.create(t).excludeZeroes());
                return criteria.list();
            }
        });
    }

	/**
	 * Find entities of T by running a query string
	 * @param queryString
	 * @return a list of entities
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByQueryString (String queryString) {
		log.debug(queryString);
		return getHibernateTemplate().find(queryString);
	}
	
	/**
	 * Find entities of T by running a named query defined in metadata
	 * @param queryName
	 * @return a list of entities
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery (String queryName, Map<String, Object> parameters) {
		log.debug(queryName);
		return getHibernateTemplate().findByNamedQuery(queryName);
	}
	
    /**
     * Find a specific entity of type T by its identifier field
     * @param id the identifier field value
     * @return the entity
     */
	@Override
	@SuppressWarnings("unchecked")
	public T findById (String id) throws DataAccessException {
		log.debug(id);
		return (T)getHibernateTemplate().load(clazz, id);
	}

    /**
     * Save a newly-created persistent entity
     * @param t entity to save
     */
	@Override
	public void persist (T t) throws DataAccessException {
		log.debug(t.toString());
//		getHibernateTemplate().saveOrUpdate(t);
		getHibernateTemplate().persist(t);
	}
	
    /**
     * Save a detached persistent entity
     * @param t entity to save
     */
	@Override
	public void rePersist (T t) throws DataAccessException {
		log.debug(t.toString());
//		getHibernateTemplate().saveOrUpdate(t);
		getHibernateTemplate().update(t);
	}

    /**
     * Delete a persistent entity
     * @param t entity to delete
     */
	@Override
	public void delete (T t) throws DataAccessException {
		log.debug(t.toString());
		getHibernateTemplate().delete(t);
	}
}
