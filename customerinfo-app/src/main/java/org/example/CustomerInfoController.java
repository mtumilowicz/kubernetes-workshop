package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerInfoController {

    @GetMapping("/customer/{customerId}/info")
    public String getCustomerInfo(@PathVariable String customerId) {
        // Simulate fetching detailed customer information
        return "Full info for customer " + customerId + ": [Name: John Doe, Email: john.doe@example.com, Address: 123 Main St]";
    }
}