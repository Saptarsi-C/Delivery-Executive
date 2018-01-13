/**
 * 
 */
package com.example.swiggy.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swiggy.builder.query.OrderQueryBuilder;
import com.example.swiggy.dto.ExecutiveDto;
import com.example.swiggy.dto.LocationDto;
import com.example.swiggy.dto.OrdersDto;
import com.example.swiggy.dto.response.AssignmentResponseDto;
import com.example.swiggy.entity.ExecutiveActivity;
import com.example.swiggy.entity.Order;
import com.example.swiggy.enums.ActivityEnum;
import com.example.swiggy.enums.OrderCycleEnum;
import com.example.swiggy.repo.JpaRepo;
import com.example.swiggy.rule.FirstMileAndWaitingTime;
import com.example.swiggy.service.ExecutiveAssignmentService;
import com.example.swiggy.util.general.DistanceService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author saptarsichaurashy
 *
 */
@Service
@Slf4j
public class ExecutiveAssignmentServiceImpl implements ExecutiveAssignmentService {

	@Autowired
	private JpaRepo<Order> jpaRepo;
	
	@Autowired
	private JpaRepo<ExecutiveActivity> executiveRepo;

	@Autowired
	private FirstMileAndWaitingTime firstMileAndWaitTime;
	
	@Autowired
	DistanceService distanceService;

	@Override
	@Transactional
	public AssignmentResponseDto assignExecutive() {

		List<Object[]> response = jpaRepo.findByNativeQuery(OrderQueryBuilder.getNativeQuery());
		List<OrdersDto> orders = new LinkedList<>();
		Map<Integer, Integer> orderPerRestaurant = new HashMap<>();
		response.forEach(resp -> {

			LocationDto location = LocationDto.builder().latitude(Float.parseFloat(resp[0].toString()))
					.longitude(Float.parseFloat(resp[1].toString())).build();
			Integer resId = Integer.parseInt(resp[3].toString());
			orders.add(OrdersDto.builder().orderId(Integer.parseInt(resp[2].toString())).locationDto(location)
					.resId(resId).build());
			if (orderPerRestaurant.containsKey(resId)) {
				Integer val = orderPerRestaurant.get(resId);
				orderPerRestaurant.put(resId, ++val);
			} else {
				orderPerRestaurant.put(resId, 1);
			}
		});

		Integer oldResId = null;
		int k = 0;
		for (OrdersDto order : orders) {

			if (oldResId != null && order.getResId() == oldResId) {
				continue;
			}
			Integer limit = orderPerRestaurant.get(order.getResId());
			List<ExecutiveDto> executiveDtoList = firstMileAndWaitTime
					.getListOfExecutivesInRange(order.getLocationDto(), 1, 1, limit);
			List<ExecutiveDto> executiveDtoListSorted = getExecutiveListInSortedOrderOfDistance(executiveDtoList,
					order.getLocationDto());
			while(!executiveDtoListSorted.isEmpty()) {
				
				Integer executiveId = executiveDtoList.get(0).getId();
				Integer orderId = executiveDtoList.get(k++).getId();
				log.info("Order : " +orderId + "Assigned to : "+ executiveId);
				updateOrderExecutiveStatus(executiveId, orderId);
				executiveDtoList.remove(0);
			}
			oldResId = order.getResId();
		}

		return null;
	}

	private void updateOrderExecutiveStatus(Integer executiveId, Integer orderId) {
		Order odr = new Order();
		odr.setId(orderId);
		odr.setOrderStatus(OrderCycleEnum.RECEIVED.getValue());
		jpaRepo.update(odr);
		ExecutiveActivity executiveActivity = new ExecutiveActivity();
		executiveActivity.setId(executiveId);
		executiveActivity.setIsBusy(ActivityEnum.BUSY.getValue());
		executiveRepo.update(executiveActivity);
	}

	private List<ExecutiveDto> getExecutiveListInSortedOrderOfDistance(List<ExecutiveDto> listDto,
			LocationDto restaurant) {
		
		Double dist = 4D;
		List<ExecutiveDto> listDtofinal = new LinkedList<>();
		while(!listDto.isEmpty()) {
			int index = -1;
			int k = 0;
			for(ExecutiveDto list : listDto) {
				
				Double calcDist = distanceService.getDistance(list.getLocation(), restaurant);
				if(calcDist < dist) {
					dist = calcDist;
					index = k;
				}
				k++;
			}
			listDtofinal.add(listDto.remove(index));
		}
		return listDtofinal;
	}

}
