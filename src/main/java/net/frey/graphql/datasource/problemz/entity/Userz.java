package net.frey.graphql.datasource.problemz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "userz")
public class Userz {
    @Id
    private UUID id;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    private String username;
    private String email;
    private String hashedPassword;
    private URL avatar;
    private String displayName;
    private boolean active;
    private String userRole;
}
