package com.yj.course_enrollment.controller;

import com.yj.course_enrollment.dto.CourseDto;
import com.yj.course_enrollment.dto.EnrollmentDto;
import com.yj.course_enrollment.dto.ResponseDto;
import com.yj.course_enrollment.service.ICourseService;
import com.yj.course_enrollment.service.IEnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class CourseEnrollmentController {
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private IEnrollmentService iEnrollmentService;


    @PostMapping("/course/create")
    public ResponseEntity<ResponseDto> createCourse(@RequestBody @Valid CourseDto courseDto){
        iCourseService.createCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("Created successfully", "201")
        );
    }

    @GetMapping("/course/fetch-title")
    public ResponseEntity<CourseDto> fetchCourse(@RequestParam String title){
        CourseDto courseDto = iCourseService.fetchCourse(title);
        return ResponseEntity.status(HttpStatus.OK).body(courseDto);
    }

    @GetMapping("/course/fetch-all")
    public ResponseEntity<List<CourseDto>> fetchAllEmployee(){
        List<CourseDto> employeeDto = iCourseService.fetchAllEmployee();
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    @GetMapping("/course/fetch-id")
    public ResponseEntity<CourseDto> fetchCourseById(@RequestParam Integer id){
        CourseDto courseDto = iCourseService.fetchCourseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(courseDto);
    }

    @PutMapping("/course/update")
    public ResponseEntity<ResponseDto> updateCourse(@RequestBody @Valid CourseDto courseDto){
        boolean isUpdated = iCourseService.updateCourse(courseDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Updated successfully", "203")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not Updated", "501")
            );
        }
    }

    @DeleteMapping("/course/delete")
    public ResponseEntity<ResponseDto> deleteCourse(@RequestParam String title){
        boolean isDeleted = iCourseService.deleteCourse(title);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Deleted successfully", "200")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not Deleted", "501")
            );
        }
    }

    @PostMapping("/enroll/create")
    public ResponseEntity<ResponseDto> enrollEmployee(@RequestBody @Valid EnrollmentDto enrollmentDto){
        iEnrollmentService.enrollEmployee(enrollmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("Enrollment created successfully", "205")
        );
    }

    @PostMapping("/enroll/update")
    public ResponseEntity<ResponseDto> updateStatus(@RequestBody @Valid EnrollmentDto enrollmentDto){
        iEnrollmentService.updateStatus(enrollmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("Enrollment Status updated successfully", "205")
        );
    }

    @GetMapping("/greet")
    public String greet(){
        return "Hello world";
    }
}
