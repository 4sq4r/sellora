package kz.sellora.core.service;

import kz.sellora.core.model.entity.Company;
import kz.sellora.core.model.enums.CompanyStatus;
import kz.sellora.core.repository.jpa.CompanyRepository;
import kz.sellora.core.util.ErrorMessageSource;
import kz.sellora.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public Company saveOne(String name) {
        String trimmed = name.trim();
        checkUniqueness(trimmed);
        Company company = new Company();
        company.setName(trimmed);
        company.setStatus(CompanyStatus.ACTIVE);

        return repository.save(company);
    }

    private void checkUniqueness(String name) {
        if (repository.existsByName(name)) {
            throw CustomException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ErrorMessageSource.COMPANY_ALREADY_EXISTS.getText(name))
                .build();
        }
    }

    public Company getOne(String id) {
        return repository.findById(id).orElseThrow(
            () -> CustomException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ErrorMessageSource.COMPANY_NOT_FOUND.getText(id))
                .build()
        );
    }
}
