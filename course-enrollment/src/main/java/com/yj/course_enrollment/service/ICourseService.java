package com.yj.course_enrollment.service;

import com.yj.course_enrollment.dto.CourseDto;
import com.yj.course_enrollment.dto.EnrollmentDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ICourseService {
    void createCourse(CourseDto courseDto);

    CourseDto fetchCourse(String title);

    boolean updateCourse(CourseDto courseDto);

    boolean deleteCourse(String title);

    List<CourseDto> fetchAllEmployee();

    CourseDto fetchCourseById(Integer id);
}
