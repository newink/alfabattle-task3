package com.alfabattle.contest.resource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AtmController {

    @GetMapping("/atms/nearest1")
    fun getNearestAtm(@RequestParam latitude: String,
                      @RequestParam longitude: String,
                      @RequestParam(defaultValue = "false") payments: Boolean) {


    }

    @GetMapping("/atms/nearest")
    fun getNearestWithAlfik(@RequestParam latitude: String,
                            @RequestParam longitude: String,
                            @RequestParam alfik: Int) {

    }

    @GetMapping("/atms/{id}")
    fun getById(@PathVariable id: String) {

    }
}
