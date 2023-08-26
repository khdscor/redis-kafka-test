package MyTest.test.Article;

import MyTest.test.domain.article.dao.ArticleMapper;
import MyTest.test.domain.article.dao.ArticleRepository;
import MyTest.test.domain.article.domain.Article;
import MyTest.test.featureFactory.ArticleFeatureFactory;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
public class BulkInsertTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void CreateArticleList() {

        EasyRandom articleBuilder = ArticleFeatureFactory.create(1L);

        var stopWatch = new StopWatch();
        stopWatch.start();
        List<Article> articles = IntStream.range(0, 1000000)
            .parallel()
            .mapToObj(i -> articleBuilder.nextObject(Article.class))
            .collect(Collectors.toList());
        stopWatch.stop();
        System.out.println("객체 생성시간: " + stopWatch.getTotalTimeSeconds());

        var queryStopWatch = new StopWatch();
        queryStopWatch.start();
        int result = 0;
        for (int i = 0; i < articles.size(); i += 1000) {
            List<Article> temps = articles.subList(i, i + 1000);
            result += articleMapper.saveArticleList(temps);
        }
        queryStopWatch.stop();
        System.out.println("DB 삽입시간: " + (queryStopWatch.getTotalTimeSeconds()));
        System.out.println("insert 값: " + result);
    }
}