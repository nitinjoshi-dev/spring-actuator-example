package com.sample.spring.actuator.info;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.sample.spring.actuator.service.intf.UserService;

@Component
public class TotalUsersInfoContributor implements InfoContributor {
 
    @Autowired
    private UserService userService;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Long> userDetails = new HashMap<>();
        userDetails.put("active", userService.findActiveUserCount());
        userDetails.put("inactive", userService.findInactiveUserCount());
 
        builder.withDetail("users", userDetails);
    }
}