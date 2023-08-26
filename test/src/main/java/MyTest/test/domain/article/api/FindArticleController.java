package MyTest.test.domain.article.api;

import MyTest.test.domain.article.domain.Article;
import MyTest.test.domain.article.service.FindArticleService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("pagetest/{page}")
    public ResponseEntity<Page<Article>> findArticlesByPage(@PathVariable("page") int page) {
        return ResponseEntity.ok().body(findArticleService.findArticlePage(page, 1L));
    }

    @GetMapping("slicetest/{page}")
    public ResponseEntity<Slice<Article>> findArticlesBySlice(@PathVariable("page") int page) {
        return ResponseEntity.ok().body(findArticleService.findArticleSlice(page, 1L));
    }

    @GetMapping("mybatistest/{page}")
    public ResponseEntity<Slice<Article>> findArticlesByMyBatis(@PathVariable("page") int page) {
        return ResponseEntity.ok().body(findArticleService.findArticlePageByMaBatis(page, 1L));
    }

}
