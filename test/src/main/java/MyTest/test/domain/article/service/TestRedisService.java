package MyTest.test.domain.article.service;

import MyTest.test.domain.article.dto.TestDataDto;
import MyTest.test.domain.article.dto.TestRedisDto;
import MyTest.test.global.util.ObjectSerializer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestRedisService {

    @Cacheable(cacheNames = "testCache", key = "#userId")
    public String testObjectInRedis(String userId) {
        System.out.println("1111111111");
        ArrayList<TestDataDto> dataDtoList = new ArrayList<>();
        dataDtoList.add(new TestDataDto(1L, "test1"));
        dataDtoList.add(new TestDataDto(2L, "test2"));

        String value = ObjectSerializer.ToJson(new TestRedisDto(10L, "title", "content", new Date(),
            dataDtoList));

        return value;
    }

    @Cacheable(cacheNames = "testCache2", key = "#userId")
    public String testObjectListInRedis(String userId) {
        System.out.println("데이터베이스에 접근");
        ArrayList<TestDataDto> dataDtoList = new ArrayList<>();
        dataDtoList.add(new TestDataDto(1L, "test1"));
        dataDtoList.add(new TestDataDto(2L, "test2"));
        List<TestRedisDto> redisDtoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            redisDtoList.add(new TestRedisDto(10L, "title", "content", new Date(),
                dataDtoList));
        }

        String value = ObjectSerializer.ToJson(redisDtoList);

        return value;
    }
}