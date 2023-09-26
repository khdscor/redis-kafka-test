package MyTest.test.global.config;

import java.time.Duration;
import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCashConfig {

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
//            .disableCachingNullValues()
//            .entryTtl(Duration.ofSeconds(500))
//            .computePrefixWith(CacheKeyPrefix.simple())
//            .serializeKeysWith(
//                RedisSerializationContext.SerializationPair.fromSerializer(
//                    new StringRedisSerializer())
//            )
//            .serializeValuesWith(
//                RedisSerializationContext.SerializationPair.fromSerializer(
//                    new StringRedisSerializer())
//            );
//
//        HashMap<String, RedisCacheConfiguration> configMap = new HashMap<>();
//        configMap.put("tfstCache", RedisCacheConfiguration.defaultCacheConfig()
//            .entryTtl(Duration.ofSeconds(100)));
//
//        return RedisCacheManager
//            .RedisCacheManagerBuilder
//            .fromConnectionFactory(connectionFactory)
//            .cacheDefaults(configuration)
//            .withInitialCacheConfigurations(configMap)
//            .build();
//
//    }
}
