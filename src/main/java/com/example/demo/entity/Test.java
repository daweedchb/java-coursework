package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private String ID;

    @Column(name = "course_number")
    private String course_number;

    @Column(name = "task_number")
    private String task_number;

    @Column(name = "input")
    private String input;

    @Column(name = "output")
    private String output;
}