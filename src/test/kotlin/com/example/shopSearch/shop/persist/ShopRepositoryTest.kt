package com.example.shopSearch.shop.persist

import com.example.shopSearch.shop.domain.persist.Shop
import com.example.shopSearch.shop.domain.persist.ShopAdvancedRepository
import com.example.shopSearch.shop.domain.vo.Category
import com.example.shopSearch.shop.domain.vo.DeliveryTipPerDistance
import com.example.shopSearch.shop.domain.vo.DetailCategory
import com.example.shopSearch.shop.domain.vo.Status
import io.kotest.common.runBlocking
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.geo.GeoPoint
import java.util.UUID

/**
 * @author Brian
 * @since 2022/11/09
 */
@SpringBootTest
internal class ShopRepositoryTest @Autowired constructor(
    private val shopRepository: ShopAdvancedRepository
) {

    @Test
    fun createTest(): Unit = runBlocking {
        val shop = generateMockShop("파리바게트 시지사월점", 35.8394218, 128.7136896)

        val savedShop = shopRepository.save(shop).awaitSingle()

        with(savedShop) {
            println(shopName)
        }
    }

    @Test
    @DisplayName("POI 반경 검색 테스트")
    fun poiSearchTest(): Unit = runBlocking {
        // given
        val searchPoint = GeoPoint(35.831728870626556, 128.7585397591836) // 영남대학교 위치
        val distance = 10.0
        val unit = "km"
        val pageRequest = PageRequest.of(0, 100)

        // when
        val shopList = shopRepository.withInSearch(searchPoint, distance, unit, pageRequest)
            .asFlow()
            .buffer(capacity = 100)
            .toList()

        shopList.forEach { println(it.shopName) }
    }

    private fun generateMockShop(shopName: String, latitude: Double, longitude: Double): Shop = Shop(
        shopId = UUID.randomUUID().toString(),
        shopName = shopName,
        location = GeoPoint(latitude, longitude),
        status = Status.CLOSE,
        deliveryTipPerDistanceList = listOf(DeliveryTipPerDistance(10.0, 3000)),
        category = Category.ETC,
        detailCategory = DetailCategory.ETC_ALL,
        totalScore = 10.0,
        reviewNumber = 1,
        averageScore = 10.0,
        businessNumber = "3333-3333-3333"
    )
}