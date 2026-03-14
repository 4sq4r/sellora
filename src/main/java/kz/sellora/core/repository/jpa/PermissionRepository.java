package kz.sellora.core.repository.jpa;

import kz.sellora.core.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
