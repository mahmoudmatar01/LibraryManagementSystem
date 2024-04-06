package org.example.bookslibrary.cache;

import org.example.bookslibrary.logger.Logger;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
@Configuration
@EnableCaching
public class CachingConfig {

    // Take an instance from Logger class
    private final Logger logger =Logger.getInstance();

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        long expireAfterDuration = 1;
        String expireAfterTimeUnit = "HOURS";
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(expireAfterDuration, TimeUnit.valueOf(expireAfterTimeUnit)) // Cache entries expire after 1 hour
                .maximumSize(100));
        logger.logInfo(this.getClass(),"Cache is ready-to-use");
        return cacheManager;
    }

}