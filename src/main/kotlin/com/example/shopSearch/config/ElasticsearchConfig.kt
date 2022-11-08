package com.example.shopSearch.config

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext

/**
 * @author Brian
 * @since 2022/11/08
 */
@Configuration
class ElasticsearchConfig : AbstractReactiveElasticsearchConfiguration() {

    // Configure the client to use.
    // This can be done by ReactiveRestClients or directly via DefaultReactiveElasticsearchClient
    @Bean
    override fun reactiveElasticsearchClient(): ReactiveElasticsearchClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build()

        return ReactiveRestClients.create(clientConfiguration)
    }

    // Set up the ElasticsearchConverter used for domain type mapping utilizing metadata provided by the mapping context
    @Bean
    fun elasticsearchConverter(): ElasticsearchConverter {
        return MappingElasticsearchConverter(elasticsearchMappingContext())
    }

    // The Elasticsearch specific mapping context for domain type metadata
    @Bean
    fun elasticsearchMappingContext(): SimpleElasticsearchMappingContext {
        return SimpleElasticsearchMappingContext()
    }

    // The actual template based on the client and conversion infrastructure
    @Bean
    fun reactiveElasticsearchOperations(): ReactiveElasticsearchOperations {
        return ReactiveElasticsearchTemplate(reactiveElasticsearchClient(), elasticsearchConverter())
    }

    @Bean
    fun elasticsearchClient(): RestHighLevelClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build()

        return RestClients.create(clientConfiguration).rest()
    }

    @Bean
    fun elasticsearchOperations(): ElasticsearchOperations {
        return ElasticsearchRestTemplate(elasticsearchClient())
    }
}