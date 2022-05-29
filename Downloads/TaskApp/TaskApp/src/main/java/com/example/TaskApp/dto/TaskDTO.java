package com.example.TaskApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
  private String title;
  private String description;
  private Long client;
  private Long project;
  private Date startDate;
  private Date endDate;
  private Long userId;
	private Boolean taskStatus;
}
