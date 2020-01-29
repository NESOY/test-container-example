package com.nesoy.testcontainer.step0;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nesoy.testcontainer.RedisBackedCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("This test class is deliberately invalid, as it relies on a non-existent local Redis")
public class RedisBackedCacheIntTestStep0 {
    private RedisBackedCache underTest;

    @BeforeEach
    public void setUp() {
        // Assume that we have Redis running locally?
        underTest = new RedisBackedCache("localhost", 6379);
    }

    @Test
    public void testSimplePutAndGet() {
        underTest.put("test", "example");

        String retrieved = underTest.get("test");
        assertEquals("example", retrieved);
    }
}
