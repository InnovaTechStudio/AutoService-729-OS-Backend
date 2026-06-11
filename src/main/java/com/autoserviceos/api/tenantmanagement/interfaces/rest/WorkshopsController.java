package com.autoserviceos.api.tenantmanagement.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshops")
public class WorkshopsController {

    @GetMapping
    public List<String> getAllWorkshops() {
        return List.of(
                "AutoService Central",
                "AutoService Lima Norte",
                "AutoService Surco"
        );
    }
}