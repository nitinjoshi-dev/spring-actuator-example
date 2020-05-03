package com.sample.spring.actuator.custom.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;

//@Component
public class MeasureTime {
	
	@Autowired
	private MeterRegistry meterRegistry;
	
//	Summa

}
