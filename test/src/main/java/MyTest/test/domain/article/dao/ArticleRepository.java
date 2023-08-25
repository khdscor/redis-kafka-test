package MyTest.test.domain.article.dao;

import MyTest.test.domain.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}