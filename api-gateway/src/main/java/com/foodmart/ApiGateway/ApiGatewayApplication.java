package com.foodmart.ApiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	


	@Autowired
    private ReactiveDiscoveryClient reactiveDiscoveryClient;

    @Bean
    public DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(
            DiscoveryLocatorProperties properties) {
        return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, properties);
    }
	
	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("UserService", r -> r.path("/users/**")
                        .uri("lb://USERSERVICE"))
                .route("ProductService", r -> r.path("/products/**")
                        .uri("lb://PRODUCTSERVICE"))
                .route("CartService", r -> r.path("/carts/**").or().path("/cart/**")
                        .uri("lb://CARTSERVICE"))
                .build();
    }
}
