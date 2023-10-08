package com.jobs.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobs.api.clients.RecruitmentAPIClient;
import com.jobs.api.models.Job;

@Service
public class JobsServiceImpl implements JobsService {
    @Autowired
    private RecruitmentAPIClient recruitmentAPIClient;

    @Override
    public Job getJobDetail(String jobId) {
        return recruitmentAPIClient.getJobDetail(jobId);
    }
}
