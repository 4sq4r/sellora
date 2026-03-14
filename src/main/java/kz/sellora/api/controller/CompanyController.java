package kz.sellora.api.controller;

import kz.sellora.core.model.entity.Company;
import kz.sellora.core.model.enums.CompanyStatus;
import kz.sellora.core.repository.jpa.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository repository;

    @PostMapping
    public Company saveOne(@RequestParam String name) {
        Company company = new Company();
        company.setName(name);
        company.setStatus(CompanyStatus.ACTIVE);

        return repository.save(company);
    }


}
