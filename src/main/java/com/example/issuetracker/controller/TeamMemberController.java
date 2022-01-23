package com.example.issuetracker.controller;

import com.example.issuetracker.dto.TeamMemberDto;
import com.example.issuetracker.service.TeamMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class TeamMemberController {

  private final TeamMemberService teamMemberService;

  @PostMapping("/teamMember")
  public ResponseEntity<TeamMemberDto> createTeamMember(@RequestBody TeamMemberDto teamMemberDto) {
    TeamMemberDto teamMemberDtoCreated = teamMemberService.createTeamMember(teamMemberDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(teamMemberDtoCreated);
  }

  @PutMapping("/teamMember")
  public ResponseEntity<TeamMemberDto> updateTeamMember(@RequestBody TeamMemberDto teamMemberDto) {
    TeamMemberDto teamMemberDtoUpdated = teamMemberService.updateTeamMember(teamMemberDto);
    return ResponseEntity.status(HttpStatus.OK).body(teamMemberDtoUpdated);
  }

  @DeleteMapping("/teamMember/{teamMemberId}")
  public ResponseEntity<Void> deleteTeamMember(@PathVariable final Integer teamMemberId) {
    teamMemberService.deleteTeamMember(teamMemberId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/teamMember")
  public ResponseEntity<List<TeamMemberDto>> getTeamMemberList() {
    List<TeamMemberDto> teamMemberDtoList = teamMemberService.getTeamMemberList();
    return ResponseEntity.ok().body(teamMemberDtoList);
  }

  @GetMapping("/teamMember/{teamMemberId}")
  public ResponseEntity<TeamMemberDto> getTeamMember(@PathVariable final Integer teamMemberId) {
    TeamMemberDto teamMemberDto = teamMemberService.getTeamMember(teamMemberId);
    return ResponseEntity.ok().body(teamMemberDto);
  }

}
