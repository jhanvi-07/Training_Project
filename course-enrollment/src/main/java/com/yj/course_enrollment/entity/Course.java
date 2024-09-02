package com.yj.course_enrollment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Course extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Course name cannot be empty")
    private String title;
    @NotNull(message = "Course duration can not be empty")
    private Integer duration;
    @NotEmpty(message = "Course author can not be empty")
    private String author;
}
