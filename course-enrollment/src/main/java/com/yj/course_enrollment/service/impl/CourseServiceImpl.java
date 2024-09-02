package com.yj.course_enrollment.service.impl;

import com.yj.course_enrollment.dto.CourseDto;
import com.yj.course_enrollment.entity.Course;
import com.yj.course_enrollment.exception.CourseAlreadyExistsException;
import com.yj.course_enrollment.exception.CourseNotFoundException;
import com.yj.course_enrollment.mapper.CourseMapper;
import com.yj.course_enrollment.repository.CourseRepository;
import com.yj.course_enrollment.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public void createCourse(CourseDto courseDto){
        Optional<Course> courseOptional = repository.findByTitle(courseDto.getTitle());
        if(courseOptional.isPresent()){
            throw new CourseAlreadyExistsException("Course already exists with title - " + courseDto.getTitle());
        }
        Course course = CourseMapper.mapToCourse(courseDto, new Course());
        repository.save(course);
    }

    @Override
    public CourseDto fetchCourse(String title) {
        Course course = repository.findByTitle(title).orElseThrow(
                () -> new CourseNotFoundException("Course does not exist with title - " + title)
        );
        CourseDto courseDto = CourseMapper.mapToCourseDto(course,new CourseDto());
        return courseDto;
    }

    @Override
    public boolean updateCourse(CourseDto courseDto) {
        boolean isUpdated = false;
        if(courseDto.getTitle()==null)
        {
            return isUpdated;
        }
        Course course = repository.findByTitle(courseDto.getTitle()).orElseThrow(
                () -> new CourseNotFoundException("Course does not exist with title - " + courseDto.getTitle())
        );
        Course updatedCourse = CourseMapper.mapToCourse(courseDto, course);
        repository.save(updatedCourse);
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteCourse(String title) {
        boolean isDeleted = false;
        if(title==null)
        {
            return isDeleted;
        }
        repository.findByTitle(title).orElseThrow(
                () -> new CourseNotFoundException("Course does not exist with title - " + title)
        );
        repository.deleteByTitle(title);
        isDeleted = true;
        return isDeleted;
    }

    @Override
    public List<CourseDto> fetchAllEmployee() {
        List<Course> course=repository.findAll();
        List<CourseDto> courseDto=new ArrayList<>();
        for(Course t:course)
        {
            courseDto.add(CourseMapper.mapToCourseDto(t,new CourseDto()));
        }
        return courseDto;
    }

    @Override
    public CourseDto fetchCourseById(Integer id) {
        Course course = repository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course does not exist with id - " + id)
        );
        CourseDto courseDto = CourseMapper.mapToCourseDto(course,new CourseDto());
        return courseDto;
    }

}
