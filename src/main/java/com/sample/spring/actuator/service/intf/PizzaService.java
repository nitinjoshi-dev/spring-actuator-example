package com.sample.spring.actuator.service.intf;

import java.util.List;

import com.sample.spring.actuator.dto.PizzaDTO;

public interface PizzaService {
	List<PizzaDTO> getAllPizzas(Integer size);
}
