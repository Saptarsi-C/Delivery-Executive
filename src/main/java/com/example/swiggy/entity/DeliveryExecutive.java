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
@Table(name = "delivery_executive")
@Data
public class DeliveryExecutive {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private Long phoneNo;

	@Column(name = "password")
	private String password;
	
	@Column(name = "level")
	private Byte level;

	@Column(name = "status")
	private Byte status;
	
	@Column(name = "area_code")
	private Integer areaCode;
	
	@Column(name = "location_code")
	private Integer locationCode;
	
	@Column(name = "joined_on")
	private Date joinedOn;
	
	@Column(name = "updated_on")
	private Date updatedOn;
}
