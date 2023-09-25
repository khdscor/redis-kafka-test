package MyTest.test.domain.article.api;

import MyTest.test.domain.article.dto.TestRedisDto;
import MyTest.test.domain.article.service.TestRedisService;
import MyTest.test.global.util.ObjectSerializer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedisController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private TestRedisService testRedisService;

    @GetMapping("/setFruit")
    public String setFruit(@RequestParam String name) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("fruit", name);

        return "saved.";
    }

    @GetMapping("/getFruit")
    public String getFruit() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        return ops.get("fruit");
    }

    @GetMapping("/getUser/{userId}")
    public String getUserProfile(@PathVariable("userId") String userId) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String cachedName = ops.get("key:" + userId);
        if (cachedName != null) {
            System.out.println("redis에서 기록된 정보를 빼옴.");
            return cachedName;
        }
        // redis에 없을 경우 service 로직을 통해 name을 가져와서 등록
        String name = "사용자 이름";
        ops.set("key:" + userId, name, 20, TimeUnit.SECONDS);
        System.out.println("데이터베이스에서 기록된 정보를 빼옴.");
        return name;
    }

    @GetMapping("/getUser/spring/{userId}")
    public TestRedisDto getUserProfileBySpringCache(@PathVariable("userId") String userId) {

        String value = testRedisService.testObjectInRedis(userId);
        TestRedisDto dto = ObjectSerializer.ToObject(value, TestRedisDto.class)
            .orElseThrow(() -> new RuntimeException("테스트입니다."));
        return dto;
    }

    @GetMapping("/getUser/spring/many/{userId}")
    public List<TestRedisDto> getUsersProfileBySpringCache(@PathVariable("userId") String userId) {
        String value = testRedisService.testObjectListInRedis(userId);
        List<TestRedisDto> dto = ObjectSerializer.ToObject(value, List.class)
            .orElseThrow(() -> new RuntimeException("테스트입니다."));
        return dto;
    }
}