package net.frey.graphql.datasource.problemz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "userz_token")
public class UserzToken {
    @Id
    private UUID userId;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    private String authToken;
    private LocalDateTime expiryTimestamp;
}
