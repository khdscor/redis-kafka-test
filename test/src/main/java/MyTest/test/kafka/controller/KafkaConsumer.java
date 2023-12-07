package MyTest.test.kafka.controller;

import MyTest.test.kafka.dto.TestMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final String TOPIC_NAME = "topic5";

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = TOPIC_NAME)
    public void listenMessage(String jsonMessage) {
        try {
            TestMessage message = objectMapper.readValue(jsonMessage, TestMessage.class);
            System.out.println("수신된 메시지: " + message.getTitle() + " : " + message.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
