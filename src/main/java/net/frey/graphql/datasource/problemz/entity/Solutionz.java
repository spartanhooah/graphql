package net.frey.graphql.datasource.problemz.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "solutionz")
public class Solutionz {
    @Id
    private UUID id;

    @CreationTimestamp
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
    @ToString.Exclude
    private Problemz problemz;
}
