package com.nesoy.testcontainer.step1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nesoy.testcontainer.RedisBackedCache;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DirtiesContext
public class RedisBackedCacheIntTest {

    private RedisBackedCache underTest;

    @Container
    public GenericContainer redis = new GenericContainer<>("redis:5.0.3-alpine")
//            .withEnv("FOO", "BAR") // 환경 변수 지정가능
//            .withCommand("command Test") // 실행될 명령어도 지정가능
//            .withNetwork(Network.newNetwork())
            .withExposedPorts(6379) // 노출될 포트 지정도 가능
            //.withVolumesFrom()
            ;

    @BeforeEach
    public void setUp() {
        String address = redis.getContainerIpAddress();
        Integer port = redis.getFirstMappedPort();

        // Now we have an address and port for Redis, no matter where it is running
        underTest = new RedisBackedCache(address, port);
    }

    @Test
    public void testSimplePutAndGet() {
        underTest.put("test", "example");

        String retrieved = underTest.get("test");
        assertEquals("example", retrieved);
    }

    @AfterEach
    public void cleanUp() {
        redis.close();
    }
}