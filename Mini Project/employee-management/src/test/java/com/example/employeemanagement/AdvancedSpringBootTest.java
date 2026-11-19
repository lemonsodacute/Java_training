package com.example.employeemanagement;

import com.example.employeemanagement.config.CacheConfig;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.EmployeeReportService;
import com.example.employeemanagement.service.SystemHeartbeat;
import org.junit.jupiter.api.Test;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdvancedSpringBootTest {
    @Test
    void totalEmployeesUsesRepository() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        when(repository.count()).thenReturn(5L);
        EmployeeReportService report = new EmployeeReportService(repository);
        assertEquals(5L, report.totalEmployees());
        verify(repository).count();
    }

    @Test
    void totalIsCachedForOneMinute() throws Exception {
        assertNotNull(EmployeeReportService.class.getMethod("totalEmployees").getAnnotation(Cacheable.class));
        CaffeineCache cache = (CaffeineCache) new CacheConfig().cacheManager().getCache("employeeTotal");
        assertNotNull(cache);
        assertEquals(Duration.ofMinutes(1), cache.getNativeCache().policy()
                .expireAfterWrite().orElseThrow().getExpiresAfter());
    }

    @Test
    void heartbeatRunsEveryThirtySeconds() throws Exception {
        Scheduled schedule = SystemHeartbeat.class.getMethod("logHeartbeat").getAnnotation(Scheduled.class);
        assertNotNull(schedule);
        assertEquals(30000L, schedule.fixedRate());
        assertEquals(30000L, schedule.initialDelay());
    }
}
