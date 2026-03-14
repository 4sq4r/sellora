package kz.sellora.api.controller;

import kz.sellora.api.facade.CompanyFacade;
import kz.sellora.api.model.CompanyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyFacade facade;

    @PostMapping
    public CompanyDTO saveOne(@RequestBody CompanyDTO companyDTO) {
        log.info("Incoming request to save company: {}", companyDTO);
        return facade.saveOne(companyDTO);
    }

    @PutMapping("/{id}")
    public CompanyDTO provideMembership(@PathVariable String companyId,
                                        @RequestParam String membershipId) {
        log.info("Incoming request to provide membership [{}] to company [{}]", membershipId, companyId);
        return facade.provideMembership(companyId, membershipId);
    }

    @GetMapping("/{id}")
    public CompanyDTO getOne(@PathVariable String id) {
        log.info("Incoming request to get company: {}", id);
        return facade.getOne(id);
    }
}
