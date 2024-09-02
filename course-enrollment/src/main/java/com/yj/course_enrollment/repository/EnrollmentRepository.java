package com.yj.course_enrollment.repository;

import com.yj.course_enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    Optional<Enrollment> findByEmpIdAndCourseId(Integer empId, Integer courseId);
}