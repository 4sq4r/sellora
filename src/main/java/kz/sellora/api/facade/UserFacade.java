package kz.sellora.api.facade;

import kz.sellora.api.mapper.UserMapper;
import kz.sellora.api.model.UserDTO;
import kz.sellora.core.processor.UserProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserMapper mapper;
    private final UserProcessor processor;

    public UserDTO saveOne(UserDTO userDTO) {
        return mapper.toDTO(processor.saveOne(userDTO.getUsername(), userDTO.getPassword(), userDTO.getCompanyId()));
    }

    public UserDTO getOne(UserDTO userDTO) {
        return null;
    }
}
