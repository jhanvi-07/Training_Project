package com.yj.course_enrollment.mapper;

import com.yj.course_enrollment.dto.EnrollmentDto;
import com.yj.course_enrollment.entity.Enrollment;

public class EnrollmentMapper {

    public static EnrollmentDto mapToEnrollmentDto(Enrollment enrollment, EnrollmentDto enrollmentDto) {
        enrollmentDto.setEmpId(enrollment.getEmpId());
        enrollmentDto.setCourseId(enrollment.getCourseId());
        return enrollmentDto;
    }

    public static Enrollment mapToEnrollment(EnrollmentDto enrollmentDto, Enrollment enrollment) {
        enrollment.setEmpId(enrollmentDto.getEmpId());
        enrollment.setCourseId(enrollmentDto.getCourseId());
        return enrollment;
    }
}
