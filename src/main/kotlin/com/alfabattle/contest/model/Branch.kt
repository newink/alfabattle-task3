package com.alfabattle.contest.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "branches")
data class Branch(@Id var id: Int,
                  var title: String,
                  var lon: Double,
                  var lat: Double,
                  var address: String)

@Entity
@Table(name = "queue_log")
data class QueueLog(@Id var id: Int,
                    @Column(name = "data")
                    var date: LocalDateTime,
                    @Column(name = "start_time_of_wait")
                    var startTimeOfWait: LocalDateTime,
                    @Column(name = "end_time_of_wait")
                    var endTimeOfWait: LocalDateTime,
                    @Column(name = "end_time_of_service")
                    var endTimeOfService: LocalDateTime,
                    @Column(name = "branches_id")
                    var branchId: Int)
