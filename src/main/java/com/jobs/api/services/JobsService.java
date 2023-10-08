package com.jobs.api.services;

import java.util.List;

import org.springframework.core.io.Resource;

import com.jobs.api.models.Job;

public interface JobsService {
    List<Job> getJobs();

    Resource getJobsCSV();

    Job getJobDetail(String jobId);
}
