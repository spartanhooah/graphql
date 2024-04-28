package net.frey.graphql.datasource.problemz.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@Data
@Table(name = "solutionz")
public class Solutionz {
    @Id
    private UUID id;

    @CreatedDate
    private LocalDateTime creationTimestamp;

    private String content;
    private String type;
    private int thumbsUps;
    private int thumbsDowns;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Userz createdBy;

    @ManyToOne
    @JoinColumn(name = "problemz_id", nullable = false)
    private Problemz problemz;
}
