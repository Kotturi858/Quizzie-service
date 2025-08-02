package com.lot.quiz.app.quizzie.configurations;

import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@Slf4j
@Order(2) // Ensure this configuration is applied after any other security configurations
public class SecurityConfig implements ApplicationListener<ContextRefreshedEvent>{


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        FilterChainProxy filterChainProxy = event.getApplicationContext().getBean(FilterChainProxy.class);

        log.info("🔒 Listing Spring Security Filters:");

        List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
        for (int i = 0; i < filterChains.size(); i++) {
            SecurityFilterChain chain = filterChains.get(i);
            log.info("🔗 Filter Chain [{}]", i);

            List<Filter> filters = chain.getFilters();
            for (Filter filter : filters) {
                log.info("   ➤ {}", filter.getClass().getName());
            }
        }
    }
}



