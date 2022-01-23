package com.example.issuetracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BugDto {
  private Integer issueId;
  private String title;
  private String description;
  private Integer assignedTo;
  private String status;
  private String priority;
  private LocalDateTime createdDate;
}
