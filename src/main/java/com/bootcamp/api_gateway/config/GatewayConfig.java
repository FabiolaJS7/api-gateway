package com.bootcamp.api_gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service:9004", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))

                .route("service-customer:8282", r -> r.path("/api/customers/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://service-customer"))

                .route("service-product:8383", r -> r.path("/api/products/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://service-product"))

                .route("service-transaction:8484", r -> r.path("/api/transactions/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://service-transaction"))

                .route("service-finance:8686", r -> r.path("/api/finance/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://service-finance"))
                .build();
    }

}
