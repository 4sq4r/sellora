package kz.sellora.core.service;

import kz.sellora.core.model.entity.Permission;
import kz.sellora.core.repository.jpa.PermissionRepository;
import kz.sellora.core.util.ErrorMessageSource;
import kz.sellora.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository repository;

    public Permission saveOne(String name, String description) {
        Permission entity = new Permission();
        entity.setName(name);
        entity.setDescription(description);
        return repository.save(entity);
    }

    public Permission getOne(String id) {
        return repository.findById(id).orElseThrow(
            () -> CustomException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ErrorMessageSource.PERMISSION_NOT_FOUND.getText(id))
                .build()
        );
    }
}
