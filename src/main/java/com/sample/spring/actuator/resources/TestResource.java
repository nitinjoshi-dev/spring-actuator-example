package com.sample.spring.actuator.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.actuator.dto.PizzaDTO;
import com.sample.spring.actuator.service.intf.PizzaService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/api")
public class TestResource {
	
	private static final Logger log = LoggerFactory.getLogger(TestResource.class);
	
	@Autowired
	private PizzaService pizzaService;

	@GetMapping(path = "/pizzas")
	public ResponseEntity<List<PizzaDTO>> getAllPizza(@RequestParam(name = "size", required = false) Integer size) {
		log.info("inside getAllPizza {}", size);
		List<PizzaDTO> pizzaDTOs = pizzaService.getAllPizzas(size);
		log.info("Exiting getAllPizza");
		return new ResponseEntity<List<PizzaDTO>>(pizzaDTOs, HttpStatus.OK);
	}

	@RequestMapping("/test")
	public ResponseEntity<Void> test() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
