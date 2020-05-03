package com.sample.spring.actuator.custom.dto;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

public class Metrics {

	Counter successCounter;
	Counter failureCounter;
	DistributionSummary distributionSummary;
	Timer timer;

	private static final class Clock {
		public long time = 0;
	}

	final ThreadLocal<Clock> clock = new ThreadLocal<Clock>() {
		@Override
		protected Clock initialValue() {
			return new Clock();
		}
	};

	public Metrics(String name, MeterRegistry meterRegistry, String... tags) {
		super();

		successCounter = Counter.builder(name + "_success").tags(tags).register(meterRegistry);

		failureCounter = Counter.builder(name + "_failure").tags(tags).register(meterRegistry);

		/*
		 * distributionSummary = DistributionSummary .builder(name) .tags(tags)
		 * .publishPercentiles(0.5, 0.9, 0.99)
		 * .distributionStatisticExpiry(Duration.ofHours(1)).register(meterRegistry);
		 */

		timer = Timer.builder(name)
				.tags(tags)
				.publishPercentiles(0.5, 0.9, 0.99)
				.distributionStatisticExpiry(Duration.ofHours(1))
				.register(meterRegistry);

	}

	public void onEnter() {
		clock.get().time = System.nanoTime();
	}

	public void onExit(boolean success) {
		if (success) {
			successCounter.increment();
		} else {
			failureCounter.increment();
		}

		long endTime = System.nanoTime();
		long diff = (endTime - clock.get().time);
		timer.record(diff, TimeUnit.NANOSECONDS);
	}

}
