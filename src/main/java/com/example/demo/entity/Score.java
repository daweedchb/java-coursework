package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "solution_id")
    private long solution_id;

    @Column(name = "student_id")
    private String student_id;

    @Column(name = "course_id")
    private String course_id;

    @Column(name = "task_id")
    private int task_id;

    @Column(name = "tries")
    private int tries;

    @Column(name = "is_right")
    private Boolean is_right;

    @Column(name = "points")
    private int points;
}
