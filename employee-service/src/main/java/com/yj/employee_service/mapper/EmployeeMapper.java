package com.yj.employee_service.mapper;

import com.yj.employee_service.dto.EmployeeDto;
import com.yj.employee_service.Entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEmployee(EmployeeDto employeeDto, Employee employee){
        employee.setName(employeeDto.getName());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobileNumber(employeeDto.getMobileNumber());
        return employee;
    }
    public static EmployeeDto mapToEmployeeDto(Employee employee, EmployeeDto employeeDto){
        employeeDto.setName(employee.getName());
        employeeDto.setDesignation(employee.getDesignation());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setMobileNumber(employee.getMobileNumber());
        return employeeDto;
    }
}
