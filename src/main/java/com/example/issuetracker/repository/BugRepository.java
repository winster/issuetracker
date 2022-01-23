package com.example.issuetracker.repository;

import com.example.issuetracker.entity.Bug;
import com.example.issuetracker.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, Integer> {

}
