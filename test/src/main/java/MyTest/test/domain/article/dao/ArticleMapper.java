package MyTest.test.domain.article.dao;

import MyTest.test.domain.article.domain.Article;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    int saveArticleList(List<Article> articles);
}