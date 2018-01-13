/**
 * 
 */
package com.example.swiggy.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * @author saptarsichaurashy
 *
 */
@Builder
@Getter
public class AssignmentResponseDto {

	private Integer orderId;

	private Integer executiveId;

}
