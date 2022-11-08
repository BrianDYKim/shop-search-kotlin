package com.example.shopSearch.shop.application

import com.example.shopSearch.shop.domain.dto.ShopCommand
import com.example.shopSearch.shop.domain.persist.ShopAdvancedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.withContext
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.geo.GeoPoint
import org.springframework.stereotype.Service

/**
 * @author Brian
 * @since 2022/11/09
 */
@Service
class ShopServiceImpl(
    private val shopRepository: ShopAdvancedRepository
) : ShopService {

    override suspend fun searchWithIn(request: ShopCommand.WithInRequest): Flow<String> = withContext(Dispatchers.IO) {
        val geoPoint = GeoPoint(request.latitude, request.longitude)
        val pageRequest = PageRequest.of(request.page, request.size)

        return@withContext shopRepository.withInSearch(geoPoint, request.distance, request.unit, pageRequest)
            .asFlow()
            .buffer(capacity = 300)
            .map { it.shopId }
    }

    override suspend fun searchByCategoryWithIn(request: ShopCommand.CategoryWithInRequest): Flow<String> =
        withContext(Dispatchers.IO) {
            val geoPoint = GeoPoint(request.latitude, request.longitude)
            val pageRequest = PageRequest.of(request.page, request.size)

            return@withContext shopRepository.searchByCategoryWithIn(
                request.category,
                geoPoint,
                request.distance,
                request.unit,
                pageRequest
            )
                .asFlow()
                .buffer(capacity = 300)
                .map { it.shopId }
        }

    override suspend fun searchByDetailCategoryWithIn(request: ShopCommand.DetailCategoryWithInRequest): Flow<String> =
        withContext(Dispatchers.IO) {
            val geoPoint = GeoPoint(request.latitude, request.longitude)
            val pageRequest = PageRequest.of(request.page, request.size)

            return@withContext shopRepository.searchByDetailCategoryWithIn(
                request.detailCategory,
                geoPoint,
                request.distance,
                request.unit,
                pageRequest
            )
                .asFlow()
                .buffer(capacity = 300)
                .map { it.shopId }
        }

    override suspend fun searchByShopNameWithIn(request: ShopCommand.ShopNameWithInRequest): Flow<String> =
        withContext(Dispatchers.IO) {
            val geoPoint = GeoPoint(request.latitude, request.longitude)
            val pageRequest = PageRequest.of(request.page, request.size)

            return@withContext shopRepository.searchByShopNameWithIn(
                request.shopName,
                geoPoint,
                request.distance,
                request.unit,
                pageRequest
            )
                .asFlow()
                .buffer(capacity = 300)
                .map { it.shopId }
        }
}