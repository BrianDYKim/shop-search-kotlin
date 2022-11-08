package com.example.shopSearch.shop.domain.vo

import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

/**
 * @author Brian
 * @since 2022/11/08
 */
data class DeliveryTipPerDistance(
    @field:Field(type = FieldType.Double)
    var distance: Double = 0.0,
    @field:Field(type = FieldType.Integer)
    var price: Int = 0
) {

}