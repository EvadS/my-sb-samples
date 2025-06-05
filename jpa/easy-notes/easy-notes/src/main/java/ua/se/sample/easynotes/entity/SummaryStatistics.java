package ua.lz.ep.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ai-statistics")
@Builder(toBuilder = true)
public class SummaryStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime startRequest;

    @Column(nullable = false, updatable = false)
    private LocalDateTime endRequest;

    private Boolean success;
    private Boolean failure;

    private String message;
}
