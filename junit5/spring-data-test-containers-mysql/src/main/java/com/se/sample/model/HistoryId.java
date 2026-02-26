package com.se.sample.model;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryId implements Serializable {
    private static final long serialVersionUID = 3616390202928641363L;

    @Column(name = "document_id",nullable = false)
    private String documentId;

    @Column( name = "user_email", nullable = false)
    private String userEmail;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
