package kz.sellora.api.mapper;

import kz.sellora.api.model.CompanyDTO;
import kz.sellora.core.model.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDTO toDTO(Company entity);

}
