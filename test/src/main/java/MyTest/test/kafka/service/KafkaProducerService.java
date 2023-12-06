package MyTest.test.kafka.service;

import MyTest.test.kafka.dto.TestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerService {

    private static final String TOPIC_NAME = "topic5";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, TestMessage> newKafkaTemplate;

    public void send(String message){
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendJson(TestMessage message){
        newKafkaTemplate.send(TOPIC_NAME, message);
    }
}
