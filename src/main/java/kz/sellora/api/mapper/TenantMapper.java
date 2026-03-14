package kz.sellora.api.mapper;

import kz.sellora.api.model.TenantDTO;
import kz.sellora.core.model.entity.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    TenantDTO toDTO(Tenant entity);
}
