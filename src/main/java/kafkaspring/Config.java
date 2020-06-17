package kafkaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
@EnableIntegration
public class Config {

    @Autowired
    private SomeClass someClass;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public IntegrationFlow topicFlow() {
            return IntegrationFlows
                    .from (Kafka.messageDrivenChannelAdapter(new ConcurrentMessageListenerContainer<String,String>(new DefaultKafkaConsumerFactory<String, String> (kafkaProperties.buildConsumerProperties()),new ContainerProperties(configProperties.getTopicFrom()))))
                    .handle(someClass)
                    .handle(Kafka.outboundChannelAdapter(new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties())).topic(configProperties.getTopicTo()))
                    .get();
    }
}
