package com.mk.dmaker.controller

import com.mk.dmaker.dto.CreateDeveloper
import com.mk.dmaker.service.DMakerService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@EnableKafka
class KafkaController(
    val kafkaProducerService: DMakerService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/sendDeveloper",consumes = ["application/json"], produces = ["application/json"])
    fun sendDeveloper(@Valid @RequestBody request: CreateDeveloper.Request) {
        log.info("sendDeveloper")
        kafkaProducerService.sendDeveloper(request)
    }
}
