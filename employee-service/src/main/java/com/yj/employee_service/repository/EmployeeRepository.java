package com.yj.employee_service.repository;

import com.yj.employee_service.Entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Optional<Employee> findByMobileNumber(String mobileNumber);
    @Transactional
    @Modifying

    void deleteByMobileNumber(String mobileNumber);
}
