/**
 * 
 */
package com.example.swiggy.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.swiggy.service.ExecutiveAssignmentService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author saptarsichaurashy
 *
 */
@Component
@Slf4j
public class CronService {

	@Autowired
	private ExecutiveAssignmentService executiveAssignmentService;

	@Scheduled(fixedDelay = 10000)
	public void scheduleAssignment() {

		log.info("Executing cron");
		executiveAssignmentService.assignExecutive();
	}

}
