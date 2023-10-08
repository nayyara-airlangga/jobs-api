package com.jobs.api.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.jobs.api.clients.RecruitmentAPIClient;
import com.jobs.api.exceptions.InternalServerErrorException;
import com.jobs.api.exceptions.JobDoesNotExistException;
import com.jobs.api.models.Job;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobsServiceImpl implements JobsService {
    @Autowired
    private RecruitmentAPIClient recruitmentAPIClient;

    @Override
    public List<Job> getJobs() {
        return recruitmentAPIClient.getJobs();
    }

    @Override
    public Resource getJobsCSV() {
        var jobs = this.getJobs();

        var jobFields = Arrays.asList(Job.class.getDeclaredFields()).stream().map(field -> field.getName())
                .collect(Collectors.toList()).toArray(String[]::new);

        ByteArrayInputStream csvStream;
        try (
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(
                        new PrintWriter(outputStream),
                        CSVFormat.DEFAULT.builder().setHeader(jobFields).build());) {
            for (Job job : jobs) {
                csvPrinter.printRecord(
                        job.getId(),
                        job.getType(),
                        job.getUrl(),
                        job.getCreatedAt(),
                        job.getCompany(),
                        job.getCompanyUrl(),
                        job.getLocation(),
                        job.getTitle(),
                        job.getDescription(),
                        job.getCompanyLogo(),
                        job.getHowToApply());
            }

            csvPrinter.flush();

            csvStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException();
        }

        return new InputStreamResource(csvStream);
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
