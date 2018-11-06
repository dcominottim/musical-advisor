package br.com.cominotti.musical_advisor.infra.config;

import br.com.cominotti.musical_advisor.app.Caches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class CachingConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CachingConfig.class);

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    @Scheduled(fixedRate = 10000)
    @CacheEvict(
            allEntries = true,
            value = {
                    Caches.OPEN_WEATHER_MAPS_CITIES_BY_GEO_COORDINATES,
                    Caches.OPEN_WEATHER_MAPS_CITIES_BY_NAME,
                    Caches.SPOTIFY_ACCESS_TOKENS,
                    Caches.SPOTIFY_RECOMMENDATIONS
            }
    )
    public void clearCaches() {
        LOGGER.info("Clearing caches...");
    }
}
