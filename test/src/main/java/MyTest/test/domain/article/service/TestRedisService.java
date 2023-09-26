package MyTest.test.domain.article.service;

import MyTest.test.domain.article.dto.TestDataDto;
import MyTest.test.domain.article.dto.TestRedisDto;
import MyTest.test.global.util.ObjectSerializer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestRedisService {

    private final ObjectSerializer objectSerializer;

    public TestRedisDto testObjectInRedis(String userId) {
        ArrayList<TestDataDto> dataDtoList = new ArrayList<>();
        dataDtoList.add(new TestDataDto(1L, "test1"));
        dataDtoList.add(new TestDataDto(2L, "test2"));
        Optional<TestRedisDto> dto = objectSerializer.getData(userId, TestRedisDto.class);
        if (dto.isPresent()) {
            return dto.get();
        }
        TestRedisDto dto2;
        System.out.println("데이터베이스 접근");
        // dto에 데이터베이스로부터 데이터 전달
        dto2 = new TestRedisDto(10L, "title", "content", new Date(),
            dataDtoList);
        objectSerializer.saveData(userId, dto2);
        return dto2;
    }
}