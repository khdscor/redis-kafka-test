package MyTest.test.domain.article.dao;

import MyTest.test.domain.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByMemberId(Long memberId, Pageable pageable);
}