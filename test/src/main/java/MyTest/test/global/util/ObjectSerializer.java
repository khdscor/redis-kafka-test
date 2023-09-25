package MyTest.test.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;

public class ObjectSerializer {

    public static <T> String ToJson(T data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> Optional<T> ToObject(String value, Class<T> classType) {
        if (value == null) {
            return Optional.empty();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Optional.of(mapper.readValue(value, classType));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}