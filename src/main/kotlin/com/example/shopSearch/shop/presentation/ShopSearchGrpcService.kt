package com.example.shopSearch.shop.presentation

import com.example.shopSearch.shop.application.ShopService
import com.example.shopSearch.shop.domain.dto.ShopCommand
import com.example.shopSearch.shop.domain.vo.Category
import com.example.shopSearch.shop.domain.vo.DetailCategory
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import team.bakkas.shop.search.*

/**
 * @author Brian
 * @since 2022/11/09
 */
@Service
class ShopSearchGrpcService(
    private val shopService: ShopService
) : ShopSearchGrpcKt.ShopSearchCoroutineImplBase() {

    override suspend fun searchCategoryWithIn(request: SearchCategoryGrpcRequest): SearchResponse = with(request) {
        val searchRequest = ShopCommand.CategoryWithInRequest(Category.valueOf(category), latitude, longitude, distance, unit, page, size)

        val searchResults = shopService.searchByCategoryWithIn(searchRequest).toList()

        SearchResponse.newBuilder()
            .addAllIdList(searchResults)
            .build()
    }

    override suspend fun searchDetailCategoryWithIn(request: SearchDetailCategoryGrpcRequest): SearchResponse = with(request) {
        val searchRequest = ShopCommand.DetailCategoryWithInRequest(DetailCategory.valueOf(detailCategory), latitude, longitude, distance, unit, page, size)

        val searchResults = shopService.searchByDetailCategoryWithIn(searchRequest).toList()

        SearchResponse.newBuilder()
            .addAllIdList(searchResults)
            .build()
    }

    override suspend fun searchShopNameWithIn(request: SearchShopNameGrpcRequest): SearchResponse = with(request) {
        val searchRequest = ShopCommand.ShopNameWithInRequest(shopName, latitude, longitude, distance, unit, page, size)

        val searchResults = shopService.searchByShopNameWithIn(searchRequest).toList()

        SearchResponse.newBuilder()
            .addAllIdList(searchResults)
            .build()
    }

    override suspend fun searchWithIn(request: SearchWithInGrpcRequest): SearchResponse = with(request) {
        val searchRequest = ShopCommand.WithInRequest(latitude, longitude, distance, unit, page, size)

        val searchResults = shopService.searchWithIn(searchRequest).toList()

        SearchResponse.newBuilder()
            .addAllIdList(searchResults)
            .build()
    }
}