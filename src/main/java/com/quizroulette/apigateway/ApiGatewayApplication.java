package com.quizroulette.apigateway;

import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class ApiGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayApplication.class, args);
  }

  //TODO Cors

  @Value("${route-question-bank-graphql:}")
  public String routeForGraphQL;

  @Bean
  Function<GatewayFilterSpec, UriSpec> brutalCorsFilters() {
    return f -> f

        .setResponseHeader("Access-Control-Allow-Origin", "*")
        .setResponseHeader("Access-Control-Allow-Methods", "*")
        .setResponseHeader("Access-Control-Expose-Headers", "*");
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("question-bank-graphql", r -> r.path("/graphql")
            .uri(routeForGraphQL))
        .build();
  }

}
