package com.yj.employee_service.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message="Employee name cannot be empty")
    private String name;
    @NotEmpty (message="Email cannot be empty")
    private String email;
    @NotEmpty (message="Mobile Number cannot be empty")
    @Pattern(regexp="(^$|[0-9]{10})", message="Mobile number should have 10 digits")
    private String mobileNumber;
    private String designation;
    private String department;
}

