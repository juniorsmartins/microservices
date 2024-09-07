package microsservices.micro_gatewayserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisConnectionTester {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisConnectionTester(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        testRedisConnection();
    }

    public void testRedisConnection() {
        try {
            redisTemplate.opsForValue().set("testKey", "testValue");
            String value = redisTemplate.opsForValue().get("testKey");
            System.out.println("Value from Redis: " + value);
        } catch (Exception e) {
            System.err.println("Error testing Redis connection: " + e.getMessage());
        }
    }
}
