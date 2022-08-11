package com.mk.dmaker.streams.process

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.mk.dmaker.entity.Developer
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Processor {

    val consumeTopic = "mk-dmaker"
    val produceTopic = "mk-dmaker-sender"

    @Autowired
    fun buildPipeline(streamsBuilder: StreamsBuilder) {
        streamsBuilder.stream(consumeTopic, Consumed.with(Serdes.String(), Serdes.String()))
            .filter { k, v ->
                ObjectMapper().registerKotlinModule().registerModule(JavaTimeModule())
                    .readValue(v, Developer::class.java)
                    .developerSkillType.name == "BACK_END"
            }
            .to(produceTopic)
    }
}
