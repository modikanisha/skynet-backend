package com.skynet.employeemgmt.controller;


import com.skynet.commons.models.Role;
import com.skynet.commons.response.GenericResponse;
import com.skynet.employeemgmt.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    ResponseEntity<GenericResponse> create(@RequestBody final Role role) {
        GenericResponse genericResponse = new GenericResponse(roleService.createRole(role));
        return ResponseEntity.ok(genericResponse);
    }

    @GetMapping("/list")
    ResponseEntity<GenericResponse> list() {
        GenericResponse genericResponse = new GenericResponse(roleService.list());
        return ResponseEntity.ok(genericResponse);
    }

}
