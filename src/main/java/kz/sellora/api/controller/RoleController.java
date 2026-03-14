package kz.sellora.api.controller;

import kz.sellora.core.model.entity.Permission;
import kz.sellora.core.model.entity.Role;
import kz.sellora.core.model.enums.RoleContext;
import kz.sellora.core.repository.jpa.PermissionRepository;
import kz.sellora.core.repository.jpa.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository repository;
    private final PermissionRepository permissionRepository;

    @PostMapping
    public Role saveOne(@RequestParam String name,
                        @RequestParam RoleContext context) {
        Role role = new Role();
        role.setName(name);
        role.setContext(context);

        return repository.save(role);
    }

    @PostMapping("/provide-permission")
    public Role updateOne(@RequestParam String roleId,
                          @RequestParam String permissionId) {
        Permission permission = permissionRepository.findById(permissionId).get();
        Role role = repository.findById(roleId).get();
        role.getPermissions().add(permission);

        return repository.save(role);
    }
}
