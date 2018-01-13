/**
 * 
 */
package com.example.swiggy.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

/**
 * @author saptarsichaurashy
 *
 */

@Builder
@Getter
public class OrderResponseDto {


	private Integer orderId;
	
	private String restaurantName;
	
	private Integer restaurantId;
	
	private Byte status;
	
	private Date time;
}
