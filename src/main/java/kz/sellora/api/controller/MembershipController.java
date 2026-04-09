package kz.sellora.api.controller;

import kz.sellora.api.facade.MembershipFacade;
import kz.sellora.api.model.MembershipDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/private/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipFacade facade;

    @PostMapping
    public MembershipDTO saveOne(@RequestBody MembershipDTO membershipDTO) {
        log.info("Incoming request to save membership: [companyId: {}, tenantId: {}]", membershipDTO.getCompanyId(), membershipDTO.getTenantId());
        return facade.saveOne(membershipDTO);
    }

    @PutMapping("/{id}")
    public MembershipDTO provideRole(@PathVariable String id,
                                     @RequestParam String roleId) {
        log.info("Incoming request to provide role to membership: [membershipId: {}, roleId: {}]", id, roleId);
        return facade.provideRole(id, roleId);
    }
}
