package kz.sellora.api.facade;

import kz.sellora.api.mapper.PermissionMapper;
import kz.sellora.api.model.PermissionDTO;
import kz.sellora.core.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionFacade {

    private final PermissionMapper mapper;
    private final PermissionService service;

    public PermissionDTO saveOne(PermissionDTO permissionDTO) {
        return mapper.toDTO(service.saveOne(
            permissionDTO.getName(),
            permissionDTO.getDescription()
        ));
    }

    public PermissionDTO getOne(String id) {
        return mapper.toDTO(service.getOne(id));
    }
}
