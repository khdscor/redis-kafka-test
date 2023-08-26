package MyTest.test.domain.article.service;

import MyTest.test.domain.article.dao.ArticleMapper;
import MyTest.test.domain.article.dao.ArticleRepository;
import MyTest.test.domain.article.domain.Article;
import MyTest.test.global.util.PageHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Transactional(readOnly = true)
    public List<Article> findArticle() {

        return articleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Article> findArticlePage(int page, Long memberId) {
        int pageSize = 10;
        Sort sort = Sort.by(Order.asc("id"), Order.desc("content"));
        PageRequest pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());
        Page<Article> articles = articleRepository.findAllByMemberId(memberId, pageable);

        return articles;
    }

    @Transactional(readOnly = true)
    public Slice<Article> findArticleSlice(int page, Long memberId) {
        int pageSize = 10;
        PageRequest pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());
        Slice<Article> articles = articleRepository.findAllByMemberId(memberId, pageable);

        return articles;
    }

    @Transactional(readOnly = true)
    public Page<Article> findArticlePageByMaBatis(int page, Long memberId) {
        int pageSize = 10;
        Sort sort = Sort.by("id").descending();
        PageRequest pageable = PageRequest.of(page, pageSize, sort);
        List<Article> articles = articleMapper.findArticlesInPage(pageable.getPageSize(),
            pageable.getOffset(), memberId, PageHelper.orderBy(sort));
        Long totalCount = articleMapper.countTotalArticles();
        return new PageImpl<>(articles, pageable, totalCount);
    }
}