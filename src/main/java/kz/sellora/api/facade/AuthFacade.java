package kz.sellora.api.facade;

import kz.sellora.api.model.AuthRequestDTO;
import kz.sellora.api.model.AuthResponseDTO;
import kz.sellora.core.processor.AuthProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthProcessor processor;

    public AuthResponseDTO signIn(AuthRequestDTO authRequestDTO) {
        AuthProcessor.AuthTokens authTokens = processor.signIn(authRequestDTO.getUsername(), authRequestDTO.getPassword(), authRequestDTO.getDeviceId());
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(authTokens.accessToken());
        response.setRefreshToken(authTokens.refreshToken());

        return response;
    }

//    public AuthResponseDTO refresh(AuthRequestDTO authRequestDTO) {
//        AuthProcessor.AuthTokens tokens = processor.refresh(authRequestDTO.getRefreshToken(), authRequestDTO.getDeviceId());
//        AuthResponseDTO response = new AuthResponseDTO();
//        response.setToken(tokens.accessToken());
//        response.setRefreshToken(tokens.refreshToken());
//
//        return response;
//    }
}
