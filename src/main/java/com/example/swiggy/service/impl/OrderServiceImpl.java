/**
 * 
 */
package com.example.swiggy.service.impl;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swiggy.dto.request.InputOrderDto;
import com.example.swiggy.dto.response.OrderResponseDto;
import com.example.swiggy.entity.Order;
import com.example.swiggy.entity.Restaurant;
import com.example.swiggy.enums.OrderCycleEnum;
import com.example.swiggy.repo.JpaRepo;
import com.example.swiggy.service.OrderService;
import com.example.swiggy.util.general.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author saptarsichaurashy
 *
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private JpaRepo<Order> orderRepo;

	@Autowired
	private JpaRepo<Restaurant> resRepo;

	@Override
	public OrderResponseDto placeOrder(InputOrderDto inputOrder) {

		Order order = new Order();
		final Integer id = inputOrder.getResId();
		CompletableFuture<Restaurant> resName = CompletableFuture.supplyAsync(() -> {

			return resRepo.findOne(id, Restaurant.class);
		});
		order.setCustomerId(inputOrder.getCustomerId());
		order.setRestaurantId(inputOrder.getCustomerId());
		order.setOrderAmt(inputOrder.getOrderAmt());
		order.setOrderedOn(new Date());
		order.setOrderStatus(OrderCycleEnum.PLACED.getValue());
		orderRepo.create(order);
		log.info("Order created");
		try {
			return OrderResponseDto.builder().orderId(order.getId()).restaurantId(order.getRestaurantId())
					.time(order.getOrderedOn()).restaurantName(resName.get().getName()).status(order.getOrderStatus())
					.build();
		} catch (Exception e) {
			StringUtil.getStackTraceInStringFmt(e);
			e.printStackTrace();
		}
		return null;
	}

}
