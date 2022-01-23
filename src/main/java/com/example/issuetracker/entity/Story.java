package com.example.issuetracker.entity;

import com.example.issuetracker.common.StoryStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@Entity
@ToString(callSuper = true)
public class Story extends Issue {
  private StoryStatus status;
  private Integer estimate;
}
