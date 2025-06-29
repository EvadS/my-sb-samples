package ua.se.sample.easynotes.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import ua.se.sample.easynotes.dto.enums.IpsRole;
import ua.se.sample.easynotes.entity.converter.IpsRoleConverter;

import java.io.Serializable;
import java.time.LocalDate;


public class SummaryId implements Serializable {
    private static final long serialVersionUID = 3616390202928641363L;

    @Column(name="creation_date", nullable = false)
//    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate creationDate;

    @Convert(converter = IpsRoleConverter.class)
    @Column(name="ips_role",nullable = false)
    private IpsRole ipsRole;

    public SummaryId() {
    }


    public SummaryId(LocalDate creationDateTime, IpsRole ipsRole) {
        this.creationDate = creationDateTime;
        this.ipsRole = ipsRole;
    }


    public LocalDate getCreationDateTime() {
        return creationDate;
    }

    public void setCreationDateTime(LocalDate creationDateTime) {
        this.creationDate = creationDateTime;
    }

    public IpsRole getIpsRole() {
        return ipsRole;
    }

    public void setIpsRole(IpsRole ipsRole) {
        this.ipsRole = ipsRole;
    }
}
