package kz.sellora.core.processor;

import kz.sellora.core.model.entity.Company;
import kz.sellora.core.model.entity.User;
import kz.sellora.core.service.CompanyService;
import kz.sellora.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProcessor {

    private final UserService userService;
    private final CompanyService companyService;

    public User saveOne(String username, String password, String companyId) {
        Company company = companyService.getOne(companyId);
        return userService.saveOne(username, password, company);
    }
}
