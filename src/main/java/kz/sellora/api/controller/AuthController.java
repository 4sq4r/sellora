package kz.sellora.api.controller;

import kz.sellora.api.facade.AuthFacade;
import kz.sellora.api.model.AuthDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade facade;

    @PostMapping("/sign-up")
    public AuthDTO signUp(@RequestBody AuthDTO authDTO) {
        return facade.signUp(authDTO);
    }

    @PostMapping("/sign-in")
    public AuthDTO signIn(@RequestBody AuthDTO authDTO) {
        return facade.signIn(authDTO);
    }
}
