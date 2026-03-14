package kz.sellora.api.facade;

import kz.sellora.api.model.AuthDTO;
import kz.sellora.core.processor.AuthProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthProcessor processor;

    public AuthDTO signIn(AuthDTO authDTO) {
        AuthProcessor.AuthTokens authTokens = processor.signIn(authDTO.getUsername(), authDTO.getPassword(), authDTO.getDeviceId());
        AuthDTO response = new AuthDTO();
        response.setToken(authTokens.accessToken());
        response.setRefreshToken(authTokens.refreshToken());

        return response;
    }

    public AuthDTO refresh(AuthDTO authDTO) {
        AuthProcessor.AuthTokens tokens = processor.refresh(authDTO.getRefreshToken(), authDTO.getDeviceId());
        AuthDTO response = new AuthDTO();
        response.setToken(tokens.accessToken());
        response.setRefreshToken(tokens.refreshToken());

        return response;
    }
}
