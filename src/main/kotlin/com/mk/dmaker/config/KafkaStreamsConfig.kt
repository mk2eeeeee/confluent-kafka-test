package com.mk.dmaker.config

import org.apache.kafka.common.config.SaslConfigs
import org.apache.kafka.common.config.SslConfigs
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration
import org.springframework.kafka.config.KafkaStreamsConfiguration

@Configuration
@EnableKafka
@EnableKafkaStreams
class KafkaStreamsConfig(
    @Value("\${spring.kafka.bootstrap-servers}") val bootstrapServer: String,
    @Value("\${spring.kafka.properties.sasl.jaas.config}") val saslJaasConfig: String,
) {

    @Bean(name = [KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME])
    fun kStreamsConfig(): KafkaStreamsConfiguration {
        return KafkaStreamsConfiguration(
            mapOf(
                StreamsConfig.APPLICATION_ID_CONFIG to "dmaker",
                StreamsConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServer,
                StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG to Serdes.String().javaClass.name,
                StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG to Serdes.String().javaClass.name,
                StreamsConfig.SECURITY_PROTOCOL_CONFIG to "SASL_SSL",
                SslConfigs.DEFAULT_SSL_PROTOCOL to "TLSv1.2",
                SaslConfigs.SASL_MECHANISM to "PLAIN",
                SaslConfigs.SASL_JAAS_CONFIG to saslJaasConfig
            )
        )
    }
}
