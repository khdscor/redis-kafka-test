package MyTest.test.domain.article.api;

import MyTest.test.domain.article.domain.TestEntity;
import MyTest.test.domain.article.service.TestEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestEntityController {

    private final TestEntityService testEntityService;

    @GetMapping("/test/save")
    public ResponseEntity<Void> saveTestEntity() {
        testEntityService.saveEntity();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/test/find")
    public ResponseEntity<TestEntity> findTestEntity() {
        return ResponseEntity.ok().body(testEntityService.findEntity());
    }
}
