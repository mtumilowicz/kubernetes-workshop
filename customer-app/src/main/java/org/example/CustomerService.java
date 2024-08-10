package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final WebClient webClient;

    public CustomerService(WebClient.Builder webClientBuilder,
                           @Value("${customerinfo.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public Mono<String> getFullCustomerInfo(String customerId) {
        return webClient.get()
                .uri("/customer/{customerId}/info", customerId)
                .retrieve()
                .bodyToMono(String.class);
    }
}