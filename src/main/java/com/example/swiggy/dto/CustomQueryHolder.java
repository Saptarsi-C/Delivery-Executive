/**
 * 
 */
package com.example.swiggy.dto;

import java.util.Map;

import lombok.Data;

/**
 * @author saptarsichaurashy
 *
 */
@Data
public class CustomQueryHolder {

	private String queryString;
	private Map<String, Object> inParamMap;

}