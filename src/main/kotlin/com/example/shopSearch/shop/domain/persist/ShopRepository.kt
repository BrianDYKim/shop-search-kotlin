package com.example.shopSearch.shop.domain.persist

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

/**
 * @author Brian
 * @since 2022/11/08
 */
@Repository
interface ShopRepository : ElasticsearchRepository<Shop, String>, ShopAdvancedRepository {

}