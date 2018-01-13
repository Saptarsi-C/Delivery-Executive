/**
 * 
 */
package com.example.swiggy.builder.query;

import java.util.HashMap;
import java.util.Map;

import com.example.swiggy.dto.CustomQueryHolder;
import com.example.swiggy.dto.LocationDto;
import com.example.swiggy.entity.ExecutiveActivity;
import com.example.swiggy.enums.ActivityEnum;
import com.example.swiggy.enums.StatusEnum;

/**
 * @author saptarsichaurashy
 *
 */
public class ExecutiveActivityQueryBuilder {

	public static CustomQueryHolder queryToGetNearByExecutives(LocationDto locationDto, Integer limit) {

		CustomQueryHolder customQueryHolder = new CustomQueryHolder();
		LocationDto l1 = LocationDto.builder().latitude(locationDto.getLatitude() - 0.02F)
				.longitude(locationDto.getLongitude() - 0.02F).build();
		LocationDto l2 = LocationDto.builder().latitude(locationDto.getLatitude() + 0.02F)
				.longitude(locationDto.getLongitude() + 0.02F).build();
		customQueryHolder.setQueryString(setQueryStringForNearByExecutive());
		customQueryHolder.setInParamMap(populateInParamMapToGetExecutive(l1,l2,limit));
		
		return customQueryHolder;

	}

	private static Map<String, Object> populateInParamMapToGetExecutive(LocationDto l1, LocationDto l2, Integer limit){
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("isBusy", ActivityEnum.FREE.getValue());
		paramMap.put("status", StatusEnum.ACTIVE.getValue());
		paramMap.put("lat1", l1.getLatitude());
		paramMap.put("lat2", l2.getLatitude());
		paramMap.put("long1", l1.getLongitude());
		paramMap.put("long2", l2.getLongitude());
		paramMap.put("limit", limit);
		return paramMap;
		
	}
	
	private static String setQueryStringForNearByExecutive() {

		StringBuilder sb = new StringBuilder();
		sb.append("select distict(t.executiveId), t.latestLat, t.latestLang");
		sb.append(" FROM ").append(ExecutiveActivity.class.getName()).append(" t where 1=1");
		sb.append(" and t.isBusy= :isBusy");
		sb.append(" and t.isLoggedIn= :status");
		sb.append(" and t.latestLat between :lat1 and :lat2");
		sb.append(" and t.latestLong between :long1 and :long2");
		sb.append(" ORDER BY t.lastUpdatedOn desc");
		sb.append(" limit :limit");

		return sb.toString();
	}
}
