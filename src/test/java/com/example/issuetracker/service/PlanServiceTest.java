package com.example.issuetracker.service;

import com.example.issuetracker.common.StoryStatus;
import com.example.issuetracker.config.AppConfigProperties;
import com.example.issuetracker.dto.MemberCapacityDto;
import com.example.issuetracker.dto.PlanDto;
import com.example.issuetracker.dto.StoryDto;
import com.example.issuetracker.entity.TeamMember;
import com.example.issuetracker.repository.TeamMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanServiceTest {

  @Mock
  StoryService storyService;
  @Mock
  TeamMemberRepository teamMemberRepository;
  @Mock
  AppConfigProperties appConfigProperties;
  @InjectMocks
  PlanService planService;

  @BeforeEach
  void setup(){
    when(appConfigProperties.getDefaultCapacity()).thenReturn(10);
    when(appConfigProperties.getWeeksCount()).thenReturn(3);
  }

  @Test
  void member_1_task_1() {
    List<StoryDto> storyList = new ArrayList<>();
    StoryDto story = new StoryDto();
    story.setIssueId(1);
    story.setTitle("title1");
    story.setDescription("desc1");
    story.setAssignedTo(null);
    story.setStatus(StoryStatus.ESTIMATED.toString());
    story.setEstimate(8);
    story.setCreatedDate(LocalDateTime.now());
    storyList.add(story);
    List<TeamMember> teamMemberList = new ArrayList<>();
    TeamMember teamMember = new TeamMember();
    teamMember.setId(1);
    teamMember.setName("A");
    teamMemberList.add(teamMember);
    when(storyService.findAllByStatus(StoryStatus.ESTIMATED)).thenReturn(storyList);
    when(teamMemberRepository.findAllByOrderByIdAsc()).thenReturn(teamMemberList);
    PlanDto planDto = planService.createPlan();
    assertNotNull(planDto);
    assertEquals(1, planDto.getPlan().size());
  }

  @Test
  void member_1_complex_task_1() {
    List<StoryDto> storyList = new ArrayList<>();
    StoryDto story = new StoryDto();
    story.setIssueId(1);
    story.setTitle("title1");
    story.setDescription("desc1");
    story.setAssignedTo(null);
    story.setStatus(StoryStatus.ESTIMATED.toString());
    story.setEstimate(13);
    story.setCreatedDate(LocalDateTime.now());
    storyList.add(story);
    List<TeamMember> teamMemberList = new ArrayList<>();
    TeamMember teamMember = new TeamMember();
    teamMember.setId(1);
    teamMember.setName("A");
    teamMemberList.add(teamMember);
    when(storyService.findAllByStatus(StoryStatus.ESTIMATED)).thenReturn(storyList);
    when(teamMemberRepository.findAllByOrderByIdAsc()).thenReturn(teamMemberList);
    PlanDto planDto = planService.createPlan();
    assertNotNull(planDto);
    assertEquals(2, planDto.getPlan().size());
  }

  @Test
  void member_2_complex_task_1() {
    List<StoryDto> storyList = new ArrayList<>();
    StoryDto story = new StoryDto();
    story.setIssueId(1);
    story.setTitle("title1");
    story.setDescription("desc1");
    story.setAssignedTo(null);
    story.setStatus(StoryStatus.ESTIMATED.toString());
    story.setEstimate(13);
    story.setCreatedDate(LocalDateTime.now());
    storyList.add(story);
    List<TeamMember> teamMemberList = new ArrayList<>();
    TeamMember teamMember1 = new TeamMember();
    teamMember1.setId(1);
    teamMember1.setName("A");
    teamMemberList.add(teamMember1);
    TeamMember teamMember2 = new TeamMember();
    teamMember2.setId(2);
    teamMember2.setName("B");
    teamMemberList.add(teamMember2);
    when(storyService.findAllByStatus(StoryStatus.ESTIMATED)).thenReturn(storyList);
    when(teamMemberRepository.findAllByOrderByIdAsc()).thenReturn(teamMemberList);
    PlanDto planDto = planService.createPlan();
    assertNotNull(planDto);
    assertEquals(2, planDto.getPlan().size());
    assertEquals(1, planDto.getPlan().stream().map(MemberCapacityDto::getMemberId).distinct().count());
  }

  @Test
  void member_2_task_2() {
    List<StoryDto> storyList = new ArrayList<>();
    StoryDto story1 = new StoryDto();
    story1.setIssueId(1);
    story1.setTitle("title1");
    story1.setDescription("desc1");
    story1.setAssignedTo(null);
    story1.setStatus(StoryStatus.ESTIMATED.toString());
    story1.setEstimate(13);
    story1.setCreatedDate(LocalDateTime.now());
    storyList.add(story1);
    StoryDto story2 = new StoryDto();
    story2.setIssueId(2);
    story2.setTitle("title2");
    story2.setDescription("desc2");
    story2.setAssignedTo(null);
    story2.setStatus(StoryStatus.ESTIMATED.toString());
    story2.setEstimate(5);
    story2.setCreatedDate(LocalDateTime.now());
    storyList.add(story2);
    List<TeamMember> teamMemberList = new ArrayList<>();
    TeamMember teamMember1 = new TeamMember();
    teamMember1.setId(1);
    teamMember1.setName("A");
    teamMemberList.add(teamMember1);
    TeamMember teamMember2 = new TeamMember();
    teamMember2.setId(2);
    teamMember2.setName("B");
    teamMemberList.add(teamMember2);
    when(storyService.findAllByStatus(StoryStatus.ESTIMATED)).thenReturn(storyList);
    when(teamMemberRepository.findAllByOrderByIdAsc()).thenReturn(teamMemberList);
    PlanDto planDto = planService.createPlan();
    assertNotNull(planDto);
    assertEquals(3, planDto.getPlan().size());
    assertEquals(2, planDto.getPlan().stream().map(MemberCapacityDto::getMemberId).distinct().count());
  }

  @Test
  void member_2_task_3() {
    List<StoryDto> storyList = new ArrayList<>();
    StoryDto story1 = new StoryDto();
    story1.setIssueId(1);
    story1.setTitle("title1");
    story1.setDescription("desc1");
    story1.setAssignedTo(null);
    story1.setStatus(StoryStatus.ESTIMATED.toString());
    story1.setEstimate(13);
    story1.setCreatedDate(LocalDateTime.now());
    storyList.add(story1);
    StoryDto story2 = new StoryDto();
    story2.setIssueId(2);
    story2.setTitle("title2");
    story2.setDescription("desc2");
    story2.setAssignedTo(null);
    story2.setStatus(StoryStatus.ESTIMATED.toString());
    story2.setEstimate(5);
    story2.setCreatedDate(LocalDateTime.now());
    storyList.add(story2);
    StoryDto story3 = new StoryDto();
    story3.setIssueId(3);
    story3.setTitle("title3");
    story3.setDescription("desc3");
    story3.setAssignedTo(null);
    story3.setStatus(StoryStatus.ESTIMATED.toString());
    story3.setEstimate(6);
    story3.setCreatedDate(LocalDateTime.now());
    storyList.add(story3);
    List<TeamMember> teamMemberList = new ArrayList<>();
    TeamMember teamMember1 = new TeamMember();
    teamMember1.setId(1);
    teamMember1.setName("A");
    teamMemberList.add(teamMember1);
    TeamMember teamMember2 = new TeamMember();
    teamMember2.setId(2);
    teamMember2.setName("B");
    teamMemberList.add(teamMember2);
    when(storyService.findAllByStatus(StoryStatus.ESTIMATED)).thenReturn(storyList);
    when(teamMemberRepository.findAllByOrderByIdAsc()).thenReturn(teamMemberList);
    PlanDto planDto = planService.createPlan();
    assertNotNull(planDto);
    assertEquals(4, planDto.getPlan().size());
    assertEquals(2, planDto.getPlan().stream().map(MemberCapacityDto::getMemberId).distinct().count());
  }

  @Test
  void member_3_task_3() {
    List<StoryDto> storyList = new ArrayList<>();
    StoryDto story1 = new StoryDto();
    story1.setIssueId(1);
    story1.setTitle("title1");
    story1.setDescription("desc1");
    story1.setAssignedTo(null);
    story1.setStatus(StoryStatus.ESTIMATED.toString());
    story1.setEstimate(13);
    story1.setCreatedDate(LocalDateTime.now());
    storyList.add(story1);
    StoryDto story2 = new StoryDto();
    story2.setIssueId(2);
    story2.setTitle("title2");
    story2.setDescription("desc2");
    story2.setAssignedTo(null);
    story2.setStatus(StoryStatus.ESTIMATED.toString());
    story2.setEstimate(5);
    story2.setCreatedDate(LocalDateTime.now());
    storyList.add(story2);
    StoryDto story3 = new StoryDto();
    story3.setIssueId(3);
    story3.setTitle("title3");
    story3.setDescription("desc3");
    story3.setAssignedTo(null);
    story3.setStatus(StoryStatus.ESTIMATED.toString());
    story3.setEstimate(6);
    story3.setCreatedDate(LocalDateTime.now());
    storyList.add(story3);
    List<TeamMember> teamMemberList = new ArrayList<>();
    TeamMember teamMember1 = new TeamMember();
    teamMember1.setId(1);
    teamMember1.setName("A");
    teamMemberList.add(teamMember1);
    TeamMember teamMember2 = new TeamMember();
    teamMember2.setId(2);
    teamMember2.setName("B");
    teamMemberList.add(teamMember2);
    TeamMember teamMember3 = new TeamMember();
    teamMember3.setId(3);
    teamMember3.setName("C");
    teamMemberList.add(teamMember3);
    when(storyService.findAllByStatus(StoryStatus.ESTIMATED)).thenReturn(storyList);
    when(teamMemberRepository.findAllByOrderByIdAsc()).thenReturn(teamMemberList);
    PlanDto planDto = planService.createPlan();
    assertNotNull(planDto);
    assertEquals(4, planDto.getPlan().size());
    assertEquals(3, planDto.getPlan().stream().map(MemberCapacityDto::getMemberId).distinct().count());
  }

  @Test
  void member_3_task_3_reinvoke() {
    List<StoryDto> storyList = new ArrayList<>();
    StoryDto story1 = new StoryDto();
    story1.setIssueId(1);
    story1.setTitle("title1");
    story1.setDescription("desc1");
    story1.setAssignedTo(null);
    story1.setStatus(StoryStatus.ESTIMATED.toString());
    story1.setEstimate(13);
    story1.setCreatedDate(LocalDateTime.now());
    storyList.add(story1);
    StoryDto story2 = new StoryDto();
    story2.setIssueId(2);
    story2.setTitle("title2");
    story2.setDescription("desc2");
    story2.setAssignedTo(null);
    story2.setStatus(StoryStatus.ESTIMATED.toString());
    story2.setEstimate(5);
    story2.setCreatedDate(LocalDateTime.now());
    storyList.add(story2);
    StoryDto story3 = new StoryDto();
    story3.setIssueId(3);
    story3.setTitle("title3");
    story3.setDescription("desc3");
    story3.setAssignedTo(null);
    story3.setStatus(StoryStatus.ESTIMATED.toString());
    story3.setEstimate(6);
    story3.setCreatedDate(LocalDateTime.now());
    storyList.add(story3);
    List<TeamMember> teamMemberList = new ArrayList<>();
    TeamMember teamMember1 = new TeamMember();
    teamMember1.setId(1);
    teamMember1.setName("A");
    teamMemberList.add(teamMember1);
    TeamMember teamMember2 = new TeamMember();
    teamMember2.setId(2);
    teamMember2.setName("B");
    teamMemberList.add(teamMember2);
    TeamMember teamMember3 = new TeamMember();
    teamMember3.setId(3);
    teamMember3.setName("C");
    teamMemberList.add(teamMember3);
    when(storyService.findAllByStatus(StoryStatus.ESTIMATED)).thenReturn(storyList);
    when(teamMemberRepository.findAllByOrderByIdAsc()).thenReturn(teamMemberList);
    PlanDto planDto1 = planService.createPlan();
    assertNotNull(planDto1);
    assertEquals(4, planDto1.getPlan().size());
    assertEquals(3, planDto1.getPlan().stream().map(MemberCapacityDto::getMemberId).distinct().count());
    PlanDto planDto2 = planService.createPlan();
    assertNotNull(planDto2);
    assertEquals(planDto1, planDto2);
  }


  @Test
  void member_3_task_4_reinvoke_after_additional_task() {
    List<StoryDto> storyList = new ArrayList<>();
    StoryDto story1 = new StoryDto();
    story1.setIssueId(1);
    story1.setTitle("title1");
    story1.setDescription("desc1");
    story1.setAssignedTo(null);
    story1.setStatus(StoryStatus.ESTIMATED.toString());
    story1.setEstimate(13);
    story1.setCreatedDate(LocalDateTime.now());
    storyList.add(story1);
    StoryDto story2 = new StoryDto();
    story2.setIssueId(2);
    story2.setTitle("title2");
    story2.setDescription("desc2");
    story2.setAssignedTo(null);
    story2.setStatus(StoryStatus.ESTIMATED.toString());
    story2.setEstimate(5);
    story2.setCreatedDate(LocalDateTime.now());
    storyList.add(story2);
    StoryDto story3 = new StoryDto();
    story3.setIssueId(3);
    story3.setTitle("title3");
    story3.setDescription("desc3");
    story3.setAssignedTo(null);
    story3.setStatus(StoryStatus.ESTIMATED.toString());
    story3.setEstimate(6);
    story3.setCreatedDate(LocalDateTime.now());
    storyList.add(story3);
    List<TeamMember> teamMemberList = new ArrayList<>();
    TeamMember teamMember1 = new TeamMember();
    teamMember1.setId(1);
    teamMember1.setName("A");
    teamMemberList.add(teamMember1);
    TeamMember teamMember2 = new TeamMember();
    teamMember2.setId(2);
    teamMember2.setName("B");
    teamMemberList.add(teamMember2);
    TeamMember teamMember3 = new TeamMember();
    teamMember3.setId(3);
    teamMember3.setName("C");
    teamMemberList.add(teamMember3);
    when(storyService.findAllByStatus(StoryStatus.ESTIMATED)).thenReturn(storyList);
    when(teamMemberRepository.findAllByOrderByIdAsc()).thenReturn(teamMemberList);
    PlanDto planDto1 = planService.createPlan();
    assertNotNull(planDto1);
    assertEquals(4, planDto1.getPlan().size());
    assertEquals(3, planDto1.getPlan().stream().map(MemberCapacityDto::getMemberId).distinct().count());
    StoryDto story4 = new StoryDto();
    story4.setIssueId(4);
    story4.setTitle("title4");
    story4.setDescription("desc4");
    story4.setAssignedTo(null);
    story4.setStatus(StoryStatus.ESTIMATED.toString());
    story4.setEstimate(2);
    story4.setCreatedDate(LocalDateTime.now());
    storyList.add(story4);
    when(storyService.findAllByStatus(StoryStatus.ESTIMATED)).thenReturn(storyList);
    PlanDto planDto2 = planService.createPlan();
    assertNotNull(planDto2);
    assertEquals(4, planDto1.getPlan().size());
    assertEquals(3, planDto1.getPlan().stream().map(MemberCapacityDto::getMemberId).distinct().count());
  }
}
