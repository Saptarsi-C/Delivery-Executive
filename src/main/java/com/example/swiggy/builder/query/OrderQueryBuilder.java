/**
 * 
 */
package com.example.swiggy.builder.query;

import java.util.HashMap;
import java.util.Map;

import com.example.swiggy.dto.CustomQueryHolder;
import com.example.swiggy.enums.StatusEnum;

/**
 * @author saptarsichaurashy
 *
 */
public class OrderQueryBuilder {

	public static CustomQueryHolder queryToGetDeliveryOrder(Integer resId) {

		CustomQueryHolder customQueryHolder = new CustomQueryHolder();
		customQueryHolder.setQueryString(setQueryStringTogetOrderId());
		customQueryHolder.setInParamMap(populateInParamMapToGetExecutive(resId));
		return customQueryHolder;
	}

	private static Map<String, Object> populateInParamMapToGetExecutive(Integer resId) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("resId", resId);
		paramMap.put("orderStatus", StatusEnum.ACTIVE.getValue());
		return paramMap;
	}

	private static String setQueryStringTogetOrderId() {

		StringBuilder sb = new StringBuilder();
		sb.append("select r.latitude, r.longitude, o.id ");
		sb.append("FROM  Restaurant r inner join Order o on o.restaurantId = r.id where 1=1");
		sb.append(" and o.restaurantId= :resId");
		sb.append(" and o.orderStatus= :orderStatus");
		sb.append(" ORDER BY o.orderedOn");
		sb.append(" limit 100");
		return sb.toString();
	}

	public static String getNativeQuery() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select r.latitude, r.longitude, o.id, o.restaurant_id FROM  restaurant r join orders o on o.restaurant_id = r.id where 1=1");
		sb.append(" and o.order_status=2 ORDER BY o.ordered_on, o.restaurant_id limit 50");
		return sb.toString();
	}
}
