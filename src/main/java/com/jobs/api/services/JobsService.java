package com.jobs.api.services;

import com.jobs.api.models.Job;

public interface JobsService {
    Job getJobDetail(String jobId);
}
