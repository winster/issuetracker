package com.example.issuetracker.entity;

import com.example.issuetracker.common.BugPriority;
import com.example.issuetracker.common.BugStatus;
import com.example.issuetracker.common.StoryStatus;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Bug extends Issue {
  private BugStatus status;
  private BugPriority priority;
}
