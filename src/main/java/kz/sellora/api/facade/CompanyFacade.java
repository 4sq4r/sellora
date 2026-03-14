package kz.sellora.api.facade;

import kz.sellora.api.mapper.CompanyMapper;
import kz.sellora.api.model.CompanyDTO;
import kz.sellora.core.model.entity.Company;
import kz.sellora.core.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyFacade {

    private final CompanyMapper mapper;
    private final CompanyService service;

    public CompanyDTO saveOne(CompanyDTO companyDTO) {
        return mapper.toDTO(service.saveOne(companyDTO.getName()));
    }

    public CompanyDTO getOne(String id) {
        return mapper.toDTO(service.getOne(id));
    }

    public CompanyDTO provideMembership(String companyId, String membershipId) {
        Company company = service.getOne(companyId);


        return null;
    }
}
