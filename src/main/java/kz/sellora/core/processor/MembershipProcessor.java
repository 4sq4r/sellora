package kz.sellora.core.processor;

import kz.sellora.core.model.entity.Company;
import kz.sellora.core.model.entity.Membership;
import kz.sellora.core.model.entity.Role;
import kz.sellora.core.model.entity.Tenant;
import kz.sellora.core.service.CompanyService;
import kz.sellora.core.service.MembershipService;
import kz.sellora.core.service.RoleService;
import kz.sellora.core.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MembershipProcessor {

    private final MembershipService membershipService;
    private final CompanyService companyService;
    private final TenantService tenantService;
    private final RoleService roleService;

    public Membership saveMembership(String companyId, String tenantId) {
        Tenant tenant = tenantService.getOne(tenantId);
        Company company = companyService.getOne(companyId);

        return membershipService.saveOne(company, tenant);
    }

    public Membership provideRole(String id, String roleId) {
        Role role = roleService.getOne(roleId);
        Membership membership = membershipService.getOne(id);
        membership.getRoles().add(role);
        return membershipService.updateOne(membership);
    }
}
