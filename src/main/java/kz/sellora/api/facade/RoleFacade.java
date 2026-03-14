package kz.sellora.api.facade;

import kz.sellora.api.mapper.RoleMapper;
import kz.sellora.api.model.RoleDTO;
import kz.sellora.core.processor.RoleProcessor;
import kz.sellora.core.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleMapper mapper;
    private final RoleService service;
    private final RoleProcessor processor;

    public RoleDTO saveOne(RoleDTO roleDTO) {
        return mapper.toDTO(service.saveOne(
            roleDTO.getName(),
            roleDTO.getContext(),
            roleDTO.getDescription()
        ));
    }

    public RoleDTO getOne(String id) {
        return mapper.toDTO(service.getOne(id));
    }

    public RoleDTO providePermission(String roleId, String permissionId) {
        return mapper.toDTO(processor.providePermission(roleId, permissionId));
    }
}
