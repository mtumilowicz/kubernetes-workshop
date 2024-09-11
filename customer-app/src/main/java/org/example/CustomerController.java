package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    private final WebClient webClient;

    public CustomerController(WebClient.Builder webClientBuilder,
                              @Value("${customerinfo.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }


    @GetMapping("/customer/{customerId}/short-info")
    public String getShortInfo(@PathVariable String customerId) {
        // Simulate short info (just customer ID in this case)
        return "Short info for customer " + customerId;
    }

    @GetMapping("/customer/{customerId}/full-info")
    public Mono<String> getFullInfo(@PathVariable String customerId) {
        // Fetch full info by calling customerinfo-app
        return webClient.get()
                .uri("/customer/{customerId}/info", customerId)
                .retrieve()
                .bodyToMono(String.class);
    }
}