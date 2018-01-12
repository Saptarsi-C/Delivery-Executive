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
@Table(name = "restaurant")
@Data
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "lat")
	private Float latitude;

	@Column(name = "long")
	private Float longitude;

	@Column(name = "created_at")
	private Date createdAt;

}
