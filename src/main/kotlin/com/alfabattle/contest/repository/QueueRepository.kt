package com.alfabattle.contest.repository

import com.alfabattle.contest.model.QueueLog
import org.springframework.data.repository.CrudRepository

interface QueueRepository : CrudRepository<QueueLog, Int> {

    fun findByBranchId(branchesId: Int): List<QueueLog>
}
