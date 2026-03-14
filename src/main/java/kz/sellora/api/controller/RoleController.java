package kz.sellora.api.controller;

import kz.sellora.api.facade.RoleFacade;
import kz.sellora.api.model.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleFacade facade;

    @PostMapping
    public RoleDTO saveOne(@RequestBody RoleDTO roleDTO) {
        return facade.saveOne(roleDTO);
    }

    @GetMapping("/{id}")
    public RoleDTO getOne(@PathVariable String id) {
        return facade.getOne(id);
    }

    @PostMapping("/provide-permission")
    public RoleDTO providePermission(@RequestParam String roleId,
                                     @RequestParam String permissionId) {
        return facade.providePermission(roleId, permissionId);
    }
}
