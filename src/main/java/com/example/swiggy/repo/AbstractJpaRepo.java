/**
 * 
 */
package com.example.swiggy.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.example.swiggy.util.general.StringUtil;

/**
 * @author saptarsichaurashy
 *
 */
@Transactional
@SuppressWarnings("unchecked")
public abstract class AbstractJpaRepo<T> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractJpaRepo.class);

	private Class<T> clazz;

	@Autowired
	@Qualifier(value = "masterEntityManagerFactory")
	protected EntityManager masterEntityManager;

	@Autowired
	@Qualifier(value = "slaveEntityManagerFactory")
	protected EntityManager slaveEntityManager;

	@Transactional(rollbackFor = Exception.class)
	public void create(final T entity) {
		masterEntityManager.persist(entity);
	}

	public void delete(final T entity) {
		masterEntityManager.remove(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteById(final Integer entityId) {
		final T entity = findOne(entityId);
		delete(entity);
	}

	@SuppressWarnings("hiding")
	public <T> List<T> fetchByQuery(final String queryString, final Map<String, Object> inParamtersMap) {
		final Query query = slaveEntityManager.createQuery(queryString);
		for (final Entry<String, Object> currentEntry : inParamtersMap.entrySet()) {
			query.setParameter(currentEntry.getKey(), currentEntry.getValue());
		}
		return query.getResultList();
	}

	public List<T> findAll() {
		return slaveEntityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	public List<T> findByQuery(final String query) {
		return slaveEntityManager.createQuery(query).getResultList();
	}

	public T findOne(final Integer id) {
		return slaveEntityManager.find(clazz, id);
	}

	public List<T> findPaginationByQuery(final String queryString, final Integer firstResult, final Integer maxResult) {
		final Query query = slaveEntityManager.createQuery(queryString);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.getResultList();
	}

	@Transactional
	public List<T> updateInBulk(List<T> entityList) {
		Integer count = 0;
		List<T> savedEntities = new ArrayList<>(entityList.size());
		try {
			for (T entity : entityList) {
				savedEntities.add(masterEntityManager.merge(entity));
				if (++count % 25 == 0) {
					masterEntityManager.flush();
					masterEntityManager.clear();
				}
			}
		} catch (Exception exp) {
			logger.error("error occured in updating in bulk due to " + StringUtil.getStackTraceInStringFmt(exp));
		}
		return savedEntities;
	}

	@Transactional
	public List<T> updateInBatch(List<T> entityList) {
		Integer count = 0;
		List<T> savedEntities = new ArrayList<>(entityList.size());
		try {
			for (T entity : entityList) {
				savedEntities.add(masterEntityManager.merge(entity));
				if (++count % 25 == 0) {
					masterEntityManager.flush();
					masterEntityManager.clear();
				}
			}
		} catch (Exception exp) {
			logger.error("error occured in updating in bulk due to " + StringUtil.getStackTraceInStringFmt(exp));
			throw new RuntimeException(exp);
		}
		return savedEntities;
	}

	@Transactional
	public <C extends Collection<T>> C updateInBulkForAnyCollection(C entityList)
			throws InstantiationException, IllegalAccessException {
		Integer count = 0;
		C savedEntities = (C) entityList.getClass().newInstance();
		try {
			for (T entity : entityList) {
				savedEntities.add(masterEntityManager.merge(entity));
				if (++count % 10 == 0) {
					masterEntityManager.flush();
					masterEntityManager.clear();
				}
			}
		} catch (Exception exp) {
			logger.error("error occured in updating in bulk due to " + StringUtil.getStackTraceInStringFmt(exp));
		}
		return savedEntities;
	}

	public List<T> findTopNElements(final String queryString, final Integer max) {
		final Query query = slaveEntityManager.createQuery(queryString);
		query.setMaxResults(max);
		return query.getResultList();
	}

	public List<Object[]> findByNativeQuery(final String queryString) {
		// TODO Auto-generated method stub
		try {
			final Query nativeQuery = slaveEntityManager.createNativeQuery(queryString);
			return nativeQuery.getResultList();
		} catch (final Exception e) {
			logger.error("Failed to run native query" + StringUtil.getStackTraceInStringFmt(e));
		}
		return null;
	}

	public Boolean runNativeQueryForUpdate(final String queryString) {
		// TODO Auto-generated method stub
		try {
			final Query nativeQuery = masterEntityManager.createNativeQuery(queryString);
			if (nativeQuery.executeUpdate() > 0) {
				return true;
			}
		} catch (final Exception e) {
			logger.error("Failed to run native query" + StringUtil.getStackTraceInStringFmt(e));
		}
		return false;
	}

	public final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	@Transactional(rollbackFor = Exception.class)
	public T update(final T entity) {
		return masterEntityManager.merge(entity);
	}

}