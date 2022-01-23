package com.example.issuetracker.service;

import com.example.issuetracker.dto.TeamMemberDto;
import com.example.issuetracker.entity.TeamMember;
import com.example.issuetracker.repository.TeamMemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TeamMemberService {

  private final TeamMemberRepository teamMemberRepository;

  public TeamMemberDto createTeamMember(TeamMemberDto teamMemberDto) {
    TeamMember teamMember = generateTeamMember(teamMemberDto.getName());
    log.info("{} {}",teamMemberDto.getName(), teamMember.getName());
    teamMember = teamMemberRepository.save(teamMember);
    return generateTeamMemberDto(teamMember);
  }

  public TeamMemberDto updateTeamMember(TeamMemberDto teamMemberDto) {
    TeamMember teamMember = generateTeamMember(teamMemberDto);
    log.info("{} {}",teamMemberDto.getName(), teamMember.getName());
    teamMember = teamMemberRepository.save(teamMember);
    return generateTeamMemberDto(teamMember);
  }

  private TeamMember generateTeamMember(String name) {
    TeamMember teamMember = new TeamMember();
    teamMember.setName(name);
    return teamMember;
  }

  private TeamMember generateTeamMember(TeamMemberDto teamMemberDto) {
    TeamMember teamMember = new TeamMember();
    teamMember.setId(teamMemberDto.getId());
    teamMember.setName(teamMemberDto.getName());
    return teamMember;
  }

  public TeamMemberDto getTeamMember(Integer teamMemberId) {
    TeamMember teamMember = teamMemberRepository.getById(teamMemberId);
    TeamMemberDto teamMemberDto = generateTeamMemberDto(teamMember);
    return teamMemberDto;
  }

  public List<TeamMemberDto> getTeamMemberList() {
    List<TeamMember> teamMemberList = teamMemberRepository.findAll();
    List<TeamMemberDto> teamMemberDtoList = generateTeamMemberDtoList(teamMemberList);
    return teamMemberDtoList;
  }

  private List<TeamMemberDto> generateTeamMemberDtoList(List<TeamMember> teamMemberList) {
    List<TeamMemberDto> teamMemberDtoList = new ArrayList<>();
    for(TeamMember teamMember : teamMemberList) {
      teamMemberDtoList.add(generateTeamMemberDto(teamMember));
    }
    return teamMemberDtoList;
  }

  private TeamMemberDto generateTeamMemberDto(TeamMember teamMember) {
    TeamMemberDto teamMemberDto = new TeamMemberDto();
    teamMemberDto.setId(teamMember.getId());
    teamMemberDto.setName(teamMember.getName());
    return teamMemberDto;
  }

  public void deleteTeamMember(Integer teamMemberId) {
    teamMemberRepository.deleteById(teamMemberId);
  }

}
