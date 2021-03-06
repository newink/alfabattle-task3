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
 * Статическая и редкоменяющаяся информация о банкомате
 * @param address Почтовый адрес АТМ
 * @param addressComments Вспомогательный текстовый комментарий, помогающий пользователю найти АТМ. Например, «справа от входа в торговый комплекс с улицы Кораблестроителей»
 * @param availablePaymentSystems 
 * @param cashInCurrencies Список кодов валют по общероссийскому классификатору, которые АТМ поддерживает для внесения
 * @param cashOutCurrencies Список кодов валют по общероссийскому классификатору, которые АТМ поддерживает для выдачи
 * @param coordinates Широта и долгота места установки АТМ
 * @param deviceId Номер Terminal-ID, присвоенный АТМ в процессинге Банка и передаваемый в информационном обмене между эквайрером, платежной системой и эмитентом.  Значение соответствует ISO-8583 полю 41 (Card Acceptor Terminal Identification)
 * @param nfc 
 * @param publicAccess Доступ к АТМ. Значения: 1 = в помещении кредитной организации в открытом доступе  2 = в помещении кредитной организации в ограниченном доступе  3 = вне помещения кредитной организации в открытом доступе  4 = вне помещения кредитной организации в ограниченном доступе  (значения соответствуют правилам заполнения Ф-250 ЦБ РФ1)  
 * @param recordUpdated Дата/время получения последней информации из master-системы
 * @param services Функции, которыми обладает данный АТМ (значения соответствуют Ф-250 ЦБ РФ)
 * @param supportInfo Информация о службе поддержке
 * @param timeAccess Режим работы ATM
 * @param timeShift Сдвиг времени региона установки конкретного АТМ, относительно UTC. Например, 3
 */
data class ATMDetails (
    /* Почтовый адрес АТМ */
    val address: PostAddress? = null,
    /* Вспомогательный текстовый комментарий, помогающий пользователю найти АТМ. Например, «справа от входа в торговый комплекс с улицы Кораблестроителей» */
    val addressComments: kotlin.String? = null,
    val availablePaymentSystems: kotlin.Array<kotlin.String>? = null,
    /* Список кодов валют по общероссийскому классификатору, которые АТМ поддерживает для внесения */
    val cashInCurrencies: kotlin.Array<kotlin.String>? = null,
    /* Список кодов валют по общероссийскому классификатору, которые АТМ поддерживает для выдачи */
    val cashOutCurrencies: kotlin.Array<kotlin.String>? = null,
    /* Широта и долгота места установки АТМ */
    val coordinates: Coordinates? = null,
    /* Номер Terminal-ID, присвоенный АТМ в процессинге Банка и передаваемый в информационном обмене между эквайрером, платежной системой и эмитентом.  Значение соответствует ISO-8583 полю 41 (Card Acceptor Terminal Identification) */
    val deviceId: kotlin.Int? = null,
    val nfc: kotlin.String? = null,
    /* Доступ к АТМ. Значения: 1 = в помещении кредитной организации в открытом доступе  2 = в помещении кредитной организации в ограниченном доступе  3 = вне помещения кредитной организации в открытом доступе  4 = вне помещения кредитной организации в ограниченном доступе  (значения соответствуют правилам заполнения Ф-250 ЦБ РФ1)   */
    val publicAccess: kotlin.String? = null,
    /* Дата/время получения последней информации из master-системы */
    val recordUpdated: java.time.LocalDateTime? = null,
    /* Функции, которыми обладает данный АТМ (значения соответствуют Ф-250 ЦБ РФ) */
    val services: ATMServices? = null,
    /* Информация о службе поддержке */
    val supportInfo: SupportInfo? = null,
    /* Режим работы ATM */
    val timeAccess: ATMAccess? = null,
    /* Сдвиг времени региона установки конкретного АТМ, относительно UTC. Например, 3 */
    val timeShift: kotlin.Int? = null
) {

}

