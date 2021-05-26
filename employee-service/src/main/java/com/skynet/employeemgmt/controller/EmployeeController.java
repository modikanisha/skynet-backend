package com.skynet.employeemgmt.controller;


import com.skynet.commons.models.Employee;
import com.skynet.commons.response.GenericResponse;
import com.skynet.employeemgmt.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping()
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

/*
    @GetMapping("/list")
    public ResponseEntity<GenericResponse> findAllByRequestId(@PathVariable("request-type") String requestType, @PathVariable("request-id") String requestId, Pageable pageable) {
           // Page<LetterEntity> letterAndResponse = letterAndResponseService.getAllLetterAndResponseList(requestId,requestType, pageable);
            return ResponseEntity.ok(new GenericResponse(employeeService.getAllLetterAndResponseList(requestId,requestType, pageable)));
        }*/
    @PostMapping("/create")
    ResponseEntity<GenericResponse>  create(@RequestBody final Employee employee) {
        GenericResponse genericResponse = new GenericResponse(employeeService.createEmployee(employee));
        return ResponseEntity.ok(genericResponse);
    }

}
