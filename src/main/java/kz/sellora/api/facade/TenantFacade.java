package kz.sellora.api.facade;

import kz.sellora.api.mapper.TenantMapper;
import kz.sellora.api.model.TenantDTO;
import kz.sellora.core.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TenantFacade {

    private final TenantMapper mapper;
    private final TenantService service;

    public TenantDTO saveOne(TenantDTO tenantDTO) {
        return mapper.toDTO(service.saveOne(tenantDTO.getName()));
    }

    public TenantDTO getOne(String id) {
        return mapper.toDTO(service.getOne(id));
    }
}
