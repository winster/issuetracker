package com.example.issuetracker.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@GenericGenerator(
        name = "TEAM_MEMBER_ID_SEQUENCE_GENERATOR",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TEAM_MEMBER_ID_SEQUENCE")
        }
)

public class TeamMember {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_MEMBER_ID_SEQUENCE_GENERATOR")
  @Column(unique = true, nullable = false)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String name;
}
