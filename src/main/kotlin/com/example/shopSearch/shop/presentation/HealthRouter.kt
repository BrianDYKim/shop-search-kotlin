package com.example.shopSearch.shop.presentation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

/**
 * @author Brian
 * @since 2022/11/09
 */
@Configuration
class HealthRouter(private val healthHandler: HealthHandler) {

    @Bean
    fun healthCheckRoutes() = coRouter {
        "".nest {
            GET("/health", healthHandler::healthCheck)
        }
    }
}