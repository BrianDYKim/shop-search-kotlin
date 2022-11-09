package com.example.shopSearch.shop.presentation

import kotlinx.coroutines.coroutineScope
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

/**
 * @author Brian
 * @since 2022/11/09
 */
@Component
class HealthHandler {

    suspend fun healthCheck(request: ServerRequest): ServerResponse = coroutineScope {
        return@coroutineScope ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait("health")
    }
}