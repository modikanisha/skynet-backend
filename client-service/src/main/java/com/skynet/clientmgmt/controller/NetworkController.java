package com.skynet.clientmgmt.controller;


import com.skynet.clientmgmt.dto.request.NetworkAddRequest;
import com.skynet.clientmgmt.service.NetworkService;
import com.skynet.commons.models.Network;
import com.skynet.commons.response.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/network")
public class NetworkController {

    private final NetworkService networkService;

    @PostMapping()
    ResponseEntity<GenericResponse> create(@RequestBody final NetworkAddRequest networkAddRequest) {
        GenericResponse genericResponse = new GenericResponse(networkService.create(networkAddRequest));
        return ResponseEntity.ok(genericResponse);
    }

    @PatchMapping()
    ResponseEntity<GenericResponse> update(@RequestBody final Network network) {
        GenericResponse genericResponse = new GenericResponse(networkService.update(network));
        return ResponseEntity.ok(genericResponse);
    }

    @GetMapping("/list")
    ResponseEntity<GenericResponse> list() {
        GenericResponse genericResponse = new GenericResponse(networkService.list());
        return ResponseEntity.ok(genericResponse);
    }

}
