package com.mdstore.orderservice.feignClient;

import com.mdstore.orderservice.modal.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AUTH-SERVICE")
public interface UserRestClient {
    @GetMapping("/users/{id}")
    User getCustomerById(@PathVariable Long id);
}
