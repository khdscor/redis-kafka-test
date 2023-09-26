package MyTest.test.domain.article.api;

import MyTest.test.domain.article.dto.TestRedisDto;
import MyTest.test.domain.article.service.TestRedisService;
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

        return testRedisService.testObjectInRedis(userId);
    }
}