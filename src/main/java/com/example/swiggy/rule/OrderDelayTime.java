/**
 * 
 */
package com.example.swiggy.rule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.swiggy.builder.query.OrderQueryBuilder;
import com.example.swiggy.entity.Order;
import com.example.swiggy.repo.JpaRepo;

/**
 * @author saptarsichaurashy
 *
 */
@Component("orderDelayTime")
public class OrderDelayTime {

	@Autowired
	private JpaRepo<Order> order;
	
	public List<Order> getOrderListForRestaurantInTimeOrder(Integer resId){
		
		List<Order> orders = order.findByQuery(OrderQueryBuilder.queryToGetDeliveryOrder(resId));
		return orders;
	}
	
}
