package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "solution")
public class Solution {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "solution_id")
    private int solution_id;

    @Column(name = "course_id")
    private String course_id;

    @Column(name = "task_id")
    private String task_id;

    @Column(name = "solution")
    private String solution;

    @Column(name = "student_name")
    private String student_name;

    @Column(name = "is_checked")
    private Boolean is_checked;

    @Column(name = "is_right")
    private Boolean is_right;

    @Column(name = "fileName")
    private String fileName;
}
