/**
 * 
 */
package com.example.swiggy.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.swiggy.dto.CustomQueryHolder;

/**
 * @author saptarsichaurashy
 *
 */
@Transactional
@Repository
@SuppressWarnings("unchecked")
public class JpaRepo<K> extends AbstractJpaRepo<K> {

	public JpaRepo() {
		super();
	}

	public <T> void deleteById(final Integer id, final Class<T> clazz) {
		final T entity = masterEntityManager.find(clazz, id);
		masterEntityManager.remove(entity);

	}

	public <T> List<T> findAll(final Class<T> clazz) {
		return slaveEntityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	public <T> List<T> findByQuery(final CustomQueryHolder queryHolder) {
		final Query query = slaveEntityManager.createQuery(queryHolder.getQueryString());
		if (queryHolder.getInParamMap() != null && !queryHolder.getInParamMap().isEmpty()) {
			for (final String currentKey : queryHolder.getInParamMap().keySet()) {
				query.setParameter(currentKey, queryHolder.getInParamMap().get(currentKey));
			}
		}
		return query.getResultList();
	}

	public <T> T findByQueryAndReturnFirstElement(final CustomQueryHolder queryHolder) {
		final Query query = slaveEntityManager.createQuery(queryHolder.getQueryString());
		if (queryHolder.getInParamMap() != null && !queryHolder.getInParamMap().isEmpty()) {
			for (final String currentKey : queryHolder.getInParamMap().keySet()) {
				query.setParameter(currentKey, queryHolder.getInParamMap().get(currentKey));
			}
		}
		return (T) query.getSingleResult();
	}

	public Boolean runQueryForUpdate(final CustomQueryHolder queryHolder) {
		// TODO Auto-generated method stub
		final Query query = masterEntityManager.createQuery(queryHolder.getQueryString());
		if (queryHolder.getInParamMap() != null && !queryHolder.getInParamMap().isEmpty()) {
			for (final Entry<String, Object> currentEntry : queryHolder.getInParamMap().entrySet()) {
				query.setParameter(currentEntry.getKey(), currentEntry.getValue());
			}
		}
		if (query.executeUpdate() > 0) {
			return true;
		}
		return false;
	}

	public <L, M> Map<L, M> findEntityMapByQuery(final CustomQueryHolder queryHolder) {
		final Map<L, M> resultMap = new HashMap<>();

		final Query query = slaveEntityManager.createQuery(queryHolder.getQueryString());
		if (queryHolder.getInParamMap() != null && !queryHolder.getInParamMap().isEmpty()) {
			for (final Entry<String, Object> currentEntry : queryHolder.getInParamMap().entrySet()) {
				query.setParameter(currentEntry.getKey(), currentEntry.getValue());
			}
		}

		for (final Map<String, Object> map : (List<Map<String, Object>>) query.getResultList()) {
			resultMap.put((L) map.get("0"), (M) map.get("1"));
		}
		return resultMap;
	}

	public <T> T findOne(final Integer id, final Class<T> clazz) {
		return slaveEntityManager.find(clazz, id);
	}

}