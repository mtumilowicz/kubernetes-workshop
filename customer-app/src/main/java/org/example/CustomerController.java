package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{customerId}/short-info")
    public String getShortInfo(@PathVariable String customerId) {
        // Simulate short info (just customer ID in this case)
        return "Short info for customer " + customerId;
    }

    @GetMapping("/customer/{customerId}/full-info")
    public Mono<String> getFullInfo(@PathVariable String customerId) {
        // Fetch full info by calling customerinfo-app
        return customerService.getFullCustomerInfo(customerId);
    }
}