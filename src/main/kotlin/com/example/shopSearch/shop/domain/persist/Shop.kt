package com.example.shopSearch.shop.domain.persist

import com.example.shopSearch.shop.domain.vo.Category
import com.example.shopSearch.shop.domain.vo.DeliveryTipPerDistance
import com.example.shopSearch.shop.domain.vo.DetailCategory
import com.example.shopSearch.shop.domain.vo.Status
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.GeoPointField
import org.springframework.data.elasticsearch.core.geo.GeoPoint

/**
 * @author Brian
 * @since 2022/11/08
 */
@Document(indexName = "shops")
class Shop(
    @field:Id
    @field:Field(type = FieldType.Keyword, name = "shop_id")
    var shopId: String = "",
    @field:Field(type = FieldType.Text, name = "shop_name")
    var shopName: String = "",
    @field:Field(type = FieldType.Keyword, name = "status")
    var status: Status = Status.CLOSE,
    @field:GeoPointField
    var location: GeoPoint = GeoPoint(0.0, 0.0),
    @field:Field(type = FieldType.Nested, name = "delivery_tip_per_distance_list")
    var deliveryTipPerDistanceList: List<DeliveryTipPerDistance> = listOf(),
    @field:Field(type = FieldType.Keyword, name = "category")
    var category: Category = Category.ETC,
    @field:Field(type = FieldType.Keyword, name = "detail_category")
    var detailCategory: DetailCategory = DetailCategory.ETC_ALL,
    @field:Field(type = FieldType.Double, name = "total_score")
    var totalScore: Double = 0.0,
    @field:Field(type = FieldType.Integer, name = "review_number")
    var reviewNumber: Int = 0,
    @field:Field(type = FieldType.Double, name = "average_score")
    var averageScore: Double = 0.0,
    @field:Field(type = FieldType.Keyword, name = "business_number")
    var businessNumber: String = ""
) {

}