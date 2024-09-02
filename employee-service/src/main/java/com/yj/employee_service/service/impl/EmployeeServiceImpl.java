package com.yj.employee_service.service.impl;

import com.yj.employee_service.Entity.Employee;
import com.yj.employee_service.dto.EmployeeDto;
import com.yj.employee_service.exception.EmployeeAlreadyExistsException;
import com.yj.employee_service.exception.EmployeeNotFoundException;
import com.yj.employee_service.mapper.EmployeeMapper;
import com.yj.employee_service.repository.EmployeeRepository;
import com.yj.employee_service.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public void createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional= repository.findByMobileNumber(employeeDto.getMobileNumber());
        if(employeeOptional.isPresent()){
            throw new EmployeeAlreadyExistsException("Employee already exists with mobile number - " + employeeDto.getMobileNumber());
        }
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto, new Employee());
        repository.save(employee);
    }

    @Override
    public EmployeeDto fetchEmployee(String mobileNumber) {
        Employee employee=repository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new EmployeeNotFoundException("Employee does not exists for mobile - " + mobileNumber)
        );

        EmployeeDto employeeDto=EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto());
        return employeeDto;
    }

    @Override
    public boolean updateEmployee(EmployeeDto employeeDto) {
        boolean isUpdated = false;
        if(employeeDto.getMobileNumber() == null){
            return isUpdated;
        }
        Employee employee = repository.findByMobileNumber(employeeDto.getMobileNumber()).orElseThrow(
                () -> new EmployeeNotFoundException("Employee does not exists for mobile number - " + employeeDto.getMobileNumber())
        );

        Employee updatedEmployee = EmployeeMapper.mapToEmployee(employeeDto, employee);
        repository.save(updatedEmployee);
        isUpdated = true;

        return isUpdated;
    }

    @Override
    public boolean deleteEmployee(String mobileNumber) {
        boolean isDeleted = false;
        if(mobileNumber == null){
            return isDeleted;
        }
        repository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new EmployeeNotFoundException("Employee does not exists for mobile number - " + mobileNumber)
        );
        repository.deleteByMobileNumber(mobileNumber);
        isDeleted = true;
        return isDeleted;
    }

    @Override
    public List<EmployeeDto> fetchAllEmployee() {
        List<Employee> employee=repository.findAll();
        List<EmployeeDto> employeeDto=new ArrayList<>();
        for(Employee t:employee)
        {
            employeeDto.add(EmployeeMapper.mapToEmployeeDto(t,new EmployeeDto()));
        }
        return employeeDto;
    }

    @Override
    public EmployeeDto fetchEmployeeById(Integer id) {
        Employee employee = repository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee not found with ID - " + id)
        );
        return EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto());
    }
}