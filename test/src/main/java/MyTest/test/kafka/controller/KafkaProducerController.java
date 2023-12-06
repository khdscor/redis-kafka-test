package MyTest.test.kafka.controller;

import MyTest.test.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/publish")
    public String publish(String message) {
        kafkaProducerService.send(message);
        return "succeed!";
    }
}
