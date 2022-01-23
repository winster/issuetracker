package com.example.issuetracker.controller;

import com.example.issuetracker.dto.StoryDto;
import com.example.issuetracker.service.StoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class StoryController {

  private final StoryService storyService;

  @PostMapping("/story")
  public ResponseEntity<StoryDto> createStory(@RequestBody StoryDto storyDto) {
    StoryDto storyDtoCreated = storyService.createStory(storyDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(storyDtoCreated);
  }

  @PutMapping("/story")
  public ResponseEntity<StoryDto> updateStory(@RequestBody StoryDto storyDto) {
    StoryDto storyDtoUpdated = storyService.updateStory(storyDto);
    return ResponseEntity.status(HttpStatus.OK).body(storyDtoUpdated);
  }

  @DeleteMapping("/story/{issueId}")
  public ResponseEntity<Void> deleteStory(@PathVariable final Integer issueId) {
    storyService.deleteStory(issueId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/story")
  public ResponseEntity<List<StoryDto>> getStoryList() {
    List<StoryDto> storyDtoList = storyService.getStoryList();
    return ResponseEntity.ok().body(storyDtoList);
  }

  @GetMapping("/story/{issueId}")
  public ResponseEntity<StoryDto> getStory(@PathVariable final Integer issueId) {
    StoryDto storyDto = storyService.getStory(issueId);
    return ResponseEntity.ok().body(storyDto);
  }

}
