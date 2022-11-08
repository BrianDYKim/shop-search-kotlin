package com.example.shopSearch.shop.domain.persist

import com.example.shopSearch.shop.domain.vo.Category
import com.example.shopSearch.shop.domain.vo.DetailCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations
import org.springframework.data.elasticsearch.core.geo.GeoPoint
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

/**
 * @author Brian
 * @since 2022/11/08
 */
@Repository
class ShopAdvancedRepositoryImpl(
    private val reactiveElasticsearchOperations: ReactiveElasticsearchOperations
) : ShopAdvancedRepository {

    override fun withInSearch(geoPoint: GeoPoint, distance: Double, unit: String, pageable: Pageable): Flux<Shop> {
        val criteria = Criteria
            .where("location").within(geoPoint, distance.toString() + unit)

        val query = CriteriaQuery.builder(criteria)
            .withSort(
                Sort.by(
                    GeoDistanceOrder("location", geoPoint).withUnit(unit)
                )
            )
            .withPageable(pageable)
            .build()

        return reactiveElasticsearchOperations.search(query, Shop::class.java)
            .map { it.content }
    }

    override fun searchByCategoryWithIn(
        category: Category,
        geoPoint: GeoPoint,
        distance: Double,
        unit: String,
        pageable: Pageable
    ): Flux<Shop> {
        val criteria = Criteria
            .where("category").`is`(category)
            .and("location").within(geoPoint, distance.toString() + unit)

        val query = CriteriaQuery.builder(criteria)
            .withSort(
                Sort.by(
                    GeoDistanceOrder("location", geoPoint).withUnit(unit)
                )
            )
            .withPageable(pageable)
            .build()

        return reactiveElasticsearchOperations.search(query, Shop::class.java)
            .map { it.content }
    }

    override fun searchByDetailCategoryWithIn(
        detailCategory: DetailCategory,
        geoPoint: GeoPoint,
        distance: Double,
        unit: String,
        pageable: Pageable
    ): Flux<Shop> {
        val criteria = Criteria
            .where("detail_category").`is`(detailCategory)
            .and("location").within(geoPoint, distance.toString() + unit)

        val query = CriteriaQuery.builder(criteria)
            .withSort(
                Sort.by(
                    GeoDistanceOrder("location", geoPoint).withUnit(unit)
                )
            )
            .withPageable(pageable)
            .build()

        return reactiveElasticsearchOperations.search(query, Shop::class.java)
            .map { it.content }
    }

    override fun searchByShopNameWithIn(
        shopName: String,
        geoPoint: GeoPoint,
        distance: Double,
        unit: String,
        pageable: Pageable
    ): Flux<Shop> {
        val criteria = Criteria
            .where("shop_name").`is`(shopName)
            .and("location").within(geoPoint, distance.toString() + unit)

        val query = CriteriaQuery.builder(criteria)
            .withSort(
                Sort.by(
                    GeoDistanceOrder("location", geoPoint).withUnit(unit)
                )
            )
            .withPageable(pageable)
            .build()

        return reactiveElasticsearchOperations.search(query, Shop::class.java)
            .map { it.content }
    }
}