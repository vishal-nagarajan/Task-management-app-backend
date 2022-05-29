package com.example.TaskApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDTO {
	private String placeOfWork;
	private String peopleAtWork;
	private String natureOfWork;
	private Timestamp workStartTime;
	private Timestamp workEndTime;
	private Long task;
}
