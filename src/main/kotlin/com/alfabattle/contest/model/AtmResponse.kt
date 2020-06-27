package com.alfabattle.contest.model

data class AtmResponse(val city: String,
                       val deviceId: Int,
                       val latitude: String,
                       val location: String,
                       val longitude: String,
                       val payments: Boolean)
