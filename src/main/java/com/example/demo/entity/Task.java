package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int task_id;

    @Column(name = "courseId")
    private String courseId;

    @Column(name = "name")
    private String name;

    @Column(name = "statement")
    private String statement;

    @Column(name = "complexity")
    private String complexity;

    @Column(name = "standard")
    private String standard;
}
