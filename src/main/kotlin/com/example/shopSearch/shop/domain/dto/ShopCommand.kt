package com.example.shopSearch.shop.domain.dto

import com.example.shopSearch.shop.domain.vo.Category
import com.example.shopSearch.shop.domain.vo.DetailCategory

/**
 * @author Brian
 * @since 2022/11/09
 */
sealed class ShopCommand {

    data class CategoryWithInRequest(
        var category: Category,
        var latitude: Double,
        var longitude: Double,
        var distance: Double,
        var unit: String,
        var page: Int,
        var size: Int
    ) {

    }

    data class DetailCategoryWithInRequest(
        var detailCategory: DetailCategory,
        var latitude: Double,
        var longitude: Double,
        var distance: Double,
        var unit: String,
        var page: Int,
        var size: Int
    ) {

    }

    data class ShopNameWithInRequest(
        var shopName: String,
        var latitude: Double,
        var longitude: Double,
        var distance: Double,
        var unit: String,
        var page: Int,
        var size: Int
    ) {

    }

    data class WithInRequest(
        var latitude: Double,
        var longitude: Double,
        var distance: Double,
        var unit: String,
        var page: Int,
        var size: Int
    ) {

    }
}