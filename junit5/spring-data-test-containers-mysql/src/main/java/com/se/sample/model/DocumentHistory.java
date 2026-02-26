package com.se.sample.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity

@Table(name = "history", indexes = {
        @Index(name = "history_id_index",
                columnList = "document_id, user_email", unique = true)
})
@IdClass(HistoryId.class)
public class DocumentHistory implements Serializable {
    @Id
    @Column(name = "document_id",nullable = false)
    private String documentId;

    @Id
    @Column( name = "user_email", nullable = false)
    private String userEmail;


    @Column(name = "publisher")
    public String publisher;
    @Column(name = "status")
    public String status;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "document_type")
    private String documentType;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "document_date")
    private Date documentDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "document_view_date")
    private Date documentViewDate;
    @Column(name = "status_color")
    private String statusColor;
}