package com.skynet.employeemgmt.dto;

import com.skynet.commons.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {

    private Employee employee;
    private String token;
}
