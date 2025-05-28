package ua.se.sample.easynotes.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * базовый маппинг сущности и простой тестовый пример с использованием этой сущности.
 */
@Entity
public class DateTimeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate localDate;

    private LocalDateTime localDateTime;

    private OffsetTime offsetTime;

    //?
    private OffsetDateTime offsetDateTime;

    // Hibernate-specific - not supported by JPA

    private Duration duration;

    private Instant instant;

    private ZonedDateTime zonedDateTime;

    //  обрабатывать информацию о часовом поясе,
    private TimeZoneStorageType timeZoneStorageType;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime zonedDateTimeNative;

    @Temporal(TemporalType.TIMESTAMP)
    private Date utilDate;

    @Temporal(TemporalType.DATE)
    private Calendar utilCalendar;


    public DateTimeItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public OffsetTime getOffsetTime() {
        return offsetTime;
    }

    public void setOffsetTime(OffsetTime offsetTime) {
        this.offsetTime = offsetTime;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public TimeZoneStorageType getTimeZoneStorageType() {
        return timeZoneStorageType;
    }

    public void setTimeZoneStorageType(TimeZoneStorageType timeZoneStorageType) {
        this.timeZoneStorageType = timeZoneStorageType;
    }

    public ZonedDateTime getZonedDateTimeNative() {
        return zonedDateTimeNative;
    }

    public void setZonedDateTimeNative(ZonedDateTime zonedDateTimeNative) {
        this.zonedDateTimeNative = zonedDateTimeNative;
    }

    public Date getUtilDate() {
        return utilDate;
    }

    public void setUtilDate(Date utilDate) {
        this.utilDate = utilDate;
    }

    public Calendar getUtilCalendar() {
        return utilCalendar;
    }

    public void setUtilCalendar(Calendar utilCalendar) {
        this.utilCalendar = utilCalendar;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DateTimeItem{");
        sb.append("id=").append(id);
        sb.append("\n");
        sb.append(", localDate=").append(localDate);
        sb.append("\n");
        sb.append(", localDateTime=").append(localDateTime);
        sb.append("\n");
        sb.append(", offsetTime=").append(offsetTime);
        sb.append("\n");
        sb.append(", offsetDateTime=").append(offsetDateTime);
        sb.append("\n");
        sb.append(", duration=").append(duration);
        sb.append("\n");
        sb.append(", instant=").append(instant);
        sb.append("\n");
        sb.append(", zonedDateTime=").append(zonedDateTime);
        sb.append("\n");
        sb.append(", timeZoneStorageType=").append(timeZoneStorageType);
        sb.append("\n");
        sb.append(", zonedDateTimeNative=").append(zonedDateTimeNative);
        sb.append("\n");
        sb.append(", utilDate=").append(utilDate);
        sb.append("\n");
        sb.append(", utilCalendar=").append(utilCalendar);
        sb.append('}');
        return sb.toString();
    }
}
