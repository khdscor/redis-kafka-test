package MyTest.test.domain.article.service;

import MyTest.test.domain.article.dao.ArticleRepository;
import MyTest.test.domain.article.domain.Article;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<Article> findArticle() {

        return articleRepository.findAll();
    }
}