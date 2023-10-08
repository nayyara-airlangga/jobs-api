package com.jobs.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jobs.api.models.Job;
import com.jobs.api.services.JobsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/jobs")
@Slf4j
public class JobsController {
    @Autowired
    private JobsService jobsService;

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobDetail(@PathVariable String jobId) {
        try {
            return ResponseEntity.ok(jobsService.getJobDetail(jobId));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        }
    }
}
