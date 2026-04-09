package kz.sellora.api.controller;

import kz.sellora.api.facade.AuthFacade;
import kz.sellora.api.model.AuthRequestDTO;
import kz.sellora.api.model.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade facade;

    @PostMapping("/sign-in")
    public AuthResponseDTO signIn(@RequestBody AuthRequestDTO authRequestDTO) {
        log.info("Incoming request to sign in: {}", authRequestDTO.getUsername());
        return facade.signIn(authRequestDTO);
    }

//    @PostMapping("/refresh")
//    public AuthResponseDTO refresh(@RequestBody AuthRequestDTO authRequestDTO) {
//        log.info("Incoming request to refresh token");
//        return facade.refresh(authRequestDTO);
//    }
}
