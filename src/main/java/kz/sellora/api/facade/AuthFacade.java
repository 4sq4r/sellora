package kz.sellora.api.facade;

import kz.sellora.api.model.AuthDTO;
import kz.sellora.core.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthService service;

    public AuthDTO signUp(AuthDTO authDTO) {
        AuthDTO response = new AuthDTO();
        response.setToken(service.signUp(authDTO.getUsername(), authDTO.getPassword()));

        return response;
    }

    public AuthDTO signIn(AuthDTO authDTO) {
        AuthDTO response = new AuthDTO();
        response.setToken(service.signIn(authDTO.getUsername(), authDTO.getPassword()));

        return response;
    }
}
