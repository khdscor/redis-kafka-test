package MyTest.test.domain.article.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TestRedisDto {

    private Long id;

    private String title;

    private String content;

    private Date createdDate;

    private List<TestDataDto> testDataDtoList;
}
