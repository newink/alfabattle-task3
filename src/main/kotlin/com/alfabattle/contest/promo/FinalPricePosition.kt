/**
* На промоигле
* Задача для Alfa Battle от X5 Retail Group
*
* OpenAPI spec version: 1.0.0
* 
*
* NOTE: This class is auto generated by the swagger code generator program.
* https://github.com/swagger-api/swagger-codegen.git
* Do not edit the class manually.
*/
package com.alfabattle.contest.promo


/**
 * Результат расчета цены для одной позиции
 * @param id ID товара
 * @param name Наименование товара
 * @param price Цена после применения скидки
 * @param regularPrice Цена до применения скидки
 */
data class FinalPricePosition (
    /* ID товара */
    val id: kotlin.String? = null,
    /* Наименование товара */
    val name: kotlin.String? = null,
    /* Цена после применения скидки */
    val price: java.math.BigDecimal? = null,
    /* Цена до применения скидки */
    val regularPrice: java.math.BigDecimal? = null
) {

}
