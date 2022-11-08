package com.example.shopSearch.shop.domain.vo

/**
 * @author Brian
 * @since 2022/11/08
 */
enum class Category(val title: String) {
    FOOD_BEVERAGE("식/음료"),
    MART("편의점/마트"),
    SERVICE("서비스업종"),
    FASHION("패션의류"),
    ACCESSORY("패션잡화"),
    ETC("그외/마켓")
}