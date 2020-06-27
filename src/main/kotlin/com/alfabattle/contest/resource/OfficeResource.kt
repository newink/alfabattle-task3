package com.alfabattle.contest.resource

import com.alfabattle.contest.config.LoggerDelegate
import com.alfabattle.contest.model.Branch
import com.alfabattle.contest.repository.BranchRepository
import com.alfabattle.contest.repository.QueueRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import kotlin.math.roundToLong

@RestController
class OfficeResource(private val branchRepository: BranchRepository, private val queueRepository: QueueRepository) {

    val log by LoggerDelegate()

    @GetMapping("/branches/{id}")
    fun getOffice(@PathVariable id: Int): ResponseEntity<Any> {
        val findById = branchRepository.findById(id)

        return if (findById.isPresent) {
            ResponseEntity.ok(findById)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("status" to "branch not found"))
        }
    }

    @GetMapping("/branches/{id}/predict")
    fun getOfficePrediction(@PathVariable id: Int,
                            @RequestParam dayOfWeek: Int,
                            @RequestParam hourOfDay: Int): ResponseEntity<Any> {
        val findById = branchRepository.findById(id)

        return if (findById.isPresent) {
            val queues = queueRepository.findByBranchId(id)
            val filtered = queues.filter {
                it.startTimeOfWait.hour == hourOfDay && it.date.dayOfWeek.value == dayOfWeek
            }
            val waitSeconds = filtered.map { Duration.between(it.startTimeOfWait, it.endTimeOfWait).seconds }
            val seconds = med(waitSeconds)

            ResponseEntity.ok(findById.get().withPrediction(seconds, dayOfWeek, hourOfDay))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("status" to "branch not found"))
        }
    }

    @GetMapping("/branches")
    fun getNearest(@RequestParam lat: Double,
                   @RequestParam lon: Double): ResponseEntity<Any> {
        val all = branchRepository.findAll()
        val map = all.map { it.withDistance(distance(lat, lon, it.lat, it.lon)) }
        val min = map.minBy { it.distance }
        log.info("{}", all)
        log.info("{}", map)
        return ResponseEntity.ok(min!!)
    }


    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Long {

        val unit = 'K'
        val theta = lon1 - lon2
        var dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta))
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515
        if (unit == 'K') {
            dist = dist * 1.609344
        } else if (unit == 'N') {
            dist = dist * 0.8684
        }
        return (dist * 1000).roundToLong()
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/ /*::  This function converts decimal degrees to radians             :*/ /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/ /*::  This function converts radians to decimal degrees             :*/ /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

    fun Branch.withDistance(distance: Long): BranchWithDistance {
        return BranchWithDistance(id, title, lon, lat, address, distance)
    }

    fun Branch.withPrediction(predicting: Long, dayOfWeek: Int, hourOfDay: Int): BranchWithPrediction {
        return BranchWithPrediction(id, title, lon, lat, address, dayOfWeek, hourOfDay, predicting)
    }

    data class BranchWithDistance(var id: Int,
                                  var title: String,
                                  var lon: Double,
                                  var lat: Double,
                                  var address: String,
                                  var distance: Long)

    data class BranchWithPrediction(var id: Int,
                                    var title: String,
                                    var lon: Double,
                                    var lat: Double,
                                    var address: String,
                                    var dayOfWeek: Int,
                                    var hourOfDay: Int,
                                    var predicting: Long)

    data class TimeOfDay(val hour: Int, val day: Int)

    fun med(list: List<Long>) = list.sorted().let {
        (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
    }
}
