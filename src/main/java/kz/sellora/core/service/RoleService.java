package kz.sellora.core.service;

import kz.sellora.core.model.entity.Role;
import kz.sellora.core.model.enums.RoleContext;
import kz.sellora.core.repository.jpa.RoleRepository;
import kz.sellora.core.util.ErrorMessageSource;
import kz.sellora.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role saveOne(String name, RoleContext context, String description) {
        Role entity = new Role();
        entity.setName(name);
        entity.setContext(context);
        entity.setDescription(description);
        return repository.save(entity);
    }

    public Role getOne(String id) {
        return repository.findById(id).orElseThrow(
            () -> CustomException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ErrorMessageSource.ROLE_NOT_FOUND.getText(id))
                .build()
        );
    }

    public Role updateOne(Role role) {
        return repository.save(role);
    }
}
