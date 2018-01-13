/**
 * 
 */
package com.example.swiggy.dto.request;

import javax.validation.constraints.NotNull;

import com.example.swiggy.dto.LocationDto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author saptarsichaurashy
 *
 */
@Builder
@Getter
public class InputOrderDto {

	@NotNull
	private Integer resId;
	
	@NotNull
	private Integer customerId;
	
	@NotNull
	private LocationDto orderLocation;
	
	@NotNull
	private Integer orderAmt;
	
}
