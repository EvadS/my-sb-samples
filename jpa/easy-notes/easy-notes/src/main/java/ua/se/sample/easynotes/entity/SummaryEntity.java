package ua.lz.ep.domain;

import lombok.Data;
import ua.lz.ep.models.enums.IpsRole;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "summary")
public class SummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(nullable = false, name = "name")
    private String text;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    @Column(nullable = false, updatable = false)
    private IpsRole ipsRole;
}