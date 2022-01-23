package com.example.issuetracker.service;

import com.example.issuetracker.common.BugPriority;
import com.example.issuetracker.common.BugStatus;
import com.example.issuetracker.dto.BugDto;
import com.example.issuetracker.entity.Bug;
import com.example.issuetracker.repository.BugRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Slf4j
public class BugService {

  private final BugRepository bugRepository;

  public BugDto createBug(BugDto bugDto) {
    Bug bug = generateBug(bugDto);
    log.info("{}", bugDto);
    bug = bugRepository.save(bug);
    return generateBugDto(bug);
  }

  public BugDto updateBug(BugDto bugDto) {
    Bug bug = generateBug(bugDto);
    bug.setIssueId(bugDto.getIssueId());
    log.info("{}", bugDto);
    bug = bugRepository.save(bug);
    return generateBugDto(bug);
  }

  private Bug generateBug(BugDto bugDto) {
    Bug bug = new Bug();
    bug.setTitle(bugDto.getTitle());
    bug.setDescription(bugDto.getDescription());
    bug.setCreatedDate(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
    bug.setStatus(BugStatus.valueOf(bugDto.getStatus().toUpperCase()));
    bug.setPriority(BugPriority.valueOf(bugDto.getPriority().toUpperCase()));
    return bug;
  }

  public BugDto getBug(Integer issueId) {
    Bug bug = bugRepository.getById(issueId);
    BugDto bugDto = generateBugDto(bug);
    return bugDto;
  }

  public List<BugDto> getBugList() {
    List<Bug> bugList = bugRepository.findAll();
    List<BugDto> bugDtoList = generateBugDtoList(bugList);
    return bugDtoList;
  }

  private List<BugDto> generateBugDtoList(List<Bug> bugList) {
    List<BugDto> bugDtoList = new ArrayList<>();
    for (Bug bug : bugList) {
      bugDtoList.add(generateBugDto(bug));
    }
    return bugDtoList;
  }

  private BugDto generateBugDto(Bug bug) {
    BugDto bugDto = new BugDto();
    bugDto.setIssueId(bug.getIssueId());
    bugDto.setStatus(bug.getStatus().toString());
    bugDto.setDescription(bug.getDescription());
    bugDto.setPriority(bug.getPriority().toString());
    bugDto.setTitle(bug.getTitle());
    bugDto.setCreatedDate(bug.getCreatedDate());
    bugDto.setAssignedTo(bug.getAssignedTo());
    return bugDto;
  }

  public void deleteBug(Integer issueId) {
    bugRepository.deleteById(issueId);
  }
}
