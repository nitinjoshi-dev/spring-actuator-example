package com.sample.spring.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("customServiceHealth")
public class CustomHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		if (checkIfServiceRunning()) {
			return Health.up().withDetail("serviceName", "someValue").build();
        }
		return Health.down().withDetail("serviceName", "someValue").build();

    }

    private Boolean checkIfServiceRunning() {
        Boolean isRunning = true;
        return isRunning;
    }
}