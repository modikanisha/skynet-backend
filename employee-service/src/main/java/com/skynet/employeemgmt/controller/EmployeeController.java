package com.skynet.employeemgmt.controller;


import com.skynet.employeemgmt.models.Employee;
import com.skynet.employeemgmt.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

/*
    @GetMapping("/list")
    public ResponseEntity<GenericResponse> findAllByRequestId(@PathVariable("request-type") String requestType, @PathVariable("request-id") String requestId, Pageable pageable) {
           // Page<LetterEntity> letterAndResponse = letterAndResponseService.getAllLetterAndResponseList(requestId,requestType, pageable);
            return ResponseEntity.ok(new GenericResponse(employeeService.getAllLetterAndResponseList(requestId,requestType, pageable)));
        }*/
    @PostMapping("/create")
    public Employee create(@RequestBody final Employee employee) {
       return employeeService.createEmployee(employee);
      //  return ResponseEntity.ok(new GenericResponse(employeeService.createEmployee(employee)));
    }
}
