package kz.sellora.core.service;

import kz.sellora.core.model.entity.Company;
import kz.sellora.core.model.entity.Membership;
import kz.sellora.core.model.entity.Tenant;
import kz.sellora.core.repository.jpa.MembershipRepository;
import kz.sellora.core.util.ErrorMessageSource;
import kz.sellora.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository repository;

    public Membership saveOne(Company company, Tenant tenant) {
        Membership membership = new Membership();
        membership.setCompany(company);
        membership.setTenant(tenant);

        return repository.save(membership);
    }

    public Membership getOne(String id) {
        return repository.findById(id).orElseThrow(
            () -> CustomException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ErrorMessageSource.MEMBERSHIP_NOT_FOUND.getText(id))
                .build()
        );
    }

    public Membership updateOne(Membership membership) {
        return repository.save(membership);
    }
}
