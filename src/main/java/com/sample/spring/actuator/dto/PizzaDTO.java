package com.sample.spring.actuator.dto;

public class PizzaDTO {
	
	private Long id;
	private String name;
	private Double cost;
	private Boolean isVeg;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Boolean getIsVeg() {
		return isVeg;
	}
	public void setIsVeg(Boolean isVeg) {
		this.isVeg = isVeg;
	}
}
