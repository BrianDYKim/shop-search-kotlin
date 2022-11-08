package com.example.shopSearch.shop.application

import com.example.shopSearch.shop.domain.dto.ShopCommand
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
    suspend fun searchWithIn(request: ShopCommand.WithInRequest): Flow<String>

    /**
     * @param request request
     * @return list of shopId
     */
    suspend fun searchByCategoryWithIn(request: ShopCommand.CategoryWithInRequest): Flow<String>

    /**
     * @param request request
     * @return list of shopId
     */
    suspend fun searchByDetailCategoryWithIn(request: ShopCommand.DetailCategoryWithInRequest): Flow<String>

    /**
     * @param request request
     * @return list of shopId
     */
    suspend fun searchByShopNameWithIn(request: ShopCommand.ShopNameWithInRequest): Flow<String>
}