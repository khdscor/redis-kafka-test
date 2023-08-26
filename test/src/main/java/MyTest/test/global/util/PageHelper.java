package MyTest.test.global.util;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

public class PageHelper {

    public static String orderBy(Sort sort) {
        if (sort.isEmpty()) {
            return "id DESC";
        }
        List<String> sorted = sort.stream()
            .map(order -> order.getProperty() + " " + order.getDirection())
            .collect(Collectors.toList());
        String test = String.join(", ", sorted);
        System.out.println(test);
        return test;
    }
}