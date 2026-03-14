package kz.sellora.api.facade;

import kz.sellora.api.mapper.MembershipMapper;
import kz.sellora.api.model.MembershipDTO;
import kz.sellora.core.processor.MembershipProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MembershipFacade {

    private final MembershipMapper mapper;
    private final MembershipProcessor processor;

    public MembershipDTO saveOne(MembershipDTO membershipDTO) {
        return mapper.toDTO(processor.saveMembership(membershipDTO.getCompanyId(), membershipDTO.getTenantId()));
    }

    public MembershipDTO provideRole(String id, String roleId) {
        return mapper.toDTO(processor.provideRole(id, roleId));
    }
}
