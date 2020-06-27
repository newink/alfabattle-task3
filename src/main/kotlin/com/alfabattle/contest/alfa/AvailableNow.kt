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
 * @param cashIn Доступность АТМ клиенту для внесения денег (функция внесения денег), заполняется для устройств, обладающих функцией приема денег.  Y = АТМ позволяет внести деньги  N = АТМ не позволяет внести деньги (поломка, иная причина)  Z = нет информации о статусе данной функции АТМ  
 * @param cashOut Доступность АТМ клиенту для получения денег (функция выдачи денег), заполняется для устройств, обладающих функцией выдачи денег.  Y = АТМ выдает деньги (деньги получить можно)  N = АТМ не выдает деньги (закончились, поломка)  Z = нет информации о статусе данной функции АТМ  
 * @param online Текущий статус работоспособности АТМ для клиента  Y = АТМ открыт и на связи, хотя бы одна из его функций доступна  N = АТМ закрыт или не на связи, клиент не может совершать операции с АТМ  Z = нет информации о статусе АТМ
 * @param payments Доступность АТМ клиенту для оплаты (функция оплаты товаров и услуг), заполняется для устройств, обладающих функцией оплаты.  Y = АТМ позволяет оплатить (сделать хотя бы один платеж)  N = АТМ не позволяет провести оплату (поломка, иная причина)  Z = нет информации о статусе данной функции АТМ
 */
data class AvailableNow (
    /* Доступность АТМ клиенту для внесения денег (функция внесения денег), заполняется для устройств, обладающих функцией приема денег.  Y = АТМ позволяет внести деньги  N = АТМ не позволяет внести деньги (поломка, иная причина)  Z = нет информации о статусе данной функции АТМ   */
    val cashIn: kotlin.String? = null,
    /* Доступность АТМ клиенту для получения денег (функция выдачи денег), заполняется для устройств, обладающих функцией выдачи денег.  Y = АТМ выдает деньги (деньги получить можно)  N = АТМ не выдает деньги (закончились, поломка)  Z = нет информации о статусе данной функции АТМ   */
    val cashOut: kotlin.String? = null,
    /* Текущий статус работоспособности АТМ для клиента  Y = АТМ открыт и на связи, хотя бы одна из его функций доступна  N = АТМ закрыт или не на связи, клиент не может совершать операции с АТМ  Z = нет информации о статусе АТМ */
    val online: kotlin.String? = null,
    /* Доступность АТМ клиенту для оплаты (функция оплаты товаров и услуг), заполняется для устройств, обладающих функцией оплаты.  Y = АТМ позволяет оплатить (сделать хотя бы один платеж)  N = АТМ не позволяет провести оплату (поломка, иная причина)  Z = нет информации о статусе данной функции АТМ */
    val payments: kotlin.String? = null
) {

}

