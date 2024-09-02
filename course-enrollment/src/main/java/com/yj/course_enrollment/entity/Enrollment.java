package com.yj.course_enrollment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="enrollment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eId;
    @NotNull(message = "Employee ID cannot be empty")
    private Integer empId;
    @NotNull(message = "Course ID cannot be empty")
    private Integer courseId;
    @NotNull(message = "Enrollment status cannot be empty")
    private Boolean status;
}
