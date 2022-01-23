package com.example.issuetracker.controller;

import com.example.issuetracker.dto.PlanDto;
import com.example.issuetracker.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class PlanController {

  private final PlanService planService;

  @GetMapping("/plan")
  public ResponseEntity<PlanDto> createPlan() {
    PlanDto planDto = planService.createPlan();
    return ResponseEntity.status(HttpStatus.OK).body(planDto);
  }

}
