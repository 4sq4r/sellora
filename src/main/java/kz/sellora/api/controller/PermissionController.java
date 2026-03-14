package kz.sellora.api.controller;

import kz.sellora.api.facade.PermissionFacade;
import kz.sellora.api.model.PermissionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionFacade facade;

    @PostMapping
    public PermissionDTO saveOne(@RequestBody PermissionDTO permissionDTO) {
        return facade.saveOne(permissionDTO);
    }

    @GetMapping("/{id}")
    public PermissionDTO getOne(@PathVariable String id) {
        return facade.getOne(id);
    }
}
