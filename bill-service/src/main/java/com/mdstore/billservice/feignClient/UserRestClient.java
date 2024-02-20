package com.mdstore.billservice.feignClient;

import com.mdstore.billservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AUTH-SERVICE")
public interface UserRestClient {
    @GetMapping("/users/{id}")
    User getuserById(@PathVariable Long id);
}
