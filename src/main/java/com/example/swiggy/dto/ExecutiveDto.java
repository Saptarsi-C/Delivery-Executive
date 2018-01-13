/**
 * 
 */
package com.example.swiggy.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author saptarsichaurashy
 *
 */
@Builder
@Getter
public class ExecutiveDto {

	private Integer id;
	
	private LocationDto location;
}
