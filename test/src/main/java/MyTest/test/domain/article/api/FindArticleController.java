package MyTest.test.domain.article.api;

import MyTest.test.domain.article.domain.Article;
import MyTest.test.domain.article.service.FindArticleService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindArticleController {

    private final FindArticleService findArticleService;

    public FindArticleController(FindArticleService findArticleService) {
        this.findArticleService = findArticleService;
    }

    @GetMapping("/test")
    public ResponseEntity<List<Article>> findArticles() {
        return ResponseEntity.ok().body(findArticleService.findArticle());
    }
}
