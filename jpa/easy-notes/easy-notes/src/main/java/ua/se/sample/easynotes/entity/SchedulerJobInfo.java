package ua.lz.ep.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * позволяет нам иметь более детальный контроль над тем, как задания сохраняются в базе данных.
  */
@ToString
@Getter
@Setter
@Entity
@Table(name = "scheduler_job_info")
public class SchedulerJobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "JOB_NAME")
    private String jobName;

    @Column(name = "JOB_GROUP")
    private String jobGroup;

    @Column(name = "JOB_STATUS")
    private String jobStatus;

    @Column(name = "JOB_CLASS")
    private String jobClass;

    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;

    private String desc;

    @Column(name = "INTERFACE_NAME")
    private String interfaceName;

    @Column(name = "REPEAT_TIME")
    private Long repeatTime;

    @Column(name = "CRON_JOB")
    private Boolean cronJob;
}
