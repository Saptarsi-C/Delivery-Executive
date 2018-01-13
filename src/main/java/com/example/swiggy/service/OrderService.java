/**
 * 
 */
package com.example.swiggy.service;

import com.example.swiggy.dto.request.InputOrderDto;
import com.example.swiggy.dto.response.OrderResponseDto;

/**
 * @author saptarsichaurashy
 *
 */
public interface OrderService {

	public OrderResponseDto placeOrder(InputOrderDto inputOrder);
}
