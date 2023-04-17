package com.claro.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletContext;
import java.util.Arrays;

@Configuration
public class WebConfiguration implements ServletContextInitializer {

    private static final long COOKIE_MAX_AGE = 1800L;

    private final Logger log = LoggerFactory.getLogger(WebConfiguration.class);

    /**
     *
     */
    @Override
    public void onStartup(final ServletContext servletContext) {
        log.info("Web application fully configured");
    }

    /**
     *
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.setAllowedMethods(Arrays.asList("POST", "GET", "OPTIONS", "DELETE", "PATCH"));
        config.setMaxAge(COOKIE_MAX_AGE);

        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            log.debug("Registering CORS filter");
            source.registerCorsConfiguration("/**", config);
            source.registerCorsConfiguration("/management/**", config);
            source.registerCorsConfiguration("/v3/api-docs", config);
        }
        return new CorsFilter(source);
    }
}
