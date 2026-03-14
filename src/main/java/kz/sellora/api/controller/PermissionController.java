package kz.sellora.api.controller;

import kz.sellora.core.model.entity.Permission;
import kz.sellora.core.repository.jpa.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionRepository repository;

    @PostMapping
    public Permission saveOne(@RequestParam String name) {
        Permission entity = new Permission();
        entity.setName(name);
        return repository.save(entity);
    }
}
