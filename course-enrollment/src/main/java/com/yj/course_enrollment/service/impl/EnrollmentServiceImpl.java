package com.yj.course_enrollment.service.impl;

import com.yj.course_enrollment.dto.EmployeeDto;
import com.yj.course_enrollment.dto.EnrollmentDto;
import com.yj.course_enrollment.entity.Enrollment;
import com.yj.course_enrollment.exception.CourseNotFoundException;
import com.yj.course_enrollment.exception.EmployeeNotFoundException;
import com.yj.course_enrollment.exception.EnrollmentNotFoundException;
import com.yj.course_enrollment.repository.CourseRepository;
import com.yj.course_enrollment.repository.EnrollmentRepository;
import com.yj.course_enrollment.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private WebClient webClient;

    @Override
    public void enrollEmployee(EnrollmentDto enrollmentDto) {
        // Check if employee exists
        EmployeeDto employeeDto = webClient.get()
                .uri("http://localhost:8090/api/fetch-id?id=" + enrollmentDto.getEmpId())
                .retrieve()
                .bodyToMono(EmployeeDto.class)
                .block();


        if (employeeDto == null) {
            throw new EmployeeNotFoundException("Employee not found with ID - " + enrollmentDto.getEmpId());
        }
        // Check if course exists
        courseRepository.findById(enrollmentDto.getCourseId()).orElseThrow(
                () -> new CourseNotFoundException("Course not found with ID - " + enrollmentDto.getCourseId())
        );

        Enrollment enrollment = new Enrollment();
        enrollment.setEmpId(enrollmentDto.getEmpId());
        enrollment.setCourseId(enrollmentDto.getCourseId());
        enrollment.setStatus(false);
        enrollmentRepository.save(enrollment);
    }

    @Override
    public boolean updateStatus(EnrollmentDto enrollmentDto) {
        boolean isUpdated = false;

        // Check if employee exists
        EmployeeDto employeeDto = webClient.get()
                .uri("http://localhost:8090/api/fetch-id?id=" + enrollmentDto.getEmpId())
                .retrieve()
                .bodyToMono(EmployeeDto.class)
                .block();

        if (employeeDto == null) {
            return isUpdated;
        }
        // Check if enrollment exists
        Enrollment enrollment = (Enrollment) enrollmentRepository.findByEmpIdAndCourseId(enrollmentDto.getEmpId(), enrollmentDto.getCourseId()).orElseThrow(
                () -> new EnrollmentNotFoundException("Enrollment does not exist for employee ID " + enrollmentDto.getEmpId() + " and course ID " + enrollmentDto.getCourseId())
        );

        // Update enrollment status
        enrollment.setStatus(true);
        enrollmentRepository.save(enrollment);
        isUpdated = true;

        return isUpdated;
    }

}
