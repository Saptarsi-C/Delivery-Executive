/**
 * 
 */
package com.example.swiggy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.swiggy.dto.request.InputOrderDto;
import com.example.swiggy.dto.response.OrderResponseDto;
import com.example.swiggy.service.OrderService;

/**
 * @author saptarsichaurashy
 *
 */
@RestController("order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(name = "/place", method = RequestMethod.POST)
	public ResponseEntity<OrderResponseDto> placeOrder(@Valid @RequestBody InputOrderDto inputOrder){
		

		OrderResponseDto response = orderService.placeOrder(inputOrder);
		return new ResponseEntity<OrderResponseDto>(response, HttpStatus.CREATED);
		
	}
}
