/**
 * 
 */
package com.example.swiggy.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

/**
 * @author saptarsichaurashy
 *
 */
@Builder
@Getter
public class LocationDto {

	@NotNull
	private Float latitude;
	
	@NotNull
	private Float longitude;
}
