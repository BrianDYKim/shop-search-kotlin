package com.example.shopSearch.shop.application

import com.example.shopSearch.shop.domain.dto.ShopRequest
import kotlinx.coroutines.flow.Flow

/**
 * @author Brian
 * @since 2022/11/09
 */
interface ShopService {

    /**
     * @param request request
     * @return list of shopId
     */
    suspend fun searchWithIn(request: ShopRequest.WithIn): Flow<String>

    /**
     * @param request request
     * @return list of shopId
     */
    suspend fun searchByCategoryWithIn(request: ShopRequest.CategoryWithIn): Flow<String>

    /**
     * @param request request
     * @return list of shopId
     */
    suspend fun searchByDetailCategoryWithIn(request: ShopRequest.DetailCategoryWithIn): Flow<String>

    /**
     * @param request request
     * @return list of shopId
     */
    suspend fun searchByShopNameWithIn(request: ShopRequest.ShopNameWithIn): Flow<String>
}