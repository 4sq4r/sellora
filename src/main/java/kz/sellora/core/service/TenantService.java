package kz.sellora.core.service;

import kz.sellora.core.model.entity.Tenant;
import kz.sellora.core.model.enums.TenantStatus;
import kz.sellora.core.repository.jpa.TenantRepository;
import kz.sellora.core.util.ErrorMessageSource;
import kz.sellora.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository repository;

    public Tenant saveOne(String name) {
        String trimmed = name.trim();
        checkUniqueness(trimmed);
        Tenant entity = new Tenant();
        entity.setName(trimmed);
        entity.setStatus(TenantStatus.ACTIVE);

        return repository.save(entity);
    }

    private void checkUniqueness(String name) {
        if (repository.existsByName(name)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorMessageSource.TENANT_ALREADY_EXISTS.getText(name));
        }
    }
}
