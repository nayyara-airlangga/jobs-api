package com.jobs.api.services;

import java.util.List;

import com.jobs.api.models.Job;

public interface JobsService {
    List<Job> getJobs();

    Job getJobDetail(String jobId);
}
