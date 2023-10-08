package com.jobs.api.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jobs.api.exceptions.InternalServerErrorException;
import com.jobs.api.models.Job;

import reactor.core.publisher.Mono;

@Service
public class RecruitmentAPIClient {
    @Autowired
    @Qualifier("recruitment_api_url")
    private String recruitmentAPIUrl;

    private final WebClient webClient = WebClient.create();

    public Job getJobDetail(String jobId) {
        var job = webClient.get()
                .uri(recruitmentAPIUrl + "/positions/{jobId}", jobId)
                .retrieve()
                .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, resp -> {
                    return Mono.error(InternalServerErrorException::new);
                })
                .bodyToMono(Job.class)
                .block();

        return job;

    }
}
