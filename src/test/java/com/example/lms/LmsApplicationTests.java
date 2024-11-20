package com.example.lms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.lms.config.TestSecurityConfig;

@SpringBootTest
@Import(TestSecurityConfig.class)  // Import test-specific config
class LmsApplicationTests {

    @Test
    void contextLoads() {
    }
}
