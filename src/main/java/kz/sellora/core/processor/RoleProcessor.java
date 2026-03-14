package kz.sellora.core.processor;

import kz.sellora.core.model.entity.Permission;
import kz.sellora.core.model.entity.Role;
import kz.sellora.core.service.PermissionService;
import kz.sellora.core.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleProcessor {

    private final RoleService roleService;
    private final PermissionService permissionService;

    public Role providePermission(String roleId, String permissionId) {
        Role role = roleService.getOne(roleId);
        Permission permission = permissionService.getOne(permissionId);

        role.getPermissions().add(permission);
        return roleService.updateOne(role);
    }
}
