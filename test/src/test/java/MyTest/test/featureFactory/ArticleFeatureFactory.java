package MyTest.test.featureFactory;


import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import MyTest.test.domain.article.domain.Article;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.function.Predicate;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class ArticleFeatureFactory {

    static public EasyRandom create(Long memberId) {
        Predicate<Field> articleIdPredicate = named("id").and(ofType(Long.class))
            .and(inClass(Article.class));
        Predicate<Field> memberIdPredicate = named("memberId").and(ofType(Long.class))
            .and(inClass(Article.class));
        EasyRandomParameters param = new EasyRandomParameters().excludeField(articleIdPredicate)
            .dateRange(LocalDate.of(2022, 5, 1), LocalDate.of(2023, 7, 22))
            .stringLengthRange(4, 20).randomize(memberIdPredicate, () -> memberId);
        return new EasyRandom(param);
    }
}