package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class City implements Serializable {

	@Id
	@SequenceGenerator(name="city_generator", sequenceName="city_sequence", initialValue = 1)
	@GeneratedValue(generator = "city_generator")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private String state;

	@Column(nullable = false)
	private String country;

	@Column(nullable = true)
	private String map;

	protected City() {
	}

	public City(String name, String country) {
		super();
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public String getState() {
		return this.state;
	}

	public String getCountry() {
		return this.country;
	}

	public String getMap() {
		return this.map;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setMap(String map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return getId() + "," + getName() + "," + getState() + "," + getCountry();
	}
}
