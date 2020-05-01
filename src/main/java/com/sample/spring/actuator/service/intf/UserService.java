package com.sample.spring.actuator.service.intf;

public interface UserService {
	
	Long findActiveUserCount();
	
	Long findInactiveUserCount();

}
