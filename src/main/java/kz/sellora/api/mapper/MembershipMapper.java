package kz.sellora.api.mapper;

import kz.sellora.api.model.MembershipDTO;
import kz.sellora.core.model.entity.Membership;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembershipMapper {

    MembershipDTO toDTO(Membership entity);
}
