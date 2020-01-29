package com.nesoy.testcontainer.step2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nesoy.testcontainer.RedisBackedCache;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class dockerComposeTest {
    private RedisBackedCache underTest;

    private final int REDIS_PORT = 6379;

    @Container
    private final DockerComposeContainer dockerCompose = new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
            .withExposedService("redis_1", REDIS_PORT);


    @BeforeEach
    public void setUp() {
        String address = dockerCompose.getServiceHost("redis_1", REDIS_PORT);
        Integer port = dockerCompose.getServicePort("redis_1", REDIS_PORT);

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
        dockerCompose.close();
    }

}