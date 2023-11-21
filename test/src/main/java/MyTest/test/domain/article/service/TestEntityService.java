package MyTest.test.domain.article.service;

import MyTest.test.domain.article.dao.TestEntityRepository;
import MyTest.test.domain.article.domain.TestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TestEntityService {

    private final TestEntityRepository testEntityRepository;

    public void saveEntity(){
        ArrayList<String> tests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tests.add("test: " + i);
        }
        TestEntity test = TestEntity.builder()
                .title("테스트입니다.")
                .columns(tests).build();
        testEntityRepository.save(test);
    }

    public TestEntity findEntity(){
        return testEntityRepository.findAll().get(0);
    }
}
