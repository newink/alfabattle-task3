package com.alfabattle.contest.resource

import com.alfabattle.contest.promo.PromoMatrix
import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Integer.parseInt
import java.math.BigDecimal
import javax.annotation.PostConstruct

@RestController
class PromoController {

    private lateinit var promoMatrix: PromoMatrix

    private var items: MutableMap<Int, Item> = mutableMapOf()
    private var groups: MutableMap<String, Group> = mutableMapOf()

    @PostConstruct
    fun init() {
        val itemsString = PromoController::class.java.classLoader.getResource("items.csv").readText()
        val itemsCsv = CsvReader().readAll(itemsString)
        itemsCsv.forEach { row -> items[parseInt(row[0])] = Item(parseInt(row[0]), row[1], row[2], BigDecimal(row[3])) }

        val groupsString = PromoController::class.java.classLoader.getResource("groups.csv").readText()
        val groupsCsv = CsvReader().readAll(groupsString)
        groupsCsv.forEach { row -> groups[row[0]] = Group(row[0], row[1]) }
    }

    @PostMapping("/promo")
    fun promo(@RequestBody promoMatrix: PromoMatrix) {
        this.promoMatrix = promoMatrix
    }

//    @PostMapping("/receipt")
//    fun receipt(@RequestBody shoppingCart: ShoppingCart): ResponseEntity<FinalPriceReceipt> {
//
//    }

    data class Item(val id: Int, val name: String, val groupId: String, val price: BigDecimal)
    data class Group(val id: String, val name: String)
}
