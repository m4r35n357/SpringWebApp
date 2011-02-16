package uk.me.doitto.mypackage.dao;

import java.util.List;
import java.util.Map;

import uk.me.doitto.mypackage.object.PersistentClass;

/**
 * A generic DAO class for use with Spring's Hibernate support
 * 
 * @author Ian Smith
 *
 * @param <T> parameter representing the class to which this DAO is to apply
 */
public class GenericNativeHibernateDAO<T extends PersistentClass> implements GenericDAOIf<T> {

	public void delete(T t) {
		// TODO Auto-generated method stub
		
	}

	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findByExample(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	public T findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findByQueryString(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	public void persist(T t) {
		// TODO Auto-generated method stub
		
	}

	public void rePersist(T t) {
		// TODO Auto-generated method stub
		
	}

//	private final Log log = LogFactory.getLog(getClass());
//	
//	private SessionFactory sessionFactory;
//	public void setSessionFactory (SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//	
//	/**
//	 * the class to which this DAO is to apply
//	 */
//	private final Class<T> clazz;
//	
//	/**
//	 * Constructor, specify class
//	 * 
//	 * @param clazz the class to which this DAO is to apply 
//	 */
//	public GenericNativeHibernateDAO (Class<T> clazz) {
//		this.clazz = clazz;
//	}
//	public GenericNativeHibernateDAO (Class<T> clazz, SessionFactory sessionFactory) {
//		this.clazz = clazz;
//	}
//	
//	/**
//	 * Find all persistent entities of the class represented by type T
//	 * @return a list of entities of type T
//	 */
//	@SuppressWarnings("unchecked")
//	public List<T> findAll () throws DataAccessException {
//		log.debug("");
//		return (List<T>)sessionFactory.getCurrentSession().loadAll(clazz);
//	}
//
//    /**
//     * Find entities of type T by example 
//     * @param t example entity, with uninteresting fields nulled or zeroed (AVOID booleans !!!)
//     * @return a list of entities matching just the given fields in the example object
//     */
//    @SuppressWarnings("unchecked")
//	public List<T> findByExample (final T t) throws DataAccessException {
//		log.debug(t.toString());
//        return (List<T>)sessionFactory.getCurrentSession().execute(new HibernateCallback() {
//            public Object doInHibernate (Session session) {
//                Criteria criteria = session.createCriteria(clazz);
//                criteria.add(Example.create(t).excludeZeroes());
//                return criteria.list();
//            }
//        });
//    }
//
//	/**
//	 * Find entities of T by running a query string
//	 * @param queryString
//	 * @return a list of entities
//	 */
//	@SuppressWarnings("unchecked")
//	public List<T> findByQueryString (String queryString) {
//		log.debug(queryString);
//		return (List<T>)sessionFactory.getCurrentSession().find(queryString);
//	}
//	
//	/**
//	 * Find entities of T by running a named query defined in metadata
//	 * @param queryName
//	 * @return a list of entities
//	 */
//	@SuppressWarnings("unchecked")
//	public List<T> findByNamedQuery (String queryName, Map<String, Object> parameters) {
//		log.debug(queryName);
//		return (List<T>)sessionFactory.getCurrentSession().findByNamedQuery(queryName);
//	}
//	
//    /**
//     * Find a specific entity of type T by its identifier field
//     * @param id the identifier field value
//     * @return the entity
//     */
//	@SuppressWarnings("unchecked")
//	public T findById (String id) throws DataAccessException {
//		log.debug(id);
//		return (T)sessionFactory.getCurrentSession().load(clazz, id);
//	}
//
//    /**
//     * Save a newly-created persistent entity
//     * @param t entity to save
//     */
//	public void persist (T t) throws DataAccessException {
//		log.debug(t.toString());
////		sessionFactory.getCurrentSession().saveOrUpdate(t);
//		sessionFactory.getCurrentSession().persist(t);
//	}
//	
//    /**
//     * Save a detached persistent entity
//     * @param t entity to save
//     */
//	public void rePersist (T t) throws DataAccessException {
//		log.debug(t.toString());
////		sessionFactory.getCurrentSession().saveOrUpdate(t);
//		sessionFactory.getCurrentSession().update(t);
//	}
//
//    /**
//     * Delete a persistent entity
//     * @param t entity to delete
//     */
//	public void delete (T t) throws DataAccessException {
//		log.debug(t.toString());
//		sessionFactory.getCurrentSession().delete(t);
//	}
}
