package com.yj.course_enrollment.service;

import com.yj.course_enrollment.dto.EnrollmentDto;
import jakarta.validation.Valid;

public interface IEnrollmentService {

    void enrollEmployee(@Valid EnrollmentDto enrollmentDto);

    boolean updateStatus(EnrollmentDto enrollmentDto);

}
