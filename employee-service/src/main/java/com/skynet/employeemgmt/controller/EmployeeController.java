package com.skynet.employeemgmt.controller;


import com.skynet.commons.models.Employee;
import com.skynet.commons.response.GenericResponse;
import com.skynet.employeemgmt.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping()
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> findAllByRequestId(Pageable pageable) {
        return ResponseEntity.ok(new GenericResponse(employeeService.list(pageable)));
    }

    @PostMapping("/")
    ResponseEntity<GenericResponse> create(@RequestBody final Employee employee) {
        GenericResponse genericResponse = new GenericResponse(employeeService.createEmployee(employee));
        return ResponseEntity.ok(genericResponse);
    }
    @PatchMapping("/")
    ResponseEntity<GenericResponse> update(@RequestBody final Employee employee) {
        GenericResponse genericResponse = new GenericResponse(employeeService.updateEmployee(employee));
        return ResponseEntity.ok(genericResponse);
    }
}
