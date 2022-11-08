package com.example.shopSearch.shop.domain.dto

import com.example.shopSearch.shop.domain.vo.Category
import com.example.shopSearch.shop.domain.vo.DetailCategory

/**
 * @author Brian
 * @since 2022/11/09
 */
sealed class ShopRequest {

    data class CategoryWithIn(
        var category: Category,
        var latitude: Double,
        var longitude: Double,
        var distance: Double,
        var unit: String,
        var page: Int,
        var size: Int
    ) {

    }

    data class DetailCategoryWithIn(
        var detailCategory: DetailCategory,
        var latitude: Double,
        var longitude: Double,
        var distance: Double,
        var unit: String,
        var page: Int,
        var size: Int
    ) {

    }

    data class ShopNameWithIn(
        var shopName: String,
        var latitude: Double,
        var longitude: Double,
        var distance: Double,
        var unit: String,
        var page: Int,
        var size: Int
    ) {

    }

    data class WithIn(
        var latitude: Double,
        var longitude: Double,
        var distance: Double,
        var unit: String,
        var page: Int,
        var size: Int
    ) {

    }
}