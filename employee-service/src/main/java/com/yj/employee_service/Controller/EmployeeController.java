package com.yj.employee_service.Controller;

import com.yj.employee_service.dto.EmployeeDto;
import com.yj.employee_service.dto.ResponseDto;
import com.yj.employee_service.service.IEmployeeService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name="Employee",
        description="Employee Controller for CRUD operations for UKG Training",
        externalDocs = @ExternalDocumentation(
                url="https://spring.io",
                description="External Documentation"
        )
)
@Validated
@RestController
@RequestMapping("/api")

public class EmployeeController {

    @Value("${build.version}")//this syntax allows to inject the value (from application.yml)
    private String buildVersion;
    @Autowired
    private IEmployeeService iEmployeeService;

    @Operation(
            description = "Create new employee operations",
            summary="Post API to create new employee in the system"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Success in creating employee"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        iEmployeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("Created successfully","201")
        );
    }

    @GetMapping("/fetch-title")
    public ResponseEntity<EmployeeDto> fetchEmployee(@RequestParam @Pattern(regexp="(^$|[0-9]{10})", message="Mobile number should have 10 digits") String mobileNumber){
        EmployeeDto employeeDto = iEmployeeService.fetchEmployee(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<List<EmployeeDto>> fetchAllEmployee(){
        List<EmployeeDto> employeeDto = iEmployeeService.fetchAllEmployee();
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        boolean isUpdated =  iEmployeeService.updateEmployee(employeeDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Updated Successfully", "203"));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not updated", "501"));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEmployee(@RequestParam @Pattern(regexp="(^$|[0-9]{10})", message="Mobile number should have 10 digits") String mobileNumber){
        boolean isDeleted = iEmployeeService.deleteEmployee(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Deleted Successfully","200")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not Deleted","501")
            );
        }
    }

    @GetMapping("/fetch-id")
    public ResponseEntity<EmployeeDto> fetchEmployeeById(@RequestParam Integer id) {
        EmployeeDto employeeDto = iEmployeeService.fetchEmployeeById(id);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/great")
    public String great(){
        return "Hello world";
    }

    @GetMapping("/build-info")
    public String buildInfo(){
        return buildVersion; //Command line(--) has the highest precedence then VM(-D) then env(caps nd 2 words separated by _) then yml
    }

}
