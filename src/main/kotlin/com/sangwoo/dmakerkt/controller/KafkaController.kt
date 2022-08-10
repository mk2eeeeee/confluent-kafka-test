package com.sangwoo.dmakerkt.controller

import com.sangwoo.dmakerkt.dto.CreateDeveloper
import com.sangwoo.dmakerkt.service.DMakerService
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
        log.info("mk-controller: kafkaProducerServiceDemo.sendDeveloper")
        kafkaProducerService.sendDeveloper(request)
    }
}