/**
 * 
 */
package com.example.swiggy.entity;

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
@Table(name = "delivery_executive_activity")
@Data
public class ExecutiveActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "executive_id")
	private Integer executiveId;

	@Column(name = "latest_lat")
	private Float latestLat;

	@Column(name = "latest_long")
	private Float latestLong;

	@Column(name = "is_busy")
	private Byte isBusy;

	@Column(name = "is_logged_in")
	private Byte isLoggedIn;

	@Column(name = "last_updated_on")
	private Byte lastUpdatedOn;

}
