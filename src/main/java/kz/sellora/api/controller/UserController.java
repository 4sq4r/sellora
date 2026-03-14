package kz.sellora.api.controller;

import kz.sellora.api.facade.UserFacade;
import kz.sellora.api.model.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade facade;

    @PostMapping
    public UserDTO signUp(@RequestBody UserDTO userDTO) {
        log.info("Incoming request to sign up: {}", userDTO.getUsername());
        return facade.saveOne(userDTO);
    }

    @GetMapping
    public UserDTO signIn(@RequestBody UserDTO userDTO) {
        log.info("Incoming request to sign in: {}", userDTO.getUsername());
        return facade.getOne(userDTO);
    }
}
