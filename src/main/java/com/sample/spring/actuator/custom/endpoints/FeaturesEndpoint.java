package com.sample.spring.actuator.custom.endpoints;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.sample.spring.actuator.dto.FeatureDTO;

@Component
@Endpoint(id = "features")
public class FeaturesEndpoint {

	private Map<String, FeatureDTO> featureDTOs = new ConcurrentHashMap<>();

	public FeaturesEndpoint() {
		// Creating dummy features
		for (int i = 0; i < 10; i++) {
			FeatureDTO featureDTO = new FeatureDTO();
			featureDTO.setName("Feature name " + (i + 1));
			featureDTO.setDescription("Feature description " + (i + 1));
			featureDTOs.put("feature" + (i + 1), featureDTO);
		}
	}

	@ReadOperation
	public Map<String, FeatureDTO> featureDTOs() {
		return featureDTOs;
	}

	@ReadOperation
	public FeatureDTO featureDTO(@Selector String name) {
		return featureDTOs.get(name);
	}

	@WriteOperation
	public void configureFeatureDTO(@Selector String name, FeatureDTO featureDTO) {
		featureDTOs.put(name, featureDTO);
	}

	@DeleteOperation
	public void deleteFeatureDTO(@Selector String name) {
		featureDTOs.remove(name);
	}

}