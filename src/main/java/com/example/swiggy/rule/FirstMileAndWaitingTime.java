/**
 * 
 */
package com.example.swiggy.rule;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.swiggy.builder.query.ExecutiveActivityQueryBuilder;
import com.example.swiggy.dto.ExecutiveDto;
import com.example.swiggy.dto.LocationDto;
import com.example.swiggy.repo.JpaRepo;

/**
 * @author saptarsichaurashy
 * 
 *         This rule will say that an executive will be assigned who are within
 *         the 3 Km radius of the restaurant Limit of executives has been set to
 *         20
 *
 */
@Component("firstMileAndWaitTime")
public class FirstMileAndWaitingTime {

	@Autowired
	private JpaRepo<Integer> activeExecutive;

	public List<ExecutiveDto> getListOfExecutivesInRange(LocationDto restaurant, Integer locationId, Integer areaCode, Integer limit) {

		List<Object[]> executiveList = activeExecutive
				.findByQuery(ExecutiveActivityQueryBuilder.queryToGetNearByExecutives(restaurant, limit));
		
		List<ExecutiveDto> executiveDtoList = new LinkedList<>();
		executiveList.forEach(executive -> {
			
			LocationDto location = LocationDto.builder().latitude(Float.parseFloat(executive[1].toString())).
					longitude(Float.parseFloat(executive[1].toString())).build();
			executiveDtoList.add(ExecutiveDto.builder().id(Integer.parseInt(executive[0].toString())).location(location).build());
		});
		
		return executiveDtoList;
	}

}
