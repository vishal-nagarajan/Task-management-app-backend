package com.example.TaskApp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Work {
    @Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String placeOfWork;
    private String peopleAtWork;
    private String natureOfWork;
    private Timestamp workStartTime;
    private Timestamp workEndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id" ,name = "task_id")
    private Task task;

}
