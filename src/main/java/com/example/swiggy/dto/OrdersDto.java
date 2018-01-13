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
public class OrdersDto {

	private Integer orderId;
	
	private LocationDto locationDto;
	
	private Integer resId;
}
