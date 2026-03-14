package kz.sellora.api.mapper;

import kz.sellora.api.model.PermissionDTO;
import kz.sellora.core.model.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDTO toDTO(Permission entity);
}
