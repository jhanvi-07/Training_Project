package com.yj.course_enrollment.repository;

import com.yj.course_enrollment.entity.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByTitle(String title); // optional bcz course may or may not be present with that title
    @Transactional
    @Modifying
    void deleteByTitle(String title);
}
