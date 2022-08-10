package com.sangwoo.dmakerkt.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
@EnableKafka
class KafkaConsumerService {

    private val log = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["mk-dmaker"])
    fun consumer(message: String) {
        log.info("KafkaConsumerService: $message")
    }

}
