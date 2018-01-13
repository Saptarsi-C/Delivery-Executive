/**
 * 
 */
package com.example.swiggy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saptarsichaurashy
 *
 */
@RestController
public class StatusController {

	@RequestMapping("/status")
	public ResponseEntity<String> getStatus() {

		return new ResponseEntity<String>("Welcome", HttpStatus.OK);
	}
}
