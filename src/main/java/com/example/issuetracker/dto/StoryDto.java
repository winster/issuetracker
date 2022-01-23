package com.example.issuetracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoryDto {
  private Integer issueId;
  private String title;
  private String description;
  private Integer assignedTo;
  private String status;
  private Integer estimate;
  private LocalDateTime createdDate;
}
