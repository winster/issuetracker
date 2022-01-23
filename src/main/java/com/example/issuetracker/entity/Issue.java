package com.example.issuetracker.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@Table
@EntityListeners(AuditingEntityListener.class)
@GenericGenerator(
        name = "ISSUE_ID_SEQUENCE_GENERATOR",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ISSUE_ID_SEQUENCE")
        }
)
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ISSUE_ID_SEQUENCE_GENERATOR")
  @Column(unique = true, nullable = false)
  private Integer issueId;

  @Column
  private String title;

  private String description;

  private Integer assignedTo;

  @CreatedDate
  private LocalDateTime createdDate;

}
