package com.jobs.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${recruitment.api.url}")
    private String recruitmentAPIUrl;

    @Bean(name = { "recruitment_api_url" })
    public String getRecruitmentAPIUrl() {
        return recruitmentAPIUrl;
    }
}
