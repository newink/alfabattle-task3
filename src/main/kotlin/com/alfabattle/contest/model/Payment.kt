package com.alfabattle.contest.model

import java.math.BigDecimal

data class Payment(val ref: String,
                   val userId: String,
                   val recipientId: String,
                   val categoryId: Int,
                   val desc: String,
                   val amount: BigDecimal)
