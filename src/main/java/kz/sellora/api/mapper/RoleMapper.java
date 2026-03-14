package kz.sellora.api.mapper;

import kz.sellora.api.model.RoleDTO;
import kz.sellora.core.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDTO(Role entity);
}
