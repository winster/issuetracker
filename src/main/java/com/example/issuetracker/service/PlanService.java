package com.example.issuetracker.service;

import com.example.issuetracker.common.StoryStatus;
import com.example.issuetracker.config.AppConfigProperties;
import com.example.issuetracker.dto.MemberCapacityDto;
import com.example.issuetracker.dto.PlanDto;
import com.example.issuetracker.dto.StoryDto;
import com.example.issuetracker.entity.TeamMember;
import com.example.issuetracker.repository.TeamMemberRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@Slf4j
public class PlanService {

  private final StoryService storyService;
  private final TeamMemberRepository teamMemberRepository;
  private final AppConfigProperties appConfigProperties;

  /**
   * if estimate is > 10, the same team member will continue the story in coming weeks
   *
   * @return planDto
   */
  public PlanDto createPlan() {
    //Fetch all stories that are estimated
    List<StoryDto> storyList = storyService.findAllByStatus(StoryStatus.ESTIMATED);
    //Fetch all team members in the order of member Id
    List<TeamMember> teamMemberList = teamMemberRepository.findAllByOrderByIdAsc();
    log.info("storyList {}", storyList);
    log.info("teamMemberList {}", teamMemberList);
    //Create a 2-D array of height same as the number of team members and width as the number of weeks(configurable)
    MemberCapacityDto[][] capacity = new MemberCapacityDto[teamMemberList.size()][appConfigProperties.getWeeksCount()];
    //Initialize the array with default capacity(configurable)
    for (int i = 0; i < teamMemberList.size(); i++) {
      for (int j = 0; j < appConfigProperties.getWeeksCount(); j++) {
        capacity[i][j] = new MemberCapacityDto(i + 1, j + 1, appConfigProperties.getDefaultCapacity(), null);
      }
    }
    log.info("available capacity");
    printCapacity(capacity);
    List<StoryDto> unAssignedStoryList = storyList.stream()
            .filter(story -> story.getAssignedTo() == null)
            .collect(Collectors.toList());
    log.info("unAssignedStoryList {}", unAssignedStoryList);
    //iterate through each unassigned story
    for (StoryDto story : unAssignedStoryList) {
      //iterate through each week
      for (int weekId = 0; weekId < appConfigProperties.getWeeksCount(); weekId++) {
        List<MemberCapacityDto> memberCapacityList = new ArrayList<>();
        //find all members who has capacity available for the given week
        for (int i = 0; i < teamMemberList.size(); i++) {
          if (capacity[i][weekId].getRemainingCapacity() > 0) {
            memberCapacityList.add(capacity[i][weekId]);
          }
        }
        if (memberCapacityList.size() > 0) {
          //sort members based on the remaining capacity in the descending order
          memberCapacityList.sort((m1, m2) -> m2.getRemainingCapacity() - m1.getRemainingCapacity());
          //assign the story to the first member as found above
          int memberIdx = memberCapacityList.get(0).getMemberId() - 1;
          //distribute the story over multiple weeks if applicable
          distributeStoryOverWeeks(capacity, story, memberIdx, weekId);
          //assignStoryToTeamMember(story, memberIdx + 1); Not sure about this requirement. We can discuss this
          break;
        }
      }
    }
    log.info("after allocation");
    printCapacity(capacity);
    PlanDto planDto = preparePlan(capacity);
    log.info("prepared Plan {}", planDto);
    return planDto;
  }

  /**
   * If the story estimate is more than the default capacity of a team member for a week, distribute it across weeks
   */
  private void distributeStoryOverWeeks(MemberCapacityDto[][] capacity, StoryDto story, int memberIdx, int weekIndex) {
    int estimate = story.getEstimate();
    while (estimate > 0) {
      int remainingCapacity = capacity[memberIdx][weekIndex].getRemainingCapacity();
      if (estimate < remainingCapacity) {
        capacity[memberIdx][weekIndex].setRemainingCapacity(remainingCapacity - estimate);
        estimate = -1;
      } else {
        capacity[memberIdx][weekIndex].setRemainingCapacity(0);
        estimate = estimate - remainingCapacity;
      }
      List<StoryDto> storyList = capacity[memberIdx][weekIndex].getStoryList();
      if (storyList == null) {
        storyList = new ArrayList<>();
      }
      storyList.add(story);
      capacity[memberIdx][weekIndex].setStoryList(storyList);
      weekIndex++;
    }
  }

  /**
   * Create a plan from 2D array
   *
   * @param capacity 2D array
   * @return planDto
   */
  private PlanDto preparePlan(MemberCapacityDto[][] capacity) {
    PlanDto planDto = new PlanDto();
    planDto.setPlan(new ArrayList<>());
    for (MemberCapacityDto[] memberCapacityDtos : capacity) {
      for (int j = 0; j < appConfigProperties.getWeeksCount(); j++) {
        if (memberCapacityDtos[j].getStoryList() != null) {
          planDto.getPlan().add(memberCapacityDtos[j]);
        }
      }
    }
    return planDto;
  }

  /**
   * convenient method to print the 2D array
   *
   * @param capacity 2D array
   */
  private void printCapacity(MemberCapacityDto[][] capacity) {
    for (int i = 0; i < capacity.length; i++) {
      for (int j = 0; j < appConfigProperties.getWeeksCount(); j++) {
        log.info("capacity[i][j] {}{} {}", i, j, capacity[i][j]);
      }
    }
  }
}
