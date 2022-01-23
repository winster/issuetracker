package com.example.issuetracker.repository;

import com.example.issuetracker.common.StoryStatus;
import com.example.issuetracker.entity.Story;
import com.example.issuetracker.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
  List<Story> findAllByStatus(StoryStatus storyStatus);
}
