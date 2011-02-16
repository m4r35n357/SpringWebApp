package uk.me.doitto.mypackage.dao;

import java.util.List;
import java.util.Map;

import uk.me.doitto.mypackage.object.PersistentClass;

/**
 * A generic DAO interface
 * 
 * @author Ian Smith
 *
 * @param <T> parameter representing the class to which this DAO is to apply
 */
public interface GenericDAOIf<T extends PersistentClass> {
	
//	List<T> findAllBySubClass (Class<T> clazz);

	// Read operations
	
	/**
	 * Find all persistent entities of the class represented by type T
	 * @return a list of entities of type T
	 */
    List<T> findAll ();

    /**
     * Find entities of type T by example 
     * @param t example entity, with uninteresting fields nulled or zeroed (AVOID booleans !!!)
     * @return a list of entities matching just the given fields in the example object
     */
    List<T> findByExample (T t);
    
	/**
	 * Find entities of T by running a query string
	 * @param queryString
	 * @return a list of entities
	 */
	List<T> findByQueryString (String queryString);
	
	/**
	 * Find entities of T by running a named query defined in metadata
	 * @param queryName
	 * @return a list of entities
	 */
	List<T> findByNamedQuery (String queryName, Map<String, Object> parameters);
	
    /**
     * Find a specific entity of type T by its identifier field
     * @param id the identifier field value
     * @return the entity
     */
    T findById (String id);

    // Write operations
    
    /**
     * Save a newly-created persistent entity
     * @param t entity to save
     */
    void persist (T t);

    /**
     * Save a detached persistent entity
     * @param t entity to save
     */
    void rePersist (T t);

    /**
     * Delete a persistent entity
     * @param t entity to delete
     */
    void delete (T t);
}