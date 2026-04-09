package kz.sellora.configuration.security;

import kz.sellora.core.service.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("selloraSecurity")
@RequiredArgsConstructor
public class SelloraSecurity {

    public boolean belongsToSellora(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) return false;

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserPrincipal user) {
            return "SELLORA".equals(user.getCompanyName());
        }

        return false;
    }

}
