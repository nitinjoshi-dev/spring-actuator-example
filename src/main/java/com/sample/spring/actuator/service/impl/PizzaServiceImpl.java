package com.sample.spring.actuator.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.spring.actuator.dto.PizzaDTO;
import com.sample.spring.actuator.service.intf.PizzaService;

@Service
public class PizzaServiceImpl implements PizzaService {

	@Override
	public List<PizzaDTO> getAllPizzas(Integer size) {
		if(size == null) {
			size = 10;
		}
		
		boolean alwaysVeg = true;
		
		List<PizzaDTO> pizzaDTOs = new ArrayList<PizzaDTO>();
		for(int i = 0; i < size ; i++) {
			PizzaDTO pizzaDTO = new PizzaDTO();
			pizzaDTO.setId(Long.valueOf(i));
			pizzaDTO.setCost(100.0 * (i+1));
			pizzaDTO.setName("Pizza name " + i);
			pizzaDTO.setIsVeg(alwaysVeg);
			pizzaDTOs.add(pizzaDTO);
		}
		return pizzaDTOs;
	}

}
