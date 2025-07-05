package com.lot.quiz.app.quizzie.configurations;
//package your.package.name;

import jakarta.servlet.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig implements ApplicationListener<ContextRefreshedEvent>{

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        FilterChainProxy filterChainProxy = event.getApplicationContext().getBean(FilterChainProxy.class);

        logger.info("🔒 Listing Spring Security Filters:");

        List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
        for (int i = 0; i < filterChains.size(); i++) {
            SecurityFilterChain chain = filterChains.get(i);
            logger.info("🔗 Filter Chain [{}]", i);

            List<Filter> filters = chain.getFilters();
            for (Filter filter : filters) {
                logger.info("   ➤ {}", filter.getClass().getName());
            }
        }
    }
}



