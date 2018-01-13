/**
 * 
 */
package com.example.swiggy.util.general;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.swiggy.dto.LocationDto;

/**
 * @author saptarsichaurashy
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistanceServiceTest {

	@Autowired
	DistanceService distanceService;
	
	@Test
	public void testDistance() {
		
		LocationDto d1 = LocationDto.builder().latitude(12.01F).longitude(20.01F).build();
		LocationDto d2 = LocationDto.builder().latitude(12.03F).longitude(20.03F).build();
		System.out.println(distanceService.getDistance(d1, d2));
	}
}
