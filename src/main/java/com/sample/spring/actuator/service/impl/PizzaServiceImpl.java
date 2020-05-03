package com.sample.spring.actuator.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.spring.actuator.custom.dto.MetricHolder;
import com.sample.spring.actuator.custom.dto.Metrics;
import com.sample.spring.actuator.dto.PizzaDTO;
import com.sample.spring.actuator.service.intf.PizzaService;

import io.micrometer.core.instrument.MeterRegistry;

@Service
public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	private MeterRegistry meterRegistry;

	@Override
	public List<PizzaDTO> getAllPizzas(Integer size) {
		Metrics pizzaMetrics = MetricHolder.getInstance(meterRegistry).getPizzaMetrics();
		pizzaMetrics.onEnter();
		if (size == null) {
			size = 10;
		}

		boolean alwaysVeg = true;

		List<PizzaDTO> pizzaDTOs = new ArrayList<PizzaDTO>();
		for (int i = 0; i < size; i++) {
			PizzaDTO pizzaDTO = new PizzaDTO();
			pizzaDTO.setId(Long.valueOf(i));
			pizzaDTO.setCost(100.0 * (i + 1));
			pizzaDTO.setName("Pizza name " + i);
			pizzaDTO.setIsVeg(alwaysVeg);
			pizzaDTOs.add(pizzaDTO);
		}
		pizzaMetrics.onExit(true);
		return pizzaDTOs;
	}

}
