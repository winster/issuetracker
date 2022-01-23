package com.example.issuetracker.service;

import com.example.issuetracker.common.StoryStatus;
import com.example.issuetracker.dto.StoryDto;
import com.example.issuetracker.entity.Story;
import com.example.issuetracker.repository.StoryRepository;
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
public class StoryService {

  private final StoryRepository storyRepository;

  public StoryDto createStory(StoryDto storyDto) {
    Story story = generateStory(storyDto);
    log.info("{}", storyDto);
    story = storyRepository.save(story);
    return generateStoryDto(story);
  }

  public StoryDto updateStory(StoryDto storyDto) {
    Story story = generateStory(storyDto);
    story.setIssueId(storyDto.getIssueId());
    log.info("{}", storyDto);
    story = storyRepository.save(story);
    return generateStoryDto(story);
  }

  private Story generateStory(StoryDto storyDto) {
    Story story = new Story();
    story.setTitle(storyDto.getTitle());
    story.setDescription(storyDto.getDescription());
    story.setCreatedDate(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
    story.setStatus(StoryStatus.valueOf(storyDto.getStatus().toUpperCase()));
    story.setEstimate(storyDto.getEstimate());
    return story;
  }

  public StoryDto getStory(Integer issueId) {
    Story story = storyRepository.getById(issueId);
    StoryDto storyDto = generateStoryDto(story);
    return storyDto;
  }

  public List<StoryDto> getStoryList() {
    List<Story> storyList = storyRepository.findAll();
    List<StoryDto> storyDtoList = generateStoryDtoList(storyList);
    return storyDtoList;
  }

  private List<StoryDto> generateStoryDtoList(List<Story> storyList) {
    List<StoryDto> storyDtoList = new ArrayList<>();
    for (Story story : storyList) {
      storyDtoList.add(generateStoryDto(story));
    }
    return storyDtoList;
  }

  private StoryDto generateStoryDto(Story story) {
    StoryDto storyDto = new StoryDto();
    storyDto.setIssueId(story.getIssueId());
    storyDto.setStatus(story.getStatus().toString());
    storyDto.setDescription(story.getDescription());
    storyDto.setEstimate(story.getEstimate());
    storyDto.setTitle(story.getTitle());
    storyDto.setCreatedDate(story.getCreatedDate());
    storyDto.setAssignedTo(story.getAssignedTo());
    return storyDto;
  }

  public void deleteStory(Integer issueId) {
    storyRepository.deleteById(issueId);
  }

  public List<StoryDto> findAllByStatus(StoryStatus storyStatus) {
    List<Story> storyList = storyRepository.findAllByStatus(storyStatus);
    List<StoryDto> storyDtoList = generateStoryDtoList(storyList);
    return storyDtoList;
  }
}
