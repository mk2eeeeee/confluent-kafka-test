package com.sangwoo.dmakerkt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.sangwoo.dmakerkt.entity.Developer
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class DmakerKotlinApplication

fun main(args: Array<String>) {
	runApplication<DmakerKotlinApplication>(*args)

//    val props = Properties()
//    props[StreamsConfig.APPLICATION_ID_CONFIG] = APPLICATION_NAME
//    props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_SERVERS
//    props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
//    props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
//    props["security.protocol"] = "SASL_SSL"
//    props["sasl.mechanism"] = "PLAIN"
//    props["ssl.protocol"] = "TLSv1.2"
//    props["ssl.enabled.protocols"] = "TLSv1.2"
//    props["sasl.jaas.config"] =
//        "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"AA7XRJJ7SQ5YQXPP\" password=\"R7tqG0gk+r+W5QAQh1jv2fdSa3lMUp8rwP5cXRc/F4NnNL6jus0C5FWu6s6eteKa\";"
// AVRO -> 스키마사용 -> 찾아보기
    // DSL 사용하기
    // 스트림 토폴로지를 정의하기 위한 StreamsBuilder (토폴로지 구축)
//    val builder = StreamsBuilder()

    /*
        - 소스 프로세서 동작
        user_score 토픽으로부터 KStream 객체를 만든다.
         */
//	val userScore = builder.stream<String, String>(TOPIC_USER_SCORE, Consumed.with(Serdes.String(), Serdes.String()))
//    val developer = builder.stream<String, String>(TOPIC)

    /*
        - 스트림 프로세서 동작
        user_score 토픽에서 가져온 데이터 중 value가 10을 넘는 경우의 값만 남도록 필터링하여 KStream 객체를 새롭게 생성
         */
//    val filteredStream = developer.filter { key: String?, value: String ->
//        value.length > 6
//    }
//	val filteredStream = developer.mapValues { v ->
//		jsonMapper().readValue(v, Developer::class.java)
//	}
//	val filteredStream = developer.filter { key: String?, value: String ->
//        ObjectMapper().registerKotlinModule().registerModule(JavaTimeModule()).readValue(value, Developer::class.java).developerSkillType.name == "BACK_END"
//    }

//    val filteredStream = developer.filter { key, value ->
//        ObjectMapper().registerKotlinModule().readValue(value, Developer::class.java).developerSkillType.name == "BACK_END"
//    }
//	}

    /*
        - 싱크 프로세서 동작
        pass_user_score 토픽으로 KStream 데이터를 전달한다.
         */
//    filteredStream.to(TOPIC_SENDER)
//
//    val streams = KafkaStreams(builder.build(), props)
//    streams.start()
}

//const val APPLICATION_NAME = "mk-streams-dmaker" // 애플리케이션 아이디로 사용할 애플리케이션 이름
//const val BOOTSTRAP_SERVERS = "pkc-e82om.ap-northeast-2.aws.confluent.cloud:9092" // 카프카 서버 정보
//const val TOPIC = "mk-dmaker" // 카피해올 데이터가 있는 토픽명
//const val TOPIC_SENDER = "mk-dmaker-sender" // 카피한 데이터를 저장할 토픽명
