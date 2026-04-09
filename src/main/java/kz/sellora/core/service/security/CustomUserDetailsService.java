package kz.sellora.core.service.security;

import kz.sellora.core.model.entity.User;
import kz.sellora.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findByUsername(username);
        String companyName = user.getCompany() != null ? user.getCompany().getName() : null;
        return new UserPrincipal(user, companyName);
    }
}
