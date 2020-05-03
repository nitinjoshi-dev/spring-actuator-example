package com.sample.spring.actuator.custom.dto;

import io.micrometer.core.instrument.MeterRegistry;

public class MetricHolder {

	private static MetricHolder singleton;
	
	private static MeterRegistry meterRegistry;
	
	private Metrics pizzaMetrics = null;

	private MetricHolder() {
		pizzaMetrics = new Metrics("pizza_metrics", meterRegistry, "key", "value");
	}

	public static MetricHolder getInstance(MeterRegistry meterRegistry) {
		if (null != singleton) {
			return singleton;
		}
		synchronized (Metrics.class.getName()) {
			if (null != singleton) {
				return singleton;
			}
			MetricHolder.meterRegistry = meterRegistry;
			singleton = new MetricHolder();
		}
		return singleton;
	}

	public static MetricHolder getSingleton() {
		return singleton;
	}

	public static void setSingleton(MetricHolder singleton) {
		MetricHolder.singleton = singleton;
	}

	public Metrics getPizzaMetrics() {
		return pizzaMetrics;
	}

	public void setPizzaMetrics(Metrics pizzaMetrics) {
		this.pizzaMetrics = pizzaMetrics;
	}

}
