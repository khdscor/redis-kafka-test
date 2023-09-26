package MyTest.test.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectSerializer {

    private final RedisTemplate<String, String> redisTemplate;

    public <T> void saveData(String key, T data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String value = mapper.writeValueAsString(data);
            redisTemplate.opsForValue().set("testCache::" + key, value, 20, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("에러가 발생하였습니다.");
        }
    }

    public <T> Optional<T> getData(String key, Class<T> classType) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get("testCache::" + key);
        if (value == null) {
            return Optional.empty();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return Optional.of(mapper.readValue(value, classType));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}