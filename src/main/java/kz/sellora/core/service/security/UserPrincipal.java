package kz.sellora.core.service.security;

import kz.sellora.core.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final User user;

    @Getter
    private final String companyName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getCompany()
            .getMemberships()
            .stream()
            .flatMap(membership -> membership.getRoles().stream())
            .flatMap(role -> role.getPermissions().stream())
            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
            .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
