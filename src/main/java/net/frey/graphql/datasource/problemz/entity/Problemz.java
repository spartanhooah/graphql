package net.frey.graphql.datasource.problemz.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@Data
@Table(name = "problemz")
public class Problemz {
    @Id
    private UUID id;

    @CreatedDate
    private LocalDateTime creationTimestamp;

    private String title;
    private String content;
    private String tags;

    @OneToMany(mappedBy = "problemz")
    private List<Solutionz> solutions;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Userz createdBy;
}
