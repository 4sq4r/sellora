package kz.sellora.api.controller;

import kz.sellora.api.facade.TenantFacade;
import kz.sellora.api.model.TenantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantFacade facade;

    @PostMapping
    public TenantDTO saveOne(@RequestBody TenantDTO tenantDTO) {
        return facade.saveOne(tenantDTO);
    }

    @GetMapping("/{id}")
    public TenantDTO getOne(@PathVariable String id) {
        return facade.getOne(id);
    }
}
