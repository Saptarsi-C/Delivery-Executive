/**
 * 
 */
package com.example.swiggy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author saptarsichaurashy
 *
 */
@Entity
@Table(name = "orders")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "restaurant_id")
	private Integer restaurantId;
	
	@Column(name = "customer_id")
	private Integer customerId;
	
	@Column(name = "order_amt")
	private Integer orderAmt;
	
	@Column(name = "ordered_on")
	private Date orderedOn;
	
	@Column(name  = "order_status")
	private Byte orderStatus;
	
	@Column(name = "order_priority")
	private Byte orderPriority;
	
	@Column(name = "customer_status")
	private Byte customerStatus;
}
