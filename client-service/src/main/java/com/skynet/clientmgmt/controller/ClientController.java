package com.skynet.clientmgmt.controller;

import com.skynet.clientmgmt.service.ClientService;
import com.skynet.commons.models.Client;
import com.skynet.commons.response.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping()
@AllArgsConstructor
public class ClientController {

   private final ClientService clientService;

    @PostMapping("/create")
    ResponseEntity<GenericResponse> create(@RequestBody final Client client) {
        GenericResponse genericResponse = new GenericResponse(clientService.createClient(client));
        return ResponseEntity.ok(genericResponse);
    }

    @GetMapping("/list")
    ResponseEntity<GenericResponse> list() {
        GenericResponse genericResponse = new GenericResponse(clientService.clientList());
        return ResponseEntity.ok(genericResponse);
    }

}
