package com.mdstore.cartservice.feignClient;

import com.mdstore.cartservice.modal.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserRestClient {
    @GetMapping("/users/{id}")
    User getCustomerById(@PathVariable Long id);
}
