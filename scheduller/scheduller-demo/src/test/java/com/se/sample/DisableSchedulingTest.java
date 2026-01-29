package com.se.sample;

import com.se.sample.scheduled.ScheduledTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

// Spring Boot test to verify the disabling of scheduled tasks.
@SpringBootTest
@ContextConfiguration(classes = DisableSchedulingTest.TestConfig.class) // Use test-specific config
public class DisableSchedulingTest {

    @MockBean
    private ScheduledTask scheduledTask; // Mock the scheduled task bean

    // Configuration class for testing to disable scheduling.
    @Configuration
    static class TestConfig {

        // Override TaskScheduler with a no-op scheduler to prevent tasks from running
        // если не переопределить то будет использоваться com.se.sample.scheduled.ScheduledTask
        @Bean
        public ConcurrentTaskScheduler taskScheduler() {
            return new ConcurrentTaskScheduler(); // No-op task scheduler
        }
    }

    @Test
    public void testWithoutScheduledTasks() {
        // Test logic here
        // Verifying that the scheduled task is never called during the test
        verify(scheduledTask,  never()).runTask();
    }
}
