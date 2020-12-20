package id.project.redis.redisletuce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisLettuceConfiguration {

    @Bean
    public LettuceConnectionFactory redisLettuceConnectionFactory(){
        LettuceConnectionFactory lettuceFactory = new LettuceConnectionFactory();
        lettuceFactory.setHostName("127.0.0.1");
        lettuceFactory.setPort(6379);
        lettuceFactory.afterPropertiesSet();
        return lettuceFactory;
    }

    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisLettuceConnectionFactory());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
