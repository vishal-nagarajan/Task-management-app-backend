package com.example.TaskApp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class TentativeTodoDTO {
  private String taskName;
  private Long taskId;
}
