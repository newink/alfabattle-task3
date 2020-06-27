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
 * @param mode Доступность АТМ для клиента, принимает следующие значения:  alltime = круглосуточно  schedule = по расписанию работы организации. В этом случае расписание указывается в поле schedule.
 * @param schedule Указывается, если mode=schedule. Массив [DD:hhmm–hhmm,DD:hhmm–hhmm,DD:hhmm–hhmm,…] DD = [MO, TU, WE, TH, FR, SA, SU] hh = 00..24 mm = 00..59 Время указывается местное (места установки АТМ) Если в течение дня есть перерыв, то указывается каждый диапазон времени в течение одного дня. Например, MO:0800-1300,MO:1400-2100,TU:0800-1300,TU:1400-2100
 */
data class ATMAccess(
        /* Доступность АТМ для клиента, принимает следующие значения:  alltime = круглосуточно  schedule = по расписанию работы организации. В этом случае расписание указывается в поле schedule. */
        val mode: Mode? = null,
        /* Указывается, если mode=schedule. Массив [DD:hhmm–hhmm,DD:hhmm–hhmm,DD:hhmm–hhmm,…] DD = [MO, TU, WE, TH, FR, SA, SU] hh = 00..24 mm = 00..59 Время указывается местное (места установки АТМ) Если в течение дня есть перерыв, то указывается каждый диапазон времени в течение одного дня. Например, MO:0800-1300,MO:1400-2100,TU:0800-1300,TU:1400-2100 */
        val schedule: kotlin.String? = null
) {

    /**
     * Доступность АТМ для клиента, принимает следующие значения:  alltime = круглосуточно  schedule = по расписанию работы организации. В этом случае расписание указывается в поле schedule.
     * Values: alltime,schedule,noinfo
     */
    enum class Mode(val value: kotlin.String) {

        alltime("alltime"),

        schedule("schedule"),

        noinfo("noinfo");

    }

}
