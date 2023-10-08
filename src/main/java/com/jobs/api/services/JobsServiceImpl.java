package com.jobs.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobs.api.clients.RecruitmentAPIClient;
import com.jobs.api.exceptions.JobDoesNotExistException;
import com.jobs.api.models.Job;

@Service
public class JobsServiceImpl implements JobsService {
    @Autowired
    private RecruitmentAPIClient recruitmentAPIClient;

    @Override
    public List<Job> getJobs() {
        return recruitmentAPIClient.getJobs();
    }

    @Override
    public Job getJobDetail(String jobId) {
        var job = recruitmentAPIClient.getJobDetail(jobId);

        if (job.getId() == null) {
            throw new JobDoesNotExistException();
        }

        return job;
    }
}
