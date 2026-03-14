package kz.sellora.api.controller;

import kz.sellora.core.model.entity.Tenant;
import kz.sellora.core.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService service;

    @PostMapping
    public Tenant saveOne(@RequestBody String name) {
        return service.saveOne(name);
    }
}
