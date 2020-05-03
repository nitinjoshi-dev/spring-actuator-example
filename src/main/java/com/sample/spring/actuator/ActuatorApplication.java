package com.sample.spring.actuator;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;

@SpringBootApplication
public class ActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActuatorApplication.class, args);
	}
	
	//Used to configure spring percentiles expiry time
	@Bean
	public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
	    return registry -> registry.config()
	        .commonTags("myTag", "myTagValue")
	        .meterFilter(new MeterFilter() {
	
	            @Override
	            public DistributionStatisticConfig configure(Meter.Id id,
	                                                         DistributionStatisticConfig config) {
	                if (id.getName().startsWith("http.server.requests")) {
	                    return config.merge(DistributionStatisticConfig.builder()
	                        .percentilesHistogram(true)
	                        .percentiles(0.5, 0.9, 0.99)
	                        .percentilePrecision(1)
	                        .minimumExpectedValue(1L)
	                        .maximumExpectedValue(Long.MAX_VALUE)
	                        .expiry(Duration.ofHours(1))
	                        .bufferLength(2)
	                        .build());
	                }
	                return config;
	            }
	        });
	}

}
