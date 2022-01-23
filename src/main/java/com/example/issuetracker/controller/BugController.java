package com.example.issuetracker.controller;

import com.example.issuetracker.dto.BugDto;
import com.example.issuetracker.service.BugService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class BugController {

  private final BugService bugService;

  @PostMapping("/bug")
  public ResponseEntity<BugDto> createBug(@RequestBody BugDto bugDto) {
    BugDto bugDtoCreated = bugService.createBug(bugDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(bugDtoCreated);
  }

  @PutMapping("/bug")
  public ResponseEntity<BugDto> updateBug(@RequestBody BugDto bugDto) {
    BugDto bugDtoUpdated = bugService.updateBug(bugDto);
    return ResponseEntity.status(HttpStatus.OK).body(bugDtoUpdated);
  }

  @DeleteMapping("/bug/{issueId}")
  public ResponseEntity<Void> deleteBug(@PathVariable final Integer issueId) {
    bugService.deleteBug(issueId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/bug")
  public ResponseEntity<List<BugDto>> getBugList() {
    List<BugDto> bugDtoList = bugService.getBugList();
    return ResponseEntity.ok().body(bugDtoList);
  }

  @GetMapping("/bug/{issueId}")
  public ResponseEntity<BugDto> getBug(@PathVariable final Integer issueId) {
    BugDto bugDto = bugService.getBug(issueId);
    return ResponseEntity.ok().body(bugDto);
  }

}
