package com.yj.course_enrollment.mapper;

import com.yj.course_enrollment.dto.CourseDto;
import com.yj.course_enrollment.entity.Course;

public class CourseMapper {
    public static Course mapToCourse(CourseDto courseDto, Course course) {
        course.setTitle(courseDto.getTitle());
        course.setDuration(courseDto.getDuration());
        course.setAuthor(courseDto.getAuthor());
        return course;
    }
    public static CourseDto mapToCourseDto(Course course, CourseDto courseDto) {
        courseDto.setTitle(course.getTitle());
        courseDto.setDuration(course.getDuration());
        courseDto.setAuthor(course.getAuthor());
        return courseDto;
    }
}
