package com.sample.spring.actuator.service.impl;

import org.springframework.stereotype.Service;

import com.sample.spring.actuator.service.intf.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public Long findActiveUserCount() {
		return Double.valueOf((1 + Math.random()) * 1000).longValue();
	}

	@Override
	public Long findInactiveUserCount() {
		return Double.valueOf((1 + Math.random()) * 1000).longValue();
	}

}
