package com.example.shopSearch.shop.domain.persist

import com.example.shopSearch.shop.domain.vo.Category
import com.example.shopSearch.shop.domain.vo.DetailCategory
import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.core.geo.GeoPoint
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Brian
 * @since 2022/11/08
 */
interface ShopAdvancedRepository {
    /**
     * @param geoPoint 기준점
     * @param distance 반경
     * @param unit     거리 단위
     * @param pageable pageable
     * @return List of shop
     */
    fun withInSearch(geoPoint: GeoPoint, distance: Double, unit: String, pageable: Pageable): Flux<Shop>

    /**
     * @param category category of shop
     * @param geoPoint user's location
     * @param distance radius from geoPoint
     * @param unit     distance unit
     * @param pageable pageable
     * @return List of shop satisfying the conditions
     */
    fun searchByCategoryWithIn(
        category: Category,
        geoPoint: GeoPoint,
        distance: Double,
        unit: String,
        pageable: Pageable
    ): Flux<Shop>

    /**
     * @param detailCategory detailCategory of shop
     * @param geoPoint       user's location
     * @param distance       radius from geoPoint
     * @param unit           distance unit
     * @param pageable       pageable
     * @return List of shop satisfying the conditions
     */
    fun searchByDetailCategoryWithIn(
        detailCategory: DetailCategory,
        geoPoint: GeoPoint,
        distance: Double,
        unit: String,
        pageable: Pageable
    ): Flux<Shop>

    /**
     * @param shopName shopName you wanna search
     * @param geoPoint user's location
     * @param distance radius
     * @param unit     distance unit
     * @param pageable pageable
     * @return list of shops what you wanna search
     */
    fun searchByShopNameWithIn(
        shopName: String,
        geoPoint: GeoPoint,
        distance: Double,
        unit: String,
        pageable: Pageable
    ): Flux<Shop>

    fun save(shop: Shop): Mono<Shop>
}