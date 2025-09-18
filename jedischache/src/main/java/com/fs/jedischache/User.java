package com.fs.jedischache;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 100)
    private String email;
    @Column(length = 30)
    private String name;
    @Column(name="created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name="updated_at")

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
