package com.example.issuetracker.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@ToString
public class MemberCapacityDto {
    private int memberId;
    private int weekId;
    private int remainingCapacity;
    private List<StoryDto> storyList;
}