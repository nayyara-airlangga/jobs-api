package com.jobs.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.api.exceptions.ExceptionWrapper;
import com.jobs.api.exceptions.JobDoesNotExistException;
import com.jobs.api.models.Job;
import com.jobs.api.services.JobsService;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {
    @Autowired
    private JobsService jobsService;

    @GetMapping()
    public ResponseEntity<List<Job>> getJobs() {
        return ResponseEntity.ok(jobsService.getJobs());
    }

    @GetMapping(value = "/download", produces = "text/csv")
    public ResponseEntity<Resource> getJobsCSV() {
        var csvFile = jobsService.getJobsCSV();

        var headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename("jobs.csv").build());
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<Resource>(csvFile, headers, HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobDetail(@PathVariable String jobId) {
        return ResponseEntity.ok(jobsService.getJobDetail(jobId));
    }

    @ExceptionHandler(value = { JobDoesNotExistException.class })
    public ResponseEntity<Object> handleJobDoesNotExist(JobDoesNotExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionWrapper(e.getMessage()));
    }
}
