package kz.sellora.api.controller;

import kz.sellora.api.facade.AuthFacade;
import kz.sellora.api.model.AuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade facade;

    @PostMapping("/sign-up")
    public AuthDTO signUp(@RequestBody AuthDTO authDTO) {
        log.info("Incoming request to sign up: {}", authDTO.getUsername());
        return facade.signUp(authDTO);
    }

    @PostMapping("/sign-in")
    public AuthDTO signIn(@RequestBody AuthDTO authDTO) {
        log.info("Incoming request to sign in: {}", authDTO.getUsername());
        return facade.signIn(authDTO);
    }

    @PostMapping("/refresh")
    public AuthDTO refresh(@RequestBody AuthDTO authDTO) {
        log.info("Incoming request to refresh token");
        return facade.refresh(authDTO);
    }
}
