package com.example.issuetracker.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "plan")
public class AppConfigProperties {
  private int defaultCapacity;
  private int weeksCount;
}
