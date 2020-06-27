/**
* Сервис проверки статуса банкоматов
* Сервис, возвращающий информацию о банкоматах Альфа-Банка
*
* OpenAPI spec version: 1.0.0
* Contact: apisupport@alfabank.ru
*
* NOTE: This class is auto generated by the swagger code generator program.
* https://github.com/swagger-api/swagger-codegen.git
* Do not edit the class manually.
*/
package com.alfabattle.contest.alfa


/**
 * 
 * @param atms Список статусов банкоматов
 * @param bankLicense Номер лицензии Альфа-Банка
 */
data class BankATMStatus (
    /* Список статусов банкоматов */
    val atms: kotlin.Array<ATMStatus>? = null,
    /* Номер лицензии Альфа-Банка */
    val bankLicense: kotlin.String? = null
) {

}
