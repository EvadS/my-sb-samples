package com.se.sample.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.se.sample.dto.ServerResponse;
import com.se.sample.job.CronJob;
import com.se.sample.job.SimpleJob;
import com.se.sample.service.JobService;
import com.se.sample.util.ServerResponseCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/scheduler")
@Tag(name = " Management", description = "APIs for managing")
public class JobController {

    @Autowired
    @Lazy
    JobService jobService;


    ///  2025/0710 10:00
    //  0 0/1 * ? * * *
    @GetMapping("/schedule")
    public ServerResponse schedule(@RequestParam("jobName") String jobName,
                                   //Mon Jun 23 14:10:00 EEST 2025
                                   @RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime,
                                   @RequestParam("cronExpression") String cronExpression){
        System.out.println("JobController.schedule()");

        //Job Name is mandatory
        if(jobName == null || jobName.trim().equals("")){
            return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false);
        }

        //Check if job Name is unique;
        if(!jobService.isJobWithNamePresent(jobName)){

            if(cronExpression == null || cronExpression.trim().equals("")){
                //Single Trigger
                boolean status = jobService.scheduleOneTimeJob(jobName, SimpleJob.class, jobScheduleTime);
                if(status){
                    return getServerResponse(ServerResponseCode.SUCCESS, jobService.getAllJobs());
                }else{
                    return getServerResponse(ServerResponseCode.ERROR, false);
                }

            }else{
                //Cron Trigger // если задано крон условия
                boolean status = jobService.scheduleCronJob(jobName, CronJob.class, jobScheduleTime, cronExpression);
                if(status){
                    return getServerResponse(ServerResponseCode.SUCCESS, jobService.getAllJobs());
                }else{
                    return getServerResponse(ServerResponseCode.ERROR, false);
                }
            }
        }else{
            return getServerResponse(ServerResponseCode.JOB_WITH_SAME_NAME_EXIST, false);
        }
    }

    @GetMapping("/unschedule")
    public void unschedule(@RequestParam("jobName") String jobName) {
        System.out.println("JobController.unschedule()");
        jobService.unScheduleJob(jobName);
    }

    @GetMapping("delete")
    public ServerResponse delete(@RequestParam("jobName") String jobName) {
        System.out.println("JobController.delete()");

        if(jobService.isJobWithNamePresent(jobName)){
            boolean isJobRunning = jobService.isJobRunning(jobName);

            if(!isJobRunning){
                boolean status = jobService.deleteJob(jobName);
                if(status){
                    return getServerResponse(ServerResponseCode.SUCCESS, true);
                }else{
                    return getServerResponse(ServerResponseCode.ERROR, false);
                }
            }else{
                return getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
            }
        }else{
            //Job doesn't exist
            return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
        }
    }

    @GetMapping("/pause")
    public ServerResponse pause(@RequestParam("jobName") String jobName) {
        System.out.println("JobController.pause()");

        if(jobService.isJobWithNamePresent(jobName)){

            boolean isJobRunning = jobService.isJobRunning(jobName);

            if(!isJobRunning){
                boolean status = jobService.pauseJob(jobName);
                if(status){
                    return getServerResponse(ServerResponseCode.SUCCESS, true);
                }else{
                    return getServerResponse(ServerResponseCode.ERROR, false);
                }
            }else{
                return getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
            }

        }else{
            //Job doesn't exist
            return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
        }
    }

    @GetMapping("/resume")
    public ServerResponse resume(@RequestParam("jobName") String jobName) {
        System.out.println("JobController.resume()");

        if(jobService.isJobWithNamePresent(jobName)){
            String jobState = jobService.getJobState(jobName);

            if(jobState.equals("PAUSED")){
                System.out.println("Job current state is PAUSED, Resuming job...");
                boolean status = jobService.resumeJob(jobName);

                if(status){
                    return getServerResponse(ServerResponseCode.SUCCESS, true);
                }else{
                    return getServerResponse(ServerResponseCode.ERROR, false);
                }
            }else{
                return getServerResponse(ServerResponseCode.JOB_NOT_IN_PAUSED_STATE, false);
            }

        }else{
            //Job doesn't exist
            return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
        }
    }

    @PutMapping("/update")
    public ServerResponse updateJob(@RequestParam("jobName") String jobName,
                                    @RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime,
                                    @RequestParam("cronExpression") String cronExpression){
        System.out.println("JobController.updateJob()");

        //Job Name is mandatory
        if(jobName == null || jobName.trim().equals("")){
            return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false);
        }

        //Edit Job
        if(jobService.isJobWithNamePresent(jobName)){

            if(cronExpression == null || cronExpression.trim().equals("")){
                //Single Trigger
                boolean status = jobService.updateOneTimeJob(jobName, jobScheduleTime);
                if(status){
                    return getServerResponse(ServerResponseCode.SUCCESS, jobService.getAllJobs());
                }else{
                    return getServerResponse(ServerResponseCode.ERROR, false);
                }

            }else{
                //Cron Trigger
                boolean status = jobService.updateCronJob(jobName, jobScheduleTime, cronExpression);
                if(status){
                    return getServerResponse(ServerResponseCode.SUCCESS, jobService.getAllJobs());
                }else{
                    return getServerResponse(ServerResponseCode.ERROR, false);
                }
            }


        }else{
            return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
        }
    }

    @GetMapping("/jobs")
    public ServerResponse getAllJobs(){
        System.out.println("JobController.getAllJobs()");

        List<Map<String, Object>> list = jobService.getAllJobs();
        return getServerResponse(ServerResponseCode.SUCCESS, list);
    }

    @RequestMapping("/checkJobName")
    public ServerResponse checkJobName(@RequestParam("jobName") String jobName){
        System.out.println("JobController.checkJobName()");

        //Job Name is mandatory
        if(jobName == null || jobName.trim().equals("")){
            return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false);
        }

        boolean status = jobService.isJobWithNamePresent(jobName);
        return getServerResponse(ServerResponseCode.SUCCESS, status);
    }

    @GetMapping("/isJobRunning")
    public ServerResponse isJobRunning(@RequestParam("jobName") String jobName) {
        System.out.println("JobController.isJobRunning()");

        boolean status = jobService.isJobRunning(jobName);
        return getServerResponse(ServerResponseCode.SUCCESS, status);
    }

    @GetMapping("/jobState")
    public ServerResponse getJobState(@RequestParam("jobName") String jobName) {
        System.out.println("JobController.getJobState()");

        String jobState = jobService.getJobState(jobName);
        return getServerResponse(ServerResponseCode.SUCCESS, jobState);
    }

    @GetMapping("/stop")
    public ServerResponse stopJob(@RequestParam("jobName") String jobName) {
        System.out.println("JobController.stopJob()");

        if(jobService.isJobWithNamePresent(jobName)){

            if(jobService.isJobRunning(jobName)){
                boolean status = jobService.stopJob(jobName);
                if(status){
                    return getServerResponse(ServerResponseCode.SUCCESS, true);
                }else{
                    //Server error
                    return getServerResponse(ServerResponseCode.ERROR, false);
                }

            }else{
                //Job not in running state
                return getServerResponse(ServerResponseCode.JOB_NOT_IN_RUNNING_STATE, false);
            }

        }else{
            //Job doesn't exist
            return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
        }
    }

    @GetMapping("/start")
    public ServerResponse startJobNow(@RequestParam("jobName") String jobName) {
        System.out.println("JobController.startJobNow()");

        if(jobService.isJobWithNamePresent(jobName)){

            if(!jobService.isJobRunning(jobName)){
                boolean status = jobService.startJobNow(jobName);

                if(status){
                    //Success
                    return getServerResponse(ServerResponseCode.SUCCESS, true);

                }else{
                    //Server error
                    return getServerResponse(ServerResponseCode.ERROR, false);
                }

            }else{
                //Job already running
                return getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
            }

        }else{
            //Job doesn't exist
            return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
        }
    }

    public ServerResponse getServerResponse(int responseCode, Object data){
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatusCode(responseCode);
        serverResponse.setData(data);
        return serverResponse;
    }
}
