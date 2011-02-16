package uk.me.doitto.mypackage.test;

import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uk.me.doitto.mypackage.dao.GenericDAOIf;
import uk.me.doitto.mypackage.object.PersistentClass;

public class MockDAO<T extends PersistentClass> extends HibernateDaoSupport implements GenericDAOIf<T>{

	public void delete(T entity) {
	}

	public List<T> findAll() {
		return null;
	}

	public List<T> findByExample(T exampleInstance) {
		return null;
	}

	public T findById(String id) {
		return null;
	}

	public T findById (T t, String id){
		return null;
	}

	public List<T> findByQueryString(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findByExample(Map<String, ?> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findByNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public void persist(T t) {
		// TODO Auto-generated method stub
		
	}

	public void rePersist(T t) {
		// TODO Auto-generated method stub
		
	}
}
